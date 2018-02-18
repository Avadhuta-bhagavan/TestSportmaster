package com.avadhuta.testmaven.model;

import javax.persistence.*;
import java.util.*;

/**
 * Описание типа контейнера
 */
@Entity
@Table (name = "CONTAINER_TYPE")
public class ContainerType {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @Basic (optional = false)
    private String name;
    /** Описание имеющихся в контейнере ячеек */
    @OneToMany (fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "parent")
    private Set<ContainerCellDescription> details;
    /** Масса самого контейнера */
    private Double containerMass;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContainerCellDescription> getDetails() {
        return details;
    }

    public void setDetails(Set<ContainerCellDescription> details) {
        this.details = details;
    }

    public Double getContainerMass() {
        return containerMass;
    }

    public void setContainerMass(Double containerMass) {
        this.containerMass = containerMass;
    }
}