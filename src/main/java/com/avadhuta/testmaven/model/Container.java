package com.avadhuta.testmaven.model;

import com.avadhuta.testmaven.business.*;

import javax.persistence.*;

/**
 * Контейнер для минералов. Может быть использован для перевозки минералов множество раз.
 */
@Entity
@Table (name = "CONTAINER")
public class Container
        implements BookableResource {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Идентификатор (штрихкод) контейнера */
    @Basic (optional = false)
    private String containerBarCode;
    /** Тип контейнера */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private ContainerType containerType;

    // Участие в бизнес процессах /////////////////////////////////////////////////////////////////////////

    /** Состояние данного контейнера. */
    @Basic (optional = false)
    private ContainerState containerState;
    /** Отметка о том, какой именно процесс забронировал контейнер */
    @ManyToOne (fetch = FetchType.LAZY)
    private LoadingProcess bookedForProcess;

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

    public String getContainerBarCode() {
        return containerBarCode;
    }

    public void setContainerBarCode(String containerBarCode) {
        this.containerBarCode = containerBarCode;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }

    public ContainerState getContainerState() {
        return containerState;
    }

    public void setContainerState(ContainerState containerState) {
        this.containerState = containerState;
    }

    @Override
    public LoadingProcess getBookedForProcess() {
        return bookedForProcess;
    }

    @Override
    public void setBookedForProcess(LoadingProcess bookedForProcess) {
        this.bookedForProcess = bookedForProcess;
    }
}