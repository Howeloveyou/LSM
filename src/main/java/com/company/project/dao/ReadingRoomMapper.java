package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.ReadingRoom;
import org.springframework.stereotype.Repository;

@Repository("readingRoomMapper")
public interface ReadingRoomMapper extends Mapper<ReadingRoom> {
}