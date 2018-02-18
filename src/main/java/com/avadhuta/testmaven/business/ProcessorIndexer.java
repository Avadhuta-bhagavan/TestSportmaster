package com.avadhuta.testmaven.business;

import org.springframework.data.jpa.repository.*;

/**
 *
 */
public interface ProcessorIndexer {
    <T extends CalculableResource> Class<T> getClassForCalculableResource(
            CalculableResource calculableResource);

    <T extends CalculableResource> JpaRepository<T, Long> getJpaRepository(Class<T> clazz);

    <T extends CalculableResource> BusinessLogicProcessor<T> getBusinessLogicProcessor(Class<T> clazz);
}