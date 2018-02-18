package com.avadhuta.testmaven.model;

import com.avadhuta.testmaven.business.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

/**
 * Отчёт о работе оценщика.
 */
@Entity
@Table (name = "APPRAISE_REPORT")
public class AppraiseReport
        implements CalculableResource {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Данный объект был изменён и требуется учесть эти изменения где-то ещё. */
    @Basic (optional = false)
    private boolean needChainedRecalculation;
    /** Дата проведения работ */
    @Basic (optional = false)
    private LocalDate date;
    /** Оценщик */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Appraiser appraiser;
    /** Результат работы сортировщика, над которым работал оценщик */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private ClassificationDetails classificationDetails;
    /** Результаты оценки */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "appraiseReport")
    private Set<CrystalDetails> details;
    /** Масса отходов при оценке */
    @Basic (optional = false)
    private Double writeOffMass;

    // Рассчитываемые поля ////////////////////////////////////////////////////////////////////////////////

    /** Общая масса оценённых кристаллов */
    @Basic (optional = false)
    private Double appraisedOutput;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Appraiser getAppraiser() {
        return appraiser;
    }

    public void setAppraiser(Appraiser appraiser) {
        this.appraiser = appraiser;
    }

    public ClassificationDetails getClassificationDetails() {
        return classificationDetails;
    }

    public void setClassificationDetails(ClassificationDetails classificationDetails) {
        this.classificationDetails = classificationDetails;
    }

    public Set<CrystalDetails> getDetails() {
        return details;
    }

    public void setDetails(Set<CrystalDetails> details) {
        this.details = details;
    }

    public Double getAppraisedOutput() {
        return appraisedOutput;
    }

    public void setAppraisedOutput(Double appraisedOutput) {
        this.appraisedOutput = appraisedOutput;
    }

    public Double getWriteOffMass() {
        return writeOffMass;
    }

    public void setWriteOffMass(Double writeOffMass) {
        this.writeOffMass = writeOffMass;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isNeedChainedRecalculation() {
        return needChainedRecalculation;
    }

    public void setNeedChainedRecalculation(boolean needChainedRecalculation) {
        this.needChainedRecalculation = needChainedRecalculation;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "AppraiseReport{" +
                "id=" + id +
                ", version=" + version +
                ", needChainedRecalculation=" + needChainedRecalculation +
                ", date=" + date +
                ", appraiser=" + appraiser +
                ", classificationDetails=" + classificationDetails +
                ", details=" + details +
                ", writeOffMass=" + writeOffMass +
                ", appraisedOutput=" + appraisedOutput +
                '}';
    }
}