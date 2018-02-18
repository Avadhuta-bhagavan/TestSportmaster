package com.avadhuta.testmaven.model;

import javax.persistence.*;
import java.util.*;

/**
 *
 */
@Entity
@Table (name = "CONTAINER_CELL_LOADING")
public class ContainerCellLoading {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private ContainerLoading parent;
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private ContainerCellType cellType;
    /** Состояние данной ячейки в контейнере. */
    @Basic (optional = false)
    private ContainerState containerState;
    /** Описание валовой перевозки минералов */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "containerCellLoading")
    private Set<HeapTransportation> heapTransportations;

    // Рассчитываемые поля ////////////////////////////////////////////////////////////////////////////////

    @Basic (optional = false)
    private Double usedVolume;
    @Basic (optional = false)
    private boolean filled;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

}