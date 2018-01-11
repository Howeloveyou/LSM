package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.ReadingRoomMapper;
import edu.ujs.lsm.dao.RecordMapper;
import edu.ujs.lsm.dao.SeatMapper;
import edu.ujs.lsm.model.ReadingRoom;
import edu.ujs.lsm.model.Record;
import edu.ujs.lsm.model.Seat;
import edu.ujs.lsm.service.RecordService;
import edu.ujs.lsm.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-9
 * @version V1.0
 */
@Service("recordService")
@Transactional
public class RecordServiceImpl extends AbstractService<Record> implements RecordService {
    @Resource
    private RecordMapper recordMapper;

    @Resource
    private ReadingRoomMapper readingRoomMapper;

    @Resource
    private SeatMapper seatMapper;

    @Override
    public List<Record> findRecord(Record record) {
        return recordMapper.select(record);
    }

    @Override
    public List<Map> getRecord(String sid) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        List<Map> result = new ArrayList<>();
        Record record = new Record();
        record.setSid(sid);
        List<Record> recordList = recordMapper.select(record);
        if (recordList != null && recordList.size() > 0){
            for (Record r : recordList){
                Map<String,Object> params = new HashMap<>();
                ReadingRoom readingRoom = readingRoomMapper.selectByPrimaryKey(r.getRid());
                params.put("readingRoom",readingRoom);
                Seat seat = seatMapper.selectByPrimaryKey(r.getSeat());
                params.put("seatNum",seat.getNumber());
                params.put("date",format.format(r.getDate()));
                params.put("times",r.getTime());
                params.put("state",r.getMark());
                result.add(params);
            }
        }

        return result;
    }
}
