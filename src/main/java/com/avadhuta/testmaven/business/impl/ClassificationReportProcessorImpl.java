package com.avadhuta.testmaven.business.impl;

import com.avadhuta.testmaven.business.*;
import com.avadhuta.testmaven.dao.*;
import com.avadhuta.testmaven.model.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 *
 */
@Service
public class ClassificationReportProcessorImpl
        implements ClassificationReportProcessor {

    private final AppraiseReportJpaRepository appraiseReportJpaRepository;

    public ClassificationReportProcessorImpl(AppraiseReportJpaRepository appraiseReportJpaRepository) {
        this.appraiseReportJpaRepository = appraiseReportJpaRepository;
    }

    @Override
    public void calculateObjectState(ClassificationReport classificationReport, boolean wasUpdatedBefore) {
        // Рассчитываем итоговые значения для отсортированных минералов.
        if (classificationReport.getDetails() != null) {
            for (ClassificationDetails classificationDetails : classificationReport.getDetails()) {
                this.calculateClassificationDetails(classificationReport, classificationDetails);
            }
        }
        // Рассчитываем итоговые значения для отчёта.
        double totalClassifiedOutput = 0.0;
        if (classificationReport.getDetails() != null) {
            for (ClassificationDetails classificationDetails : classificationReport.getDetails()) {
                totalClassifiedOutput += classificationDetails.getClassifiedOutput();
            }
        }
        classificationReport.setTotalClassifiedOutput(totalClassifiedOutput);
        // Нужен перерасчёт данных смены если объект был изменён.
        classificationReport.setNeedChainedRecalculation(
                classificationReport.isNeedChainedRecalculation() || wasUpdatedBefore);
    }

    private void calculateClassificationDetails(ClassificationReport classificationReport,
            ClassificationDetails classificationDetails) {
        classificationDetails.setMine(classificationReport.getShiftOutput().getParent().getMine());
        classificationDetails.setDate(classificationReport.getShiftOutput().getParent().getDate());
        double totalAppraisedOutput = 0.0;
        double writeOffMassForAppraise = 0.0;
        if (classificationDetails.getId() != null) {
            List<AppraiseReport> byClassificationDetails =
                    this.appraiseReportJpaRepository.findByClassificationDetails(classificationDetails);
            for (AppraiseReport appraiseReport : byClassificationDetails) {
                if (appraiseReport.getAppraisedOutput() != null) {
                    totalAppraisedOutput += appraiseReport.getAppraisedOutput();
                }
                if (appraiseReport.getWriteOffMass() != null) {
                    writeOffMassForAppraise += appraiseReport.getWriteOffMass();
                }
            }
        }
        boolean appraisingCompleted =
                (totalAppraisedOutput + writeOffMassForAppraise) >= classificationDetails.getClassifiedOutput();
        classificationDetails.setTotalAppraisedOutput(totalAppraisedOutput);
        classificationDetails.setWriteOffMassForAppraise(writeOffMassForAppraise);
        classificationDetails.setAppraisingCompleted(appraisingCompleted);
    }

    @Override
    public List<CalculableResource> getObjectsForChainCalculation(ClassificationReport object) {
        Shift shift = object.getShiftOutput().getParent();
        return Collections.singletonList(shift);
    }

}