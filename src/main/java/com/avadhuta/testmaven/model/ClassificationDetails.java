package com.avadhuta.testmaven.model;

import com.avadhuta.testmaven.business.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

/**
 * Результат работы сортировщика.
 */
@Entity
@Table (name = "CLASSIFICATION_DETAILS")
public class ClassificationDetails
        implements BookableResource {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private ClassificationReport parent;
    /** Тип кристаллов */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private CrystalType crystalType;
    /** Ценность кристаллов */
    @Basic (optional = false)
    private CrystalCostClassification crystalCostClassification;
    /** Масса отсортированных кристаллов, размещённых в ящике */
    @Basic (optional = false)
    private Double classifiedOutput;
    /** Идентификатор (штрихкод) ящика с отсортированными кристаллами */
    @Basic (optional = false)
    private String vesselBarCode;
    /** Описание валовой перевозки минералов */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "classificationDetails")
    private Set<HeapTransportation> heapTransportations;

    // Участие в бизнес процессах /////////////////////////////////////////////////////////////////////////

    /** Отметка о том, какой именно процесс забронировал ящик с отсортированными кристаллами */
    @ManyToOne (fetch = FetchType.LAZY)
    private LoadingProcess bookedForProcess;

    // Рассчитываемые поля ////////////////////////////////////////////////////////////////////////////////

    /** Шахта */
    @ManyToOne (fetch = FetchType.LAZY)
    private Mine mine;
    /** Дата добычи */
    @Basic (optional = false)
    private LocalDate date;
    /** Общая масса оценённых кристаллов */
    @Basic (optional = false)
    private Double totalAppraisedOutput;
    /** Общая масса отходов при оценке */
    @Basic (optional = false)
    private Double writeOffMassForAppraise;
    /** Отметка об окончании оценки результатов сортировки */
    @Basic (optional = false)
    private boolean appraisingCompleted;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassificationReport getParent() {
        return parent;
    }

    public void setParent(ClassificationReport parent) {
        this.parent = parent;
    }

    public CrystalCostClassification getCrystalCostClassification() {
        return crystalCostClassification;
    }

    public void setCrystalCostClassification(CrystalCostClassification crystalCostClassification) {
        this.crystalCostClassification = crystalCostClassification;
    }

    public CrystalType getCrystalType() {
        return crystalType;
    }

    public void setCrystalType(CrystalType crystalType) {
        this.crystalType = crystalType;
    }

    public String getVesselBarCode() {
        return vesselBarCode;
    }

    public void setVesselBarCode(String vesselBarCode) {
        this.vesselBarCode = vesselBarCode;
    }

    public boolean isAppraisingCompleted() {
        return appraisingCompleted;
    }

    public void setAppraisingCompleted(boolean appraisingCompleted) {
        this.appraisingCompleted = appraisingCompleted;
    }

    public Double getWriteOffMassForAppraise() {
        return writeOffMassForAppraise;
    }

    public void setWriteOffMassForAppraise(Double writeOffMassForAppraise) {
        this.writeOffMassForAppraise = writeOffMassForAppraise;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Double getTotalAppraisedOutput() {
        return totalAppraisedOutput;
    }

    public void setTotalAppraisedOutput(Double totalAppraisedOutput) {
        this.totalAppraisedOutput = totalAppraisedOutput;
    }

    public Double getClassifiedOutput() {
        return classifiedOutput;
    }

    public void setClassifiedOutput(Double classifiedOutput) {
        this.classifiedOutput = classifiedOutput;
    }

    public Mine getMine() {
        return mine;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LoadingProcess getBookedForProcess() {
        return bookedForProcess;
    }

    public void setBookedForProcess(LoadingProcess bookedForProcess) {
        this.bookedForProcess = bookedForProcess;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "ClassificationDetails{" +
                "id=" + id +
                ", version=" + version +
                ", parent=" + parent.getId() +
                ", crystalType=" + crystalType +
                ", crystalCostClassification=" + crystalCostClassification +
                ", classifiedOutput=" + classifiedOutput +
                ", vesselBarCode='" + vesselBarCode + '\'' +
                ", bookedForProcess=" + bookedForProcess +
                ", mine=" + mine +
                ", date=" + date +
                ", totalAppraisedOutput=" + totalAppraisedOutput +
                ", writeOffMassForAppraise=" + writeOffMassForAppraise +
                ", appraisingCompleted=" + appraisingCompleted +
                '}';
    }
}