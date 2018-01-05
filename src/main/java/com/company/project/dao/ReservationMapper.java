package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Reservation;
import org.springframework.stereotype.Repository;

@Repository("reservationMapper")
public interface ReservationMapper extends Mapper<Reservation> {
}