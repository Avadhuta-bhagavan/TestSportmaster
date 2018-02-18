package com.avadhuta.testmaven.model;

import com.avadhuta.testmaven.business.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

/**
 * Смена. Заполняется бригадиром смены по её окончании.
 */
@Entity
@Table (name = "SHIFT")
public class Shift
        implements CalculableResource {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Данный объект был изменён и требуется учесть эти изменения где-то ещё. */
    @Basic (optional = false)
    private boolean needChainedRecalculation;
    /** Шахта */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Mine mine;
    /** Дата проведения работ */
    @Basic (optional = false)
    private LocalDate date;
    /** Смена */
    @Basic (optional = false)
    private ShiftType shiftType;
    /** Бригадир */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private TeamLeader teamLeader;
    /** Результаты работы бригады за смену */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "parent")
    private Set<ShiftOutput> details;
    /** Короткий комментарий бригадира */
    private String comment;

    // Рассчитываемые поля ////////////////////////////////////////////////////////////////////////////////

    /** Масса всей добытой за смену породы */
    @Basic (optional = false)
    private Double totalShiftOutput;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TeamLeader getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(TeamLeader teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Set<ShiftOutput> getDetails() {
        return details;
    }

    public void setDetails(Set<ShiftOutput> details) {
        this.details = details;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Double getTotalShiftOutput() {
        return totalShiftOutput;
    }

    public void setTotalShiftOutput(Double totalShiftOutput) {
        this.totalShiftOutput = totalShiftOutput;
    }

    @Override
    public boolean isNeedChainedRecalculation() {
        return needChainedRecalculation;
    }

    @Override
    public void setNeedChainedRecalculation(boolean needChainedRecalculation) {
        this.needChainedRecalculation = needChainedRecalculation;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", version=" + version +
                ", needChainedRecalculation=" + needChainedRecalculation +
                ", mine=" + mine +
                ", date=" + date +
                ", shiftType=" + shiftType +
                ", teamLeader=" + teamLeader +
                ", details=" + details +
                ", comment='" + comment + '\'' +
                ", totalShiftOutput=" + totalShiftOutput +
                '}';
    }
}