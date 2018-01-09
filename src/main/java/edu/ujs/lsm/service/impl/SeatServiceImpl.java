package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.SeatMapper;
import edu.ujs.lsm.model.Seat;
import edu.ujs.lsm.service.SeatService;
import edu.ujs.lsm.core.AbstractService;
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
    public void changeState(Integer seid,String time) {
        Seat seat = seatMapper.selectByPrimaryKey(seid);
        logger.info(Integer.toString(seat.getMorning()));
        switch (time){
            case "morning":
                seat.setMorning(0);
                break;
            case "afternoon":
                seat.setAfternoon(0);
                break;
            case "night":
                seat.setNight(0);
                break;
        }
        seatMapper.updateByPrimaryKey(seat);
    }

}
