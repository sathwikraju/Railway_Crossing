package com.railway.models;

import javax.persistence.*;

@Entity
@Table(name = "railway_crossings")
public class RailwayCrossing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String landmark;
    private String trainSchedules;
    private String personInCharge;
    @Enumerated(EnumType.STRING)
    private Status status;

    // Status enumeration
    public enum Status {
        open,
        closed
    }

    // Constructors, getters, and setters (required for Hibernate)

    public RailwayCrossing() {
    }

    public RailwayCrossing(String name, String address, String landmark, String trainSchedules,
                           String personInCharge, Status status) {
        this.name = name;
        this.address = address;
        this.landmark = landmark;
        this.trainSchedules = trainSchedules;
        this.personInCharge = personInCharge;
        this.status = status;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getTrainSchedules() {
        return trainSchedules;
    }

    public void setTrainSchedules(String trainSchedules) {
        this.trainSchedules = trainSchedules;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
