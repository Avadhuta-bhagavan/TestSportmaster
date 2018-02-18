package com.avadhuta.testmaven.model;

import com.avadhuta.testmaven.business.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

/**
 * Отчёт о результатах работы сортировщика.
 */
@Entity
@Table (name = "CLASSIFICATION_REPORT")
public class ClassificationReport
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
    /** Сортировщик */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Sorter sorter;
    /** Смена, во время которой была добыта порода, и тип кристаллов */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private ShiftOutput shiftOutput;
    /** Результаты сортировки */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "parent")
    private Set<ClassificationDetails> details;
    /** Масса отходов при сортировке */
    @Basic (optional = false)
    private Double writeOffMass;
    /** Короткий комментарий сортировщика */
    private String comment;

    // Рассчитываемые поля ////////////////////////////////////////////////////////////////////////////////

    /** Общая масса отсортированных минералов */
    @Basic (optional = false)
    private Double totalClassifiedOutput;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public Set<ClassificationDetails> getDetails() {
        return details;
    }

    public void setDetails(Set<ClassificationDetails> details) {
        this.details = details;
    }

    public ShiftOutput getShiftOutput() {
        return shiftOutput;
    }

    public void setShiftOutput(ShiftOutput shiftOutput) {
        this.shiftOutput = shiftOutput;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Double getTotalClassifiedOutput() {
        return totalClassifiedOutput;
    }

    public void setTotalClassifiedOutput(Double totalClassifiedOutput) {
        this.totalClassifiedOutput = totalClassifiedOutput;
    }

    public Double getWriteOffMass() {
        return writeOffMass;
    }

    public void setWriteOffMass(Double writeOffMass) {
        this.writeOffMass = writeOffMass;
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
        return "ClassificationReport{" +
                "id=" + id +
                ", version=" + version +
                ", needChainedRecalculation=" + needChainedRecalculation +
                ", date=" + date +
                ", sorter=" + sorter +
                ", shiftOutput=" + shiftOutput +
                ", details=" + details +
                ", writeOffMass=" + writeOffMass +
                ", comment='" + comment + '\'' +
                ", totalClassifiedOutput=" + totalClassifiedOutput +
                '}';
    }
}