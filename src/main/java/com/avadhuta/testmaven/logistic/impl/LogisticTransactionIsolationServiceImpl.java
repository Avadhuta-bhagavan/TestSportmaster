package com.avadhuta.testmaven.logistic.impl;

import com.avadhuta.testmaven.dao.*;
import com.avadhuta.testmaven.logistic.*;
import com.avadhuta.testmaven.model.*;
import org.springframework.stereotype.*;

import javax.transaction.*;

import static javax.transaction.Transactional.TxType.*;

/**
 *
 */
@Service
public class LogisticTransactionIsolationServiceImpl
        implements LogisticTransactionIsolationService {

    private final LoadingProcessJpaRepository loadingProcessJpaRepository;

    public LogisticTransactionIsolationServiceImpl(LoadingProcessJpaRepository loadingProcessJpaRepository) {
        this.loadingProcessJpaRepository = loadingProcessJpaRepository;
    }

    @Override
    @Transactional (REQUIRES_NEW)
    public void updateLoadingProcessState(Long id, LoadingProcessState state) {
        LoadingProcess loadingProcess = this.loadingProcessJpaRepository.findOne(id);
        loadingProcess.setState(state);
    }

}