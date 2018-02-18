package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Содержит информацию о состоянии конкретной ячейки контейнера на момент окончания операции.
 */
@Entity
@Table (name = "CELL_LOADING")
public class CellLoading {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    /** Процесс загрузки контейнера */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private LoadingProcess bookedForProcess;
    /** Операция загрузки контейнера */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private ContainerLoading containerLoading;

    /** Тип ячейки */
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private ContainerCellType cellType;
    /** Состояние ячейки после выполнения данной операции. */
    @Basic (optional = false)
    private CellState cellState;

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

    public ContainerLoading getContainerLoading() {
        return containerLoading;
    }

    public void setContainerLoading(ContainerLoading containerLoading) {
        this.containerLoading = containerLoading;
    }

    public ContainerCellType getCellType() {
        return cellType;
    }

    public void setCellType(ContainerCellType cellType) {
        this.cellType = cellType;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }
}