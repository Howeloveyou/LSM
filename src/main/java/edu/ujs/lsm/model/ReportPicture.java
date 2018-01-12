package edu.ujs.lsm.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "report_picture")
public class ReportPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sid;

    private Date date;

    private String img;

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
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
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