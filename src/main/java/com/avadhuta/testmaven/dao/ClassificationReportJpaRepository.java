package com.avadhuta.testmaven.dao;

import com.avadhuta.testmaven.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 *
 */
@Repository
public interface ClassificationReportJpaRepository
        extends JpaRepository<ClassificationReport, Long> {

    List<ClassificationReport> findByShiftOutput(ShiftOutput shiftOutput);

}