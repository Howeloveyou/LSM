package com.company.project.service.impl;

import com.company.project.dao.ReadingRoomMapper;
import com.company.project.dao.ReservationMapper;
import com.company.project.model.ReadingRoom;
import com.company.project.model.Reservation;
import com.company.project.service.ReservationService;
import com.company.project.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/05.
 */
@Service
@Transactional
public class ReservationServiceImpl extends AbstractService<Reservation> implements ReservationService {

    private final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Resource
    private ReservationMapper reservationMapper;

    @Resource
    private ReadingRoomMapper readingRoomMapper;

    @Override
    public List<ReadingRoom> getRoomList(String date, String time) throws ParseException {
        Reservation reservation = new Reservation();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date relDate = format.parse(date);
        reservation.setReservationDate(new java.sql.Date(relDate.getTime()));
        logger.info(relDate.toString());
        List<ReadingRoom> roomList = readingRoomMapper.selectAll();
        List<Reservation> revList = reservationMapper.select(reservation);
        logger.info(reservationMapper.selectAll().toString());
        logger.info(roomList.toString());
        logger.info(revList.toString());
        switch (time){
            case "moring":
                setMoring(roomList,revList);
                break;
            case "afternoon":
                setAfternoon(roomList,revList);
                break;
            case "night":
                setNight(roomList,revList);
                break;
        }
        return  roomList;
    }

    private void setMoring(List<ReadingRoom> roomList, List<Reservation> revList) {
        for (ReadingRoom room:roomList) {
            for (Reservation rev:revList) {
                if (room.getId() == rev.getRid()){
                    room.setSurplus(rev.getMoring());
                }
            }
        }
    }

    private void setAfternoon(List<ReadingRoom> roomList, List<Reservation> revList) {
        for (ReadingRoom room:roomList) {
            for (Reservation rev:revList) {
                if (room.getId() == rev.getRid()){
                    room.setSurplus(rev.getAfternoon());
                }
            }
        }
    }

    private void setNight(List<ReadingRoom> roomList, List<Reservation> revList) {
        for (ReadingRoom room:roomList) {
            for (Reservation rev:revList) {
                if (room.getId() == rev.getRid()){
                    room.setSurplus(rev.getNight());
                }
            }
        }
    }
}
