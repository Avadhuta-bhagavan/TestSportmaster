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
    /** Описание персональной перевозки минералов */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "containerCellLoading")
    private Set<CrystalTransportation> crystalTransportation;

    // Рассчитываемые поля ////////////////////////////////////////////////////////////////////////////////

    @Basic (optional = false)
    private Double usedVolume;
    @Basic (optional = false)
    private boolean filled;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public ContainerLoading getParent() {
        return parent;
    }

    public void setParent(ContainerLoading parent) {
        this.parent = parent;
    }

    public ContainerCellType getCellType() {
        return cellType;
    }

    public void setCellType(ContainerCellType cellType) {
        this.cellType = cellType;
    }

    public ContainerState getContainerState() {
        return containerState;
    }

    public void setContainerState(ContainerState containerState) {
        this.containerState = containerState;
    }

    public Set<HeapTransportation> getHeapTransportations() {
        return heapTransportations;
    }

    public void setHeapTransportations(Set<HeapTransportation> heapTransportations) {
        this.heapTransportations = heapTransportations;
    }

    public Set<CrystalTransportation> getCrystalTransportation() {
        return crystalTransportation;
    }

    public void setCrystalTransportation(Set<CrystalTransportation> crystalTransportation) {
        this.crystalTransportation = crystalTransportation;
    }

    public Double getUsedVolume() {
        return usedVolume;
    }

    public void setUsedVolume(Double usedVolume) {
        this.usedVolume = usedVolume;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}