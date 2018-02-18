package com.avadhuta.testmaven.dao;

import com.avadhuta.testmaven.model.*;
import org.springframework.data.jpa.repository.*;

/**
 *
 */
public interface CellLoadingJpaRepository
        extends JpaRepository<CellLoading, Long> {
}