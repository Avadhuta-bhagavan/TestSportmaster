package com.avadhuta.testmaven.service;

import com.avadhuta.testmaven.business.*;

import java.util.*;

/**
 *
 */
public interface ChainedUpdatesService {

    void scheduleForCalculation(List<CalculableResource> sheduledForCalculation);

    void scheduleForCalculation(CalculableResource calculableResource);

    void calculateAllQueue();

    boolean calculateNextObject()
            throws Exception;

    boolean lookForLostUpdates();
}