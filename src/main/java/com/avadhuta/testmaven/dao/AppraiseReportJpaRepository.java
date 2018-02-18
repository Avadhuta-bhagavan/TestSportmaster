package com.avadhuta.testmaven.dao;

import com.avadhuta.testmaven.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

/**
 *
 */
public interface AppraiseReportJpaRepository
        extends JpaRepository<AppraiseReport, Long> {

    List<AppraiseReport> findByClassificationDetails(ClassificationDetails classificationDetails);

}