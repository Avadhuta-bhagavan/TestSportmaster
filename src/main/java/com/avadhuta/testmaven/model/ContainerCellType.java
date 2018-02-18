package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Описание типа ячейки в контейнере.
 */
@Entity
@Table (name = "CONTAINER_CELL_TYPE")
public class ContainerCellType {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @Basic (optional = false)
    private String name;
    @Basic (optional = false)
    private ContainerCellClass cellClass;
    @Basic (optional = false)
    private Double widthX;
    @Basic (optional = false)
    private Double widthY;
    @Basic (optional = false)
    private Double height;
    @Basic (optional = false)
    private Double volume;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContainerCellClass getCellClass() {
        return cellClass;
    }

    public void setCellClass(ContainerCellClass cellClass) {
        this.cellClass = cellClass;
    }

    public Double getWidthX() {
        return widthX;
    }

    public void setWidthX(Double widthX) {
        this.widthX = widthX;
    }

    public Double getWidthY() {
        return widthY;
    }

    public void setWidthY(Double widthY) {
        this.widthY = widthY;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

}