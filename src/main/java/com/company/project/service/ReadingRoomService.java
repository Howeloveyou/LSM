package com.company.project.service;
import com.company.project.model.ReadingRoom;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
public interface ReadingRoomService extends Service<ReadingRoom> {

    List<ReadingRoom> getRoomList();
}
