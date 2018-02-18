package com.avadhuta.testmaven.business.impl;

import com.avadhuta.testmaven.business.*;
import com.avadhuta.testmaven.dao.*;
import com.avadhuta.testmaven.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 *
 */
@Service
public class ProcessorIndexerImpl
        implements ProcessorIndexer {

    private final ShiftJpaRepository shiftJpaRepository;
    private final ClassificationReportJpaRepository classificationReportJpaRepository;
    private final AppraiseReportJpaRepository appraiseReportJpaRepository;

    private final ShiftProcessor shiftProcessor;
    private final ClassificationReportProcessor classificationReportProcessor;
    private final AppraiseReportProcessor appraiseReportProcessor;

    public ProcessorIndexerImpl(ShiftJpaRepository shiftJpaRepository,
            ClassificationReportJpaRepository classificationReportJpaRepository,
            AppraiseReportJpaRepository appraiseReportJpaRepository, ShiftProcessor shiftProcessor,
            ClassificationReportProcessor classificationReportProcessor,
            AppraiseReportProcessor appraiseReportProcessor) {
        this.shiftJpaRepository = shiftJpaRepository;
        this.classificationReportJpaRepository = classificationReportJpaRepository;
        this.appraiseReportJpaRepository = appraiseReportJpaRepository;
        this.shiftProcessor = shiftProcessor;
        this.classificationReportProcessor = classificationReportProcessor;
        this.appraiseReportProcessor = appraiseReportProcessor;
    }

    @Override
    public <T extends CalculableResource> Class<T> getClassForCalculableResource(
            CalculableResource calculableResource) {
        if (calculableResource instanceof Shift) {
            //noinspection unchecked
            return (Class<T>) Shift.class;
        } else if (calculableResource instanceof ClassificationReport) {
            //noinspection unchecked
            return (Class<T>) ClassificationReport.class;
        } else if (calculableResource instanceof AppraiseReport) {
            //noinspection unchecked
            return (Class<T>) AppraiseReport.class;
        } else {
            throw new IllegalArgumentException("Unsupported class found: " + calculableResource.getClass());
        }
    }

    @Override
    public <T extends CalculableResource> JpaRepository<T, Long> getJpaRepository(Class<T> clazz) {
        if (clazz.equals(Shift.class)) {
            //noinspection unchecked
            return (JpaRepository<T, Long>) this.shiftJpaRepository;
        } else if (clazz.equals(ClassificationReport.class)) {
            //noinspection unchecked
            return (JpaRepository<T, Long>) this.classificationReportJpaRepository;
        } else if (clazz.equals(AppraiseReport.class)) {
            //noinspection unchecked
            return (JpaRepository<T, Long>) this.appraiseReportJpaRepository;
        } else {
            throw new IllegalArgumentException("Unsupported class found: " + clazz);
        }
    }

    @Override
    public <T extends CalculableResource> BusinessLogicProcessor<T> getBusinessLogicProcessor(Class<T> clazz) {
        if (clazz.equals(Shift.class)) {
            //noinspection unchecked
            return (BusinessLogicProcessor<T>) this.shiftProcessor;
        } else if (clazz.equals(ClassificationReport.class)) {
            //noinspection unchecked
            return (BusinessLogicProcessor<T>) this.classificationReportProcessor;
        } else if (clazz.equals(AppraiseReport.class)) {
            //noinspection unchecked
            return (BusinessLogicProcessor<T>) this.appraiseReportProcessor;
        } else {
            throw new IllegalArgumentException("Unsupported class found: " + clazz);
        }
    }

}