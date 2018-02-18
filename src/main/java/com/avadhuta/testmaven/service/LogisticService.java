package com.avadhuta.testmaven.service;

import com.avadhuta.testmaven.model.*;
import org.springframework.scheduling.annotation.*;

/**
 *
 */
public interface LogisticService {
    LoadingProcess createLoadingProcess();

    @Async
    void startLoadingProcess(Long loadingProcessId);
}