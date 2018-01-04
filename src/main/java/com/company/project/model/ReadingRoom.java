package com.company.project.model;

import javax.persistence.*;

public class ReadingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String floor;

    private String room;

    private Integer surplus;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return floor
     */
    public String getFloor() {
        return floor;
    }

    /**
     * @param floor
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * @return room
     */
    public String getRoom() {
        return room;
    }

    /**
     * @param room
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * @return surplus
     */
    public Integer getSurplus() {
        return surplus;
    }

    /**
     * @param surplus
     */
    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }
}