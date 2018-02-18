package com.avadhuta.testmaven.logistic;

import com.avadhuta.testmaven.model.*;

import javax.transaction.*;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

/**
 *
 */
public interface LogisticTransactionIsolationService {
    @Transactional (REQUIRES_NEW)
    void updateLoadingProcessState(Long id, LoadingProcessState state);
}