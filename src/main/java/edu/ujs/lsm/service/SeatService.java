package edu.ujs.lsm.service;
import edu.ujs.lsm.model.Seat;
import edu.ujs.lsm.core.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;


/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-9
 * @version V1.0
 */
public interface SeatService extends Service<Seat> {

    List<Seat> getSeatList(Integer rid, String date, String times) throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    boolean changeState(Integer seid, String time) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    void cancelSeat(String times, Integer seid) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
