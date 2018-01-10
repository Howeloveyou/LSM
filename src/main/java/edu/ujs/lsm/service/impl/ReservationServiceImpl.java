package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.ReadingRoomMapper;
import edu.ujs.lsm.dao.ReservationMapper;
import edu.ujs.lsm.dao.SeatMapper;
import edu.ujs.lsm.model.ReadingRoom;
import edu.ujs.lsm.model.Reservation;
import edu.ujs.lsm.model.Seat;
import edu.ujs.lsm.service.ReservationService;
import edu.ujs.lsm.core.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-4
 * @version V1.0
 */
@Service
@Transactional
public class ReservationServiceImpl extends AbstractService<Reservation> implements ReservationService {

    private final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Resource
    private ReservationMapper reservationMapper;

    @Resource
    private ReadingRoomMapper readingRoomMapper;

    @Resource
    private SeatMapper seatMapper;

    @Override
    public List<ReadingRoom> getRoomList(String date, String times) throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Reservation reservation = new Reservation();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
//        Date relDate = format.parse(date);
//        reservation.setReservationDate(new java.sql.Date(relDate.getTime()));
//        logger.info(relDate.toString());
//        List<ReadingRoom> roomList = readingRoomMapper.selectAll();
//        List<Reservation> revList = reservationMapper.select(reservation);
//        logger.info(reservationMapper.selectAll().toString());
//        logger.info(roomList.toString());
//        logger.info(revList.toString());
//        logger.info(times);
//        switch (times){
//            case "morning":
//                logger.info("switch do...");
//                setMorning(roomList,revList);
//                break;
//            case "afternoon":
//                setAfternoon(roomList,revList);
//                break;
//            case "night":
//                setNight(roomList,revList);
//                break;
//        }
//        return  roomList;

        String [] time  = StringUtils.split(times,',');
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date relDate = format.parse(date);
        Seat seat = new Seat();
        List<ReadingRoom> roomList = readingRoomMapper.selectAll();
        if (time.length == 1){
            Class<Seat> clazz = Seat.class;
            String time0 = time[0];
            time0 = "get" + time0.substring(0,1).toUpperCase() + time0.substring(1);
            Method method0 = clazz.getMethod(time0);
            for (ReadingRoom room : roomList){
                seat.setDate(new java.sql.Date(relDate.getTime()));
                seat.setRid(room.getId());
                List<Seat> seatList = seatMapper.select(seat);
                int count = seatList.size();
                for (Seat s : seatList){
                    int x = (Integer) method0.invoke(s);
                    if (x != 0){
                        continue;
                    }
                    count --;
                }
                room.setSurplus(count);
            }
            return  roomList;
        }else if(time.length == 2){
            Class<Seat> clazz = Seat.class;
            String time1 = time[0];
            String time2 = time[1];
            time1 = "get" + time1.substring(0,1).toUpperCase() + time1.substring(1);
            time2 = "get" + time2.substring(0,1).toUpperCase() + time2.substring(1);
            Method method1 = clazz.getMethod(time1);
            Method method2 = clazz.getMethod(time2);
            for (ReadingRoom room : roomList) {
                seat.setDate(new java.sql.Date(relDate.getTime()));
                seat.setRid(room.getId());
                List<Seat> seatList = seatMapper.select(seat);
                int count = seatList.size();
                for (Seat s : seatList){
                    int x = (Integer) method1.invoke(s);
                    int y = (Integer) method2.invoke(s);
                   if (x != 0 && y != 0){
                       continue;
                   }
                   count --;
                }
                room.setSurplus(count);
            }

            return roomList;

        }else if(time.length == 3){
            for (ReadingRoom room : roomList){
                seat.setDate(new java.sql.Date(relDate.getTime()));
                seat.setRid(room.getId());
                List<Seat> seatList = seatMapper.select(seat);
                int count = seatList.size();
                for (Seat s : seatList){
                    int x = s.getMorning();
                    int y = s.getAfternoon();
                    int z = s.getNight();
                    if (x != 0 && y != 0 && z != 0){
                        continue;
                    }
                    count --;
                }
                room.setSurplus(count);
            }

            return roomList;
        }else
            return null;
    }



    @Override
    public boolean changeSurplus(Integer resid, String time) {
        Reservation reservation = reservationMapper.selectByPrimaryKey(1);
        logger.info(reservation.toString());
        logger.info(Integer.toString(reservation.getMorning()));
        boolean flag = false;
        if(reservation != null){
            switch (time){
                case "morning":
                    flag = setMorningSurplus(reservation);
                    break;
                case "afternoon":
                    flag = setAfternoonSurplus(reservation);
                    break;
                case "night":
                    flag = setNightSurplus(reservation);
                    break;
            }
        }
        return flag;
    }

    private boolean setMorningSurplus(Reservation reservation) {
        int surplus = reservation.getMorning();
        logger.info(Integer.toString(surplus));
        if (surplus>0){
            reservation.setMorning(--surplus);
            reservationMapper.updateByPrimaryKey(reservation);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean setAfternoonSurplus(Reservation reservation) {
        int surplus = reservation.getAfternoon();
        if (surplus>0){
            reservation.setAfternoon(--surplus);
            reservationMapper.updateByPrimaryKey(reservation);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean setNightSurplus(Reservation reservation) {
        int surplus = reservation.getNight();
        if (surplus>0){
            reservation.setNight(--surplus);
            reservationMapper.updateByPrimaryKey(reservation);
            return true;
        }
        else {
            return false;
        }
    }


    private void setMorning(List<ReadingRoom> roomList, List<Reservation> revList) {
        logger.info("do...");
        for (ReadingRoom room:roomList) {
            logger.info(room.getId().toString());
            for (Reservation rev:revList) {

                logger.info(rev.getRid().toString());
                if (room.getId() == rev.getRid()){
                    room.setSurplus(rev.getMorning());
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
