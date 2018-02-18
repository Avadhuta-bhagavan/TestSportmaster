package com.avadhuta.testmaven.service.impl;

import com.avadhuta.testmaven.business.*;
import com.avadhuta.testmaven.model.*;
import com.avadhuta.testmaven.service.*;
import org.springframework.stereotype.*;

import javax.transaction.*;

import static javax.transaction.Transactional.TxType.*;

/**
 * Методы этого сервиса вызываются при обработке бизнес-операций, связанных с работой шахты.
 * По нормальному, этот код должен или генериться, или должны напрямую использоваться процессоры.
 * Здесь интерфейс MiningService играет роль Фасада, который скрывает логику работы от веб-части программы.
 */
@Service ("MiningServiceInner")
public class MiningServiceImpl
        implements MiningService {

    private final ProcessorIndexer processorIndexer;

    public MiningServiceImpl(ProcessorIndexer processorIndexer) {
        this.processorIndexer = processorIndexer;
    }

    /**
     * Позволяет бригадиру смены сохранить информацию о результатах работы.
     *
     * @param shift Информация о выработке за смену.
     */
    @Override
    @Transactional (REQUIRES_NEW)
    public Shift saveShift(Shift shift) {
        if (shift.getId() != null) {
            shift = this.processorIndexer
                    .getJpaRepository(Shift.class)
                    .save(shift);
        }
        this.processorIndexer
                .getBusinessLogicProcessor(Shift.class)
                .calculateObjectState(shift, true);
        if (shift.getId() == null) {
            shift = this.processorIndexer
                    .getJpaRepository(Shift.class)
                    .save(shift);
        }
        return shift;
    }

    /**
     * Позволяет сортировщику сохранить отчёт о результатах своей работы.
     */
    @Override
    @Transactional (REQUIRES_NEW)
    public ClassificationReport saveClassificationReport(ClassificationReport classificationReport) {
        if (classificationReport.getId() != null) {
            classificationReport = this.processorIndexer
                    .getJpaRepository(ClassificationReport.class)
                    .save(classificationReport);
        }
        this.processorIndexer
                .getBusinessLogicProcessor(ClassificationReport.class)
                .calculateObjectState(classificationReport, true);
        if (classificationReport.getId() == null) {
            classificationReport = this.processorIndexer
                    .getJpaRepository(ClassificationReport.class)
                    .save(classificationReport);
        }
        return classificationReport;
    }

    /**
     * Позволяет оценщику сохранить отчёт о результатах своей работы.
     */
    @Override
    @Transactional (REQUIRES_NEW)
    public AppraiseReport saveAppraiseReport(AppraiseReport appraiseReport) {
        if (appraiseReport.getId() != null) {
            appraiseReport = this.processorIndexer
                    .getJpaRepository(AppraiseReport.class)
                    .save(appraiseReport);
        }
        this.processorIndexer
                .getBusinessLogicProcessor(AppraiseReport.class)
                .calculateObjectState(appraiseReport, true);
        if (appraiseReport.getId() == null) {
            appraiseReport = this.processorIndexer
                    .getJpaRepository(AppraiseReport.class)
                    .save(appraiseReport);
        }
        return appraiseReport;
    }

}