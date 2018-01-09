package edu.ujs.lsm.dao;

import edu.ujs.lsm.core.Mapper;
import edu.ujs.lsm.model.Seat;
import org.springframework.stereotype.Repository;

@Repository("seatMapper")
public interface SeatMapper extends Mapper<Seat> {
}