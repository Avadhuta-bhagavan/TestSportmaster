package com.avadhuta.testmaven.model;

import javax.persistence.*;

/**
 * Бригадир
 */
@Entity
@Table (name = "TEAM_LEADER")
public class TeamLeader {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Person person;
    private String teamName;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
        return "TeamLeader{" +
                "id=" + id +
                ", version=" + version +
                ", person=" + person +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}