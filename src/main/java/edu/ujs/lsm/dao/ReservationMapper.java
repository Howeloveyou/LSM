package edu.ujs.lsm.dao;

import edu.ujs.lsm.core.Mapper;
import edu.ujs.lsm.model.Reservation;
import org.springframework.stereotype.Repository;

@Repository("reservationMapper")
public interface ReservationMapper extends Mapper<Reservation> {
}