package edu.ujs.lsm.model;

import java.sql.Date;
import javax.persistence.*;

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rid;

    @Column(name = "reservation_date")
    private Date reservationDate;

    private Integer morning;

    private Integer afternoon;

    private Integer night;

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
     * @return reservation_date
     */
    public Date getReservationDate() {
        return reservationDate;
    }

    /**
     * @param reservationDate
     */
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * @return moring
     */
    public Integer getMorning() {
        return morning;
    }

    /**
     * @param morning
     */
    public void setMorning(Integer morning) {
        this.morning = morning;
    }

    /**
     * @return afternoon
     */
    public Integer getAfternoon() {
        return afternoon;
    }

    /**
     * @param afternoon
     */
    public void setAfternoon(Integer afternoon) {
        this.afternoon = afternoon;
    }

    /**
     * @return night
     */
    public Integer getNight() {
        return night;
    }

    /**
     * @param night
     */
    public void setNight(Integer night) {
        this.night = night;
    }
}