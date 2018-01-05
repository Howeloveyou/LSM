package com.company.project.service;
import com.company.project.model.ReadingRoom;
import com.company.project.model.Reservation;
import com.company.project.core.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/05.
 */
public interface ReservationService extends Service<Reservation> {
    List<ReadingRoom> getRoomList(String date, String time) throws ParseException;
}
