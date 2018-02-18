package com.avadhuta.testmaven.model;

import javax.persistence.*;
import java.time.*;

/**
 * Описание процесса загрузки контейнеров. Используется для контроля доступности ресурсов.
 */
@Entity
@Table (name = "LOADING_PROCESS")
public class LoadingProcess {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Дата и время проведения работ */
    @Basic (optional = false)
    private LocalDateTime date;
    /** Текущее состояние процесса загрузки контейнеров */
    @Basic (optional = false)
    private LoadingProcessState state;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LoadingProcessState getState() {
        return state;
    }

    public void setState(LoadingProcessState state) {
        this.state = state;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "LoadingProcess{" +
                "id=" + id +
                ", version=" + version +
                ", date=" + date +
                ", state=" + state +
                '}';
    }
}