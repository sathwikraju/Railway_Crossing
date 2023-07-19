package com.railway.models;

import javax.persistence.*;

@Entity
@Table(name = "favorite_crossings")
public class FavoriteCrossing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "crossing_id")
    private RailwayCrossing railwayCrossing;

    @Column(name = "user_id")
    private int userId;

    // Constructors

    public FavoriteCrossing() {
    }

    public FavoriteCrossing(RailwayCrossing railwayCrossing, int userId) {
        this.railwayCrossing = railwayCrossing;
        this.userId = userId;
    }

    // Getters and setters (required for Hibernate)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RailwayCrossing getRailwayCrossing() {
        return railwayCrossing;
    }

    public void setRailwayCrossing(RailwayCrossing railwayCrossing) {
        this.railwayCrossing = railwayCrossing;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
