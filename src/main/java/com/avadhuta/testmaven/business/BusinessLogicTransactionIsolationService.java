package com.avadhuta.testmaven.business;

import org.springframework.data.jpa.repository.*;

import javax.transaction.*;
import java.util.*;

import static javax.transaction.Transactional.TxType.*;

/**
 *
 */
public interface BusinessLogicTransactionIsolationService {
    @Transactional (REQUIRES_NEW)
    <T extends CalculableResource> List<CalculableResource> getObjectsForChainCalculation(
            JpaRepository<T, Long> jpaRepository, BusinessLogicProcessor<T> businessLogicProcessor,
            Long id);

    @Transactional (REQUIRES_NEW)
    <T extends CalculableResource> void markObjectAsProcessed(JpaRepository<T, Long> jpaRepository, Long id);

    @Transactional (REQUIRES_NEW)
    <T extends CalculableResource> T calculateObjectState(
            JpaRepository<T, Long> jpaRepository, BusinessLogicProcessor<T> businessLogicProcessor,
            Long id);
}