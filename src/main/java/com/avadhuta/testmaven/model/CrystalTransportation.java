package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table (name = "CRYSTAL_TRANSPORTATION")
public class CrystalTransportation {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private CrystalDetails crystalDetails;
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private ContainerCellLoading containerCellLoading;
    @Basic (optional = false)
    private LoadingProcessState loadingProcessState;

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

    public CrystalDetails getCrystalDetails() {
        return crystalDetails;
    }

    public void setCrystalDetails(CrystalDetails crystalDetails) {
        this.crystalDetails = crystalDetails;
    }

    public ContainerCellLoading getContainerCellLoading() {
        return containerCellLoading;
    }

    public void setContainerCellLoading(ContainerCellLoading containerCellLoading) {
        this.containerCellLoading = containerCellLoading;
    }

    public LoadingProcessState getLoadingProcessState() {
        return loadingProcessState;
    }

    public void setLoadingProcessState(LoadingProcessState loadingProcessState) {
        this.loadingProcessState = loadingProcessState;
    }
}