package com.avadhuta.testmaven.model;

import javax.persistence.*;
import java.time.*;

/**
 * Работник.
 * Хранит личную информацию о сотруднике.
 */
@Entity
@Table (name = "PERSON")
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @Basic (optional = false)
    private String firstName;
    private String middleName;
    @Basic (optional = false)
    private String lastName;
    @Basic (optional = false)
    private LocalDate dateOfBirth;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
        return "Person{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}