package com.avadhuta.testmaven.model;

import com.avadhuta.testmaven.business.*;

import javax.persistence.*;
import java.util.*;

/**
 * Информация об операции загрузки контейнера минералами.
 */
@Entity
@Table (name = "CONTAINER_LOADING")
public class ContainerLoading
        implements CalculableResource {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Данный объект был изменён и требуется учесть эти изменения где-то ещё. */
    @Basic (optional = false)
    private boolean needChainedRecalculation;
    /** Процесс загрузки контейнера */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private LoadingProcess bookedForProcess;
    /** Контейнер */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Container container;
    /** Состояние контейнера после выполнения данной операции. */
    @Basic (optional = false)
    private ContainerState containerState;
    /** Описание использования конкретных ячеек контейнера */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "parent")
    private Set<ContainerCellLoading> details;

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

    public LoadingProcess getBookedForProcess() {
        return bookedForProcess;
    }

    public void setBookedForProcess(LoadingProcess bookedForProcess) {
        this.bookedForProcess = bookedForProcess;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public ContainerState getContainerState() {
        return containerState;
    }

    public void setContainerState(ContainerState containerState) {
        this.containerState = containerState;
    }

    @Override
    public boolean isNeedChainedRecalculation() {
        return needChainedRecalculation;
    }

    @Override
    public void setNeedChainedRecalculation(boolean needChainedRecalculation) {
        this.needChainedRecalculation = needChainedRecalculation;
    }

    public Set<ContainerCellLoading> getDetails() {
        return details;
    }

    public void setDetails(Set<ContainerCellLoading> details) {
        this.details = details;
    }
}