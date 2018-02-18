package com.avadhuta.testmaven.service.impl;

import com.avadhuta.testmaven.dao.*;
import com.avadhuta.testmaven.logistic.*;
import com.avadhuta.testmaven.model.*;
import com.avadhuta.testmaven.service.*;
import org.slf4j.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.time.*;

import static javax.transaction.Transactional.TxType.*;

/**
 * Позволяет запланировать и подтвердить процесс загрузки минералов в контейнеры.
 */
@Service
public class LogisticServiceImpl
        implements LogisticService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogisticServiceImpl.class);

    private final LoadingProcessJpaRepository loadingProcessJpaRepository;
    private final LogisticTransactionIsolationService logisticTIService;
    private final LogisticCalculator logisticCalculator;

    public LogisticServiceImpl(LoadingProcessJpaRepository loadingProcessJpaRepository,
            LogisticTransactionIsolationService logisticTIService,
            LogisticCalculator logisticCalculator) {
        this.loadingProcessJpaRepository = loadingProcessJpaRepository;
        this.logisticTIService = logisticTIService;
        this.logisticCalculator = logisticCalculator;
    }

    @Override
    @Transactional (REQUIRES_NEW)
    public LoadingProcess createLoadingProcess() {
        LoadingProcess loadingProcess = new LoadingProcess();
        loadingProcess.setDate(LocalDateTime.now());
        loadingProcess.setState(LoadingProcessState.SCHEDULED);
        loadingProcess = this.loadingProcessJpaRepository.save(loadingProcess);
        return loadingProcess;
    }

    @Override
    @Async
    @Transactional (NEVER)
    public void startLoadingProcess(Long loadingProcessId) {
        LoadingProcess loadingProcess = this.loadingProcessJpaRepository.findOne(loadingProcessId);
        if (loadingProcess.getState() != LoadingProcessState.SCHEDULED) {
            throw new IllegalStateException("LoadingProcess " + loadingProcessId +
                    " is in wrong state: " + loadingProcess.getState());
        }

        this.logisticTIService.updateLoadingProcessState(loadingProcessId,
                LoadingProcessState.STARTED);
        try {


            this.logisticTIService.updateLoadingProcessState(loadingProcessId,
                    LoadingProcessState.PLANNED);
        } catch (Exception ex) {
            LOGGER.error("Ошибка при расчёте параметров загрузки контейнеров: ", ex);
            this.logisticTIService.updateLoadingProcessState(loadingProcessId,
                    LoadingProcessState.FAILED);
        }
    }

}