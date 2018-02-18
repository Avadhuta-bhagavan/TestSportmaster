package com.avadhuta.testmaven.service.impl;

import com.avadhuta.testmaven.model.*;
import com.avadhuta.testmaven.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.transaction.*;

import static javax.transaction.Transactional.TxType.*;

/**
 * Позволяет вынести момент инициации обработки цепочки объектов за пределы транзакции.
 * Это простой пример логики работы. В таких случаях лучше использовать AOP-прокси, но там есть свои проблемы.
 */
@Service ("MiningService")
public class MiningServiceProxy
        implements MiningService {

    private final MiningService delegate;
    private final ChainedUpdatesService chainedUpdatesService;

    public MiningServiceProxy(ChainedUpdatesService chainedUpdatesService,
            @Qualifier ("MiningServiceInner") MiningService delegate) {
        this.chainedUpdatesService = chainedUpdatesService;
        this.delegate = delegate;
    }

    @Override
    @Transactional (NEVER)
    public Shift saveShift(Shift shift) {
        // Транзакция начинается и завершается внутри вызова.
        shift = this.delegate.saveShift(shift);
        // Ставим объект в очередь на обработку.
        this.chainedUpdatesService.scheduleForCalculation(shift);
        return shift;
    }

    @Override
    @Transactional (NEVER)
    public ClassificationReport saveClassificationReport(ClassificationReport classificationReport) {
        classificationReport = this.delegate.saveClassificationReport(classificationReport);
        this.chainedUpdatesService.scheduleForCalculation(classificationReport);
        return classificationReport;
    }

    @Override
    @Transactional (NEVER)
    public AppraiseReport saveAppraiseReport(AppraiseReport appraiseReport) {
        appraiseReport = this.delegate.saveAppraiseReport(appraiseReport);
        this.chainedUpdatesService.scheduleForCalculation(appraiseReport);
        return appraiseReport;
    }

}