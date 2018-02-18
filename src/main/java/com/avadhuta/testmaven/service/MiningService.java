package com.avadhuta.testmaven.service;

import com.avadhuta.testmaven.model.*;

/**
 *
 */
public interface MiningService {

    Shift saveShift(Shift shift);

    ClassificationReport saveClassificationReport(ClassificationReport classificationReport);

    AppraiseReport saveAppraiseReport(AppraiseReport appraiseReport);
}