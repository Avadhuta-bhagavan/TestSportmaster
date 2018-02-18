package com.avadhuta.testmaven.business.impl;

import com.avadhuta.testmaven.business.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import javax.transaction.*;
import java.util.*;

import static javax.transaction.Transactional.TxType.*;

/**
 * Позволяет выполнять бизнес-логику в отдельной транзакции.
 */
@Service
public class BusinessLogicTransactionIsolationServiceImpl
        implements BusinessLogicTransactionIsolationService {

    private final EntityManager entityManager;

    public BusinessLogicTransactionIsolationServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional (REQUIRES_NEW)
    public <T extends CalculableResource> List<CalculableResource> getObjectsForChainCalculation(
            JpaRepository<T, Long> jpaRepository, BusinessLogicProcessor<T> businessLogicProcessor,
            Long id) {
        T calculableResource = jpaRepository.findOne(id);
        if (calculableResource == null || !calculableResource.isNeedChainedRecalculation()) {
            return null;
        }
        List<CalculableResource> objectsForChainCalculation =
                businessLogicProcessor.getObjectsForChainCalculation(calculableResource);
        if (objectsForChainCalculation != null) {
            for (CalculableResource resource : objectsForChainCalculation) {
                entityManager.detach(resource);
            }
        }
        return objectsForChainCalculation;
    }

    @Override
    @Transactional (REQUIRES_NEW)
    public <T extends CalculableResource> void markObjectAsProcessed(JpaRepository<T, Long> jpaRepository, Long id) {
        T calculableResource = jpaRepository.findOne(id);
        if (calculableResource == null) {
            return;
        }
        calculableResource.setNeedChainedRecalculation(false);
    }

    @Override
    @Transactional (REQUIRES_NEW)
    public <T extends CalculableResource> T calculateObjectState(
            JpaRepository<T, Long> jpaRepository, BusinessLogicProcessor<T> businessLogicProcessor,
            Long id) {
        T calculableResource = jpaRepository.findOne(id);
        if (calculableResource == null) {
            return null;
        }
        businessLogicProcessor.calculateObjectState(calculableResource, false);
        return calculableResource;
    }

}