package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Запись о количестве добытой за смену породы.
 */
@Entity
@Table (name = "SHIFT_OUTPUT")
public class ShiftOutput {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Смена, во время которой была добыта порода */
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private Shift parent;
    /** Тип добытых кристаллов */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private CrystalType crystalType;
    /** Масса добытой породы */
    @Basic (optional = false)
    private Double shiftOutput;
    /** Идентификатор (штрихкод) ящика с добытой породой */
    @Basic (optional = false)
    private String vesselBarCode;

    // Рассчитываемые поля ////////////////////////////////////////////////////////////////////////////////

    /** Масса отсортированной породы */
    @Basic (optional = false)
    private Double totalClassifiedOutput;
    /** Общая масса отходов при сортировке */
    @Basic (optional = false)
    private Double writeOffMassForClassification;
    /** Отметка об окончании сортировки выработки за смену */
    @Basic (optional = false)
    private boolean classificationCompleted;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CrystalType getCrystalType() {
        return crystalType;
    }

    public void setCrystalType(CrystalType crystalType) {
        this.crystalType = crystalType;
    }

    public Double getShiftOutput() {
        return shiftOutput;
    }

    public void setShiftOutput(Double shiftOutput) {
        this.shiftOutput = shiftOutput;
    }

    public Shift getParent() {
        return parent;
    }

    public void setParent(Shift parent) {
        this.parent = parent;
    }

    public Double getWriteOffMassForClassification() {
        return writeOffMassForClassification;
    }

    public void setWriteOffMassForClassification(Double writeOffMassForClassification) {
        this.writeOffMassForClassification = writeOffMassForClassification;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isClassificationCompleted() {
        return classificationCompleted;
    }

    public void setClassificationCompleted(boolean classificationCompleted) {
        this.classificationCompleted = classificationCompleted;
    }

    public Double getTotalClassifiedOutput() {
        return totalClassifiedOutput;
    }

    public void setTotalClassifiedOutput(Double totalClassifiedOutput) {
        this.totalClassifiedOutput = totalClassifiedOutput;
    }

    public String getVesselBarCode() {
        return vesselBarCode;
    }

    public void setVesselBarCode(String vesselBarCode) {
        this.vesselBarCode = vesselBarCode;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "ShiftOutput{" +
                "id=" + id +
                ", version=" + version +
                ", parent=" + parent.getId() +
                ", crystalType=" + crystalType +
                ", shiftOutput=" + shiftOutput +
                ", vesselBarCode='" + vesselBarCode + '\'' +
                ", totalClassifiedOutput=" + totalClassifiedOutput +
                ", writeOffMassForClassification=" + writeOffMassForClassification +
                ", classificationCompleted=" + classificationCompleted +
                '}';
    }
}