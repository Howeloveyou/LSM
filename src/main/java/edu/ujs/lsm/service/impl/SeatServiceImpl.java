package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.SeatMapper;
import edu.ujs.lsm.model.Seat;
import edu.ujs.lsm.service.SeatService;
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
 * @date 2018-1-9
 * @version V1.0
 */
@Service("seatService")
@Transactional
public class SeatServiceImpl extends AbstractService<Seat> implements SeatService {

    private final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

    @Resource
    private SeatMapper seatMapper;

    @Override
    public List<Seat> getSeatList(Integer rid, String date) throws ParseException {
        Seat seat = new Seat();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date relDate = format.parse(date);
        seat.setDate(new java.sql.Date(relDate.getTime()));
        seat.setRid(rid);
        //logger.info(seat.getDate().toString());
        //logger.info(seat.getRid().toString());
        List<Seat> list = seatMapper.select(seat);
        logger.info(list.toString());
        return list;
    }

    @Override
    public boolean changeState(Integer seid,String times) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Seat seat = seatMapper.selectByPrimaryKey(seid);
//        logger.info(Integer.toString(seat.getMorning()));
//        switch (time){
//            case "morning":
//                seat.setMorning(0);
//                break;
//            case "afternoon":
//                seat.setAfternoon(0);
//                break;
//            case "night":
//                seat.setNight(0);
//                break;
//        }
//        seatMapper.updateByPrimaryKey(seat);
        String time [] = StringUtils.split(times,",");
        Seat seat = seatMapper.selectByPrimaryKey(seid);
        Class<Seat> clazz = Seat.class;
        if (time.length == 1){
            String methodName0 = "get" + time[0].substring(0,1).toUpperCase() + time[0].substring(1);
            String methodName1 = "set" + time[0].substring(0,1).toUpperCase() + time[0].substring(1);
            Method method0 = clazz.getMethod(methodName0);
            Method method1 = clazz.getMethod(methodName1,Integer.class);
            int x = (Integer) method0.invoke(seat);
            if (x != 0){
                method1.invoke(seat,0);
                seatMapper.updateByPrimaryKey(seat);
                return true;
            }
        }else if (time.length == 2){
            String methodName0 = "get" + time[0].substring(0,1).toUpperCase() + time[0].substring(1);
            String methodName1 = "get" + time[1].substring(0,1).toUpperCase() + time[1].substring(1);
            String methodName2 = "set" + time[0].substring(0,1).toUpperCase() + time[0].substring(1);
            String methodName3 = "set" + time[1].substring(0,1).toUpperCase() + time[1].substring(1);
            Method method0 = clazz.getMethod(methodName0);
            Method method1 = clazz.getMethod(methodName1);
            Method method2 = clazz.getMethod(methodName2,Integer.class);
            Method method3 = clazz.getMethod(methodName3,Integer.class);
            int x = (Integer) method0.invoke(seat);
            int y = (Integer) method1.invoke(seat);
            if (x != 0 && y != 0){
                method2.invoke(seat,0);
                method3.invoke(seat,0);
                seatMapper.updateByPrimaryKey(seat);
                return true;
            }
        }else if (time.length == 3){
            int x = seat.getMorning();
            int y = seat.getAfternoon();
            int z = seat.getNight();
            if (x != 0 && y != 0 && z != 0){
                seat.setMorning(0);
                seat.setAfternoon(0);
                seat.setNight(0);
                seatMapper.updateByPrimaryKey(seat);
                return true;
            }

        }
        return false;
    }

    @Override
    public void cancelSeat(String date, String time, Seat seat) {

    }


}
