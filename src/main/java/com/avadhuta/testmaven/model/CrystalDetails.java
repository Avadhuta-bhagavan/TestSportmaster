package com.avadhuta.testmaven.model;

import com.avadhuta.testmaven.business.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

/**
 * Описание кристалла высокого уровня ценности
 */
@Entity
@Table (name = "CRYSTAL_DETAILS")
public class CrystalDetails
        implements BookableResource {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Отчёт оценщика */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private AppraiseReport appraiseReport;
    /** Тип кристалла */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private CrystalType crystalType;
    /** Ценность кристалла */
    @Basic (optional = false)
    private CrystalCostClassification crystalCostClassification;
    /** Общая чистота кристалла */
    @Basic (optional = false)
    private CrystalClearnessType crystalClearnessType;
    /** Масса кристалла */
    @Basic (optional = false)
    private Double mass;
    /** Ширина по оси Х кристалла */
    @Basic (optional = false)
    private Double widthX;
    /** Ширина по оси Y кристалла */
    @Basic (optional = false)
    private Double widthY;
    /** Высота кристалла */
    @Basic (optional = false)
    private Double height;
    /** Описание и комментарии */
    private String description;
    /** Идентификатор (штрихкод) бирки на кристалле */
    @Basic (optional = false)
    private String сrystalBarCode;
    /** Описание дефектов кристалла */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "parent")
    private Set<CrystalDefectDescription> defects;

    // Участие в бизнес процессах /////////////////////////////////////////////////////////////////////////

    /** Местонахождение кристалла */
    @Basic (optional = false)
    private CrystalState crystalState;
    /** Отметка о том, какой именно процесс забронировал кристалл */
    @ManyToOne (fetch = FetchType.LAZY)
    private LoadingProcess bookedForProcess;
    /** Описание персональной перевозки кристалла */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "crystalDetails")
    private Set<CrystalTransportation> crystalTransportation;

    // Рассчитываемые поля ////////////////////////////////////////////////////////////////////////////////

    /** Шахта */
    @ManyToOne (fetch = FetchType.LAZY)
    private Mine mine;
    /** Дата добычи */
    @Basic (optional = false)
    private LocalDate date;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppraiseReport getAppraiseReport() {
        return appraiseReport;
    }

    public void setAppraiseReport(AppraiseReport appraiseReport) {
        this.appraiseReport = appraiseReport;
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

    public CrystalType getCrystalType() {
        return crystalType;
    }

    public void setCrystalType(CrystalType crystalType) {
        this.crystalType = crystalType;
    }

    public CrystalCostClassification getCrystalCostClassification() {
        return crystalCostClassification;
    }

    public void setCrystalCostClassification(CrystalCostClassification crystalCostClassification) {
        this.crystalCostClassification = crystalCostClassification;
    }

    public CrystalClearnessType getCrystalClearnessType() {
        return crystalClearnessType;
    }

    public void setCrystalClearnessType(CrystalClearnessType crystalClearnessType) {
        this.crystalClearnessType = crystalClearnessType;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getСrystalBarCode() {
        return сrystalBarCode;
    }

    public void setСrystalBarCode(String сrystalBarCode) {
        this.сrystalBarCode = сrystalBarCode;
    }

    public Set<CrystalDefectDescription> getDefects() {
        return defects;
    }

    public void setDefects(Set<CrystalDefectDescription> defects) {
        this.defects = defects;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public CrystalState getCrystalState() {
        return crystalState;
    }

    public void setCrystalState(CrystalState crystalState) {
        this.crystalState = crystalState;
    }

    public LoadingProcess getBookedForProcess() {
        return bookedForProcess;
    }

    public void setBookedForProcess(LoadingProcess bookedForProcess) {
        this.bookedForProcess = bookedForProcess;
    }

    public Set<CrystalTransportation> getCrystalTransportation() {
        return crystalTransportation;
    }

    public void setCrystalTransportation(Set<CrystalTransportation> crystalTransportation) {
        this.crystalTransportation = crystalTransportation;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "CrystalDetails{" +
                "id=" + id +
                ", version=" + version +
                ", appraiseReport=" + appraiseReport.getId() +
                ", crystalType=" + crystalType +
                ", crystalCostClassification=" + crystalCostClassification +
                ", crystalClearnessType=" + crystalClearnessType +
                ", mass=" + mass +
                ", widthX=" + widthX +
                ", widthY=" + widthY +
                ", height=" + height +
                ", description='" + description + '\'' +
                ", сrystalBarCode='" + сrystalBarCode + '\'' +
                ", defects=" + defects +
                ", crystalState=" + crystalState +
                ", bookedForProcess=" + bookedForProcess +
                ", mine=" + mine +
                ", date=" + date +
                '}';
    }
}