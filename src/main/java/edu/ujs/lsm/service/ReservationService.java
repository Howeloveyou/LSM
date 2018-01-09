package edu.ujs.lsm.service;
import edu.ujs.lsm.model.ReadingRoom;
import edu.ujs.lsm.model.Reservation;
import edu.ujs.lsm.core.Service;

import java.text.ParseException;
import java.util.List;


/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-4
 * @version V1.0
 */
public interface ReservationService extends Service<Reservation> {
    List<ReadingRoom> getRoomList(String date, String time) throws ParseException;

    boolean changeSurplus(Integer resid, String time);
}
