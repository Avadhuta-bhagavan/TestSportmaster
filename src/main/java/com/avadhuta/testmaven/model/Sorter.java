package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Сортировщик.
 */
@Entity
@Table (name = "SORTER")
public class Sorter {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Person person;
    private String numberOfWorkingPlace;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getNumberOfWorkingPlace() {
        return numberOfWorkingPlace;
    }

    public void setNumberOfWorkingPlace(String numberOfWorkingPlace) {
        this.numberOfWorkingPlace = numberOfWorkingPlace;
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
        return "Sorter{" +
                "id=" + id +
                ", version=" + version +
                ", person=" + person +
                ", numberOfWorkingPlace='" + numberOfWorkingPlace + '\'' +
                '}';
    }
}