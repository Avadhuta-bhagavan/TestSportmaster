package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Описание входящих в тип контейнера ячеек.
 */
@Entity
@Table (name = "CONTAINER_CELL_DESCRIPTION")
public class ContainerCellDescription {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Описание типа контейнера */
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private ContainerType parent;
    /** Тип ячейки */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private ContainerCellType cellType;
    /** Количество ячеек данного вида в контейнере */
    @Basic (optional = false)
    private Integer quantity;

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

    public ContainerType getParent() {
        return parent;
    }

    public void setParent(ContainerType parent) {
        this.parent = parent;
    }

    public ContainerCellType getCellType() {
        return cellType;
    }

    public void setCellType(ContainerCellType cellType) {
        this.cellType = cellType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}