package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Описание дефекта кристалла.
 */
@Entity
@Table (name = "CRYSTAL_DEFECT")
public class CrystalDefectDescription {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private CrystalDetails parent;
    @Basic (optional = false)
    private CrystalDefectType defectType;
    @Basic (optional = false)
    private String description;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CrystalDetails getParent() {
        return parent;
    }

    public void setParent(CrystalDetails parent) {
        this.parent = parent;
    }

    public CrystalDefectType getDefectType() {
        return defectType;
    }

    public void setDefectType(CrystalDefectType defectType) {
        this.defectType = defectType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}