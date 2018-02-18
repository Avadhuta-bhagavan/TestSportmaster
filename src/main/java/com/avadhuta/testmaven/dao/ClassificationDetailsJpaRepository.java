package com.avadhuta.testmaven.dao;

import com.avadhuta.testmaven.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 *
 */
@Repository
public interface ClassificationDetailsJpaRepository
        extends JpaRepository<ClassificationDetails, Long> {
}