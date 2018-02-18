package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Шахта.
 */
@Entity
@Table (name = "MINE")
public class Mine {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @Basic (optional = false)
    private String name;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Mine{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                '}';
    }
}