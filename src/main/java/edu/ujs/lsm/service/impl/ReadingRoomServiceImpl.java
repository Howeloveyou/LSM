package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.ReadingRoomMapper;
import edu.ujs.lsm.model.ReadingRoom;
import edu.ujs.lsm.service.ReadingRoomService;
import edu.ujs.lsm.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-3
 * @version V1.0
 */
@Service("readingRoomService")
@Transactional
public class ReadingRoomServiceImpl extends AbstractService<ReadingRoom> implements ReadingRoomService {
    @Resource
    private ReadingRoomMapper readingRoomMapper;

}
