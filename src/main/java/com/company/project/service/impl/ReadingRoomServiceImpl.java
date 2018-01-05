package com.company.project.service.impl;

import com.company.project.dao.ReadingRoomMapper;
import com.company.project.model.ReadingRoom;
import com.company.project.service.ReadingRoomService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
@Service("readingRoomService")
@Transactional
public class ReadingRoomServiceImpl extends AbstractService<ReadingRoom> implements ReadingRoomService {
    @Resource
    private ReadingRoomMapper readingRoomMapper;

}
