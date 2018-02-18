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
public class ShiftProcessorImpl
        implements ShiftProcessor {

    private final ClassificationReportJpaRepository classificationReportJpaRepository;

    public ShiftProcessorImpl(ClassificationReportJpaRepository classificationReportJpaRepository) {
        this.classificationReportJpaRepository = classificationReportJpaRepository;
    }

    @Override
    public void calculateObjectState(Shift shift, boolean wasUpdatedBefore) {
        // Рассчитываем итоговые значения для добытых за смену минералов.
        if (shift.getDetails() != null) {
            for (ShiftOutput shiftOutput : shift.getDetails()) {
                this.calculateShiftOutput(shiftOutput);
            }
        }
        // Рассчитываем итоговые значения для смены.
        double totalShiftOutput = 0.0;
        if (shift.getDetails() != null) {
            for (ShiftOutput shiftOutput : shift.getDetails()) {
                totalShiftOutput += shiftOutput.getShiftOutput();
            }
        }
        shift.setTotalShiftOutput(totalShiftOutput);
        // У смены нет влияния на другие объекты.
        shift.setNeedChainedRecalculation(false);
    }

    private void calculateShiftOutput(ShiftOutput shiftOutput) {
        double totalClassifiedOutput = 0.0;
        double writeOffMassForClassification = 0.0;
        if (shiftOutput.getId() != null) {
            // Это существующий объект.
            List<ClassificationReport> byShiftOutput =
                    this.classificationReportJpaRepository.findByShiftOutput(shiftOutput);
            for (ClassificationReport classificationReport : byShiftOutput) {
                if (classificationReport.getTotalClassifiedOutput() != null) {
                    totalClassifiedOutput += classificationReport.getTotalClassifiedOutput();
                }
                if (classificationReport.getWriteOffMass() != null) {
                    writeOffMassForClassification += classificationReport.getWriteOffMass();
                }
            }
        }
        boolean classificationCompleted =
                (totalClassifiedOutput + writeOffMassForClassification) >= shiftOutput.getShiftOutput();

        shiftOutput.setTotalClassifiedOutput(totalClassifiedOutput);
        shiftOutput.setWriteOffMassForClassification(writeOffMassForClassification);
        shiftOutput.setClassificationCompleted(classificationCompleted);
    }

    @Override
    public List<CalculableResource> getObjectsForChainCalculation(Shift shift) {
        return null;
    }

}