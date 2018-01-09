package edu.ujs.lsm.dao;

import edu.ujs.lsm.core.Mapper;
import edu.ujs.lsm.model.ReadingRoom;
import org.springframework.stereotype.Repository;

@Repository("readingRoomMapper")
public interface ReadingRoomMapper extends Mapper<ReadingRoom> {
}