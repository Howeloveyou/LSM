package edu.ujs.lsm.model;

import java.sql.Date;
import javax.persistence.*;

public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sid;

    private Integer rid;

    private Date date;

    private String time;

    private Integer seat;

    private Integer mark;

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
     * @return sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return rid
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return seat
     */
    public Integer getSeat() {
        return seat;
    }

    /**
     * @param seat
     */
    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    /**
     * @return mark
     */
    public Integer getMark() {
        return mark;
    }

    /**
     * @param mark
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }
}