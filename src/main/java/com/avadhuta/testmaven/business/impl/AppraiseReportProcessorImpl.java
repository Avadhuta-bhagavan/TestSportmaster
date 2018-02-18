package com.avadhuta.testmaven.business.impl;

import com.avadhuta.testmaven.business.*;
import com.avadhuta.testmaven.model.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 *
 */
@Service
public class AppraiseReportProcessorImpl
        implements AppraiseReportProcessor {

    @Override
    public void calculateObjectState(AppraiseReport appraiseReport, boolean wasUpdatedBefore) {
        // Рассчитываем информацию по отдельным кристалам
        if (appraiseReport.getDetails() != null) {
            for (CrystalDetails crystalDetails : appraiseReport.getDetails()) {
                this.calculateCrystalDetails(appraiseReport, crystalDetails);
            }
        }
        // Рассчитываем итоговые цифры
        double appraisedOutput = 0.0;
        if (appraiseReport.getDetails() != null) {
            for (CrystalDetails crystalDetails : appraiseReport.getDetails()) {
                if (crystalDetails.getMass() != null) {
                    appraisedOutput += crystalDetails.getMass();
                }
            }
        }
        appraiseReport.setAppraisedOutput(appraisedOutput);
        // Нужен перерасчёт данных смены если объект был изменён.
        appraiseReport.setNeedChainedRecalculation(
                appraiseReport.isNeedChainedRecalculation() || wasUpdatedBefore);
    }

    private void calculateCrystalDetails(AppraiseReport appraiseReport, CrystalDetails crystalDetails) {
        crystalDetails.setMine(appraiseReport.getClassificationDetails().getMine());
        crystalDetails.setDate(appraiseReport.getClassificationDetails().getDate());
        if (crystalDetails.getCrystalState() == null) {
            crystalDetails.setCrystalState(CrystalState.ON_STORAGE);
        }
    }

    @Override
    public List<CalculableResource> getObjectsForChainCalculation(AppraiseReport appraiseReport) {
        ClassificationReport classificationReport = appraiseReport.getClassificationDetails().getParent();
        return Collections.singletonList(classificationReport);
    }

}