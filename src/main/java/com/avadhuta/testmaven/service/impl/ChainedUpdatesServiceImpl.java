package com.avadhuta.testmaven.service.impl;

import com.avadhuta.testmaven.business.*;
import com.avadhuta.testmaven.service.*;
import org.slf4j.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Это пример реализации класса, обеспечивающего перерасчёт содержимого связанных объектов.
 */
@Service
public class ChainedUpdatesServiceImpl
        implements ChainedUpdatesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChainedUpdatesServiceImpl.class);

    private final ProcessorIndexer processorIndexer;
    private final BusinessLogicTransactionIsolationService businessLogicTransactionIsolationService;
    /** Используем LinkedList для очереди FIFO. */
    private final LinkedList<ObjectInfo> objectsForUpdate = new LinkedList<>();

    public ChainedUpdatesServiceImpl(ProcessorIndexer processorIndexer,
            BusinessLogicTransactionIsolationService businessLogicTransactionIsolationService) {
        this.processorIndexer = processorIndexer;
        this.businessLogicTransactionIsolationService = businessLogicTransactionIsolationService;
    }

    @Override
    public void scheduleForCalculation(List<CalculableResource> sheduledForCalculation) {
        if (sheduledForCalculation == null || sheduledForCalculation.isEmpty()) {
            return;
        }
        for (CalculableResource calculableResource : sheduledForCalculation) {
            this.scheduleForCalculation(calculableResource);
        }
    }

    @Override
    public void scheduleForCalculation(CalculableResource calculableResource) {
        if (!calculableResource.isNeedChainedRecalculation()) {
            return;
        }
        ObjectInfo objectInfo = new ObjectInfo(
                this.processorIndexer.getClassForCalculableResource(calculableResource),
                calculableResource.getId());
        synchronized (this.objectsForUpdate) {
            this.objectsForUpdate.addLast(objectInfo);
        }
    }

    /**
     * Это метод только для тестирования. Обработка очереди изменений должна проводиться в многопоточной среде.
     */
    @Override
    public void calculateAllQueue() {
        boolean hasNext = true;
        for (int i = 0; i < 100 && hasNext; i++) {
            try {
                hasNext = calculateNextObject();

            } catch (Exception ex) {
                LOGGER.error("Can't calculate state of object: ", ex);
                hasNext = true;
            }
        }
    }

    @Override
    public boolean calculateNextObject()
            throws Exception {
        ObjectInfo objectInfo;
        synchronized (this.objectsForUpdate) {
            if (this.objectsForUpdate.isEmpty()) {
                return false;
            }
            objectInfo = this.objectsForUpdate.removeFirst();
        }
        // Загружаем в память связанные объекты.
        //noinspection unchecked
        JpaRepository<CalculableResource, Long> jpaRepository =
                this.processorIndexer.getJpaRepository(objectInfo.clazz);
        //noinspection unchecked
        BusinessLogicProcessor<CalculableResource> businessLogicProcessor =
                this.processorIndexer.getBusinessLogicProcessor(objectInfo.clazz);
        List<CalculableResource> objectsForChainCalculation =
                this.businessLogicTransactionIsolationService.getObjectsForChainCalculation(
                        jpaRepository, businessLogicProcessor, objectInfo.id);

        // Проводим планирование перерасчёта всех связанных объектов.
        if (objectsForChainCalculation != null) {
            for (CalculableResource calculableResource : objectsForChainCalculation) {
                this.recalculateResource(calculableResource);
            }
        }

        // Отмечаем начальный объект как обработанный.
        // TODO Тут мы надеемся, что перерасчёт вложенных объектов будет удачным.
        // На самом деле нужно проконтролировать этот процесс и только потом менять значение флага.
        this.businessLogicTransactionIsolationService.markObjectAsProcessed(jpaRepository, objectInfo.id);
        return true;
    }

    private void recalculateResource(CalculableResource calculableResource) {
        Class<CalculableResource> clazz =
                this.processorIndexer.getClassForCalculableResource(calculableResource);
        //noinspection unchecked
        JpaRepository<CalculableResource, Long> jpaRepository =
                this.processorIndexer.getJpaRepository(clazz);
        //noinspection unchecked
        BusinessLogicProcessor<CalculableResource> businessLogicProcessor =
                this.processorIndexer.getBusinessLogicProcessor(clazz);
        try {
            CalculableResource updatedResource = this.businessLogicTransactionIsolationService.calculateObjectState(
                    jpaRepository, businessLogicProcessor, calculableResource.getId());
            // Отмечаем объекты для расчётов дальше по цепочке.
            this.scheduleForCalculation(updatedResource);

        } catch (Exception ex) {
            // Здесь должен быть код для восстановления после ошибки, но пока его нет.
            LOGGER.error("Can't calculate state of object. Class: {} ID: {}",
                    clazz, calculableResource.getId(), ex);
        }
    }

    @Override
    public boolean lookForLostUpdates() {
        // TODO Сканируем базу данных на предмет наличия объектов, у которых выставлен флаг needChainedRecalculation.
        return false;
    }

    private static class ObjectInfo {
        private final Class clazz;
        private final Long id;

        private ObjectInfo(Class clazz, Long id) {
            this.clazz = clazz;
            this.id = id;
        }
    }

}