package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Тип кристаллов
 */
@Entity
@Table (name = "CRYSTAL_TYPE")
public class CrystalType {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @Basic (optional = false)
    private String name;
    /** Средняя плотность при перевозке в общем контейнере */
    @Basic (optional = false)
    private Double heapDencity;
    private Integer hardness;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHardness() {
        return hardness;
    }

    public void setHardness(Integer hardness) {
        this.hardness = hardness;
    }

    public Double getHeapDencity() {
        return heapDencity;
    }

    public void setHeapDencity(Double heapDencity) {
        this.heapDencity = heapDencity;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "CrystalType{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", heapDencity=" + heapDencity +
                ", hardness=" + hardness +
                '}';
    }
}