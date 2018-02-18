package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Содержит информацию о валовой перевозке минералов из ящика с отсортированными кристаллами в ячейку контейнера.
 */
@Entity
@Table (name = "HEAP_TRANSPORTATION")
public class HeapTransportation {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private ClassificationDetails classificationDetails;
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private ContainerCellLoading containerCellLoading;
    @Basic (optional = false)
    private Double volume;
    @Basic (optional = false)
    private LoadingProcessState loadingProcessState;

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

    public ClassificationDetails getClassificationDetails() {
        return classificationDetails;
    }

    public void setClassificationDetails(ClassificationDetails classificationDetails) {
        this.classificationDetails = classificationDetails;
    }

    public ContainerCellLoading getContainerCellLoading() {
        return containerCellLoading;
    }

    public void setContainerCellLoading(ContainerCellLoading containerCellLoading) {
        this.containerCellLoading = containerCellLoading;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public LoadingProcessState getLoadingProcessState() {
        return loadingProcessState;
    }

    public void setLoadingProcessState(LoadingProcessState loadingProcessState) {
        this.loadingProcessState = loadingProcessState;
    }
}