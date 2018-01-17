package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.ReadingRoomMapper;
import edu.ujs.lsm.dao.RecordMapper;
import edu.ujs.lsm.dao.SeatMapper;
import edu.ujs.lsm.model.ReadingRoom;
import edu.ujs.lsm.model.Record;
import edu.ujs.lsm.model.Seat;
import edu.ujs.lsm.service.RecordService;
import edu.ujs.lsm.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);

    /*
     *@ mark(状态)0 入座 1 预约 2 超时 3 取消
     *
     */
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
                //params.put("readingRoom",readingRoom);
                Seat seat = seatMapper.selectByPrimaryKey(r.getSeat());
                String point = readingRoom.getFloor()+"F  "+readingRoom.getRoom()+
                        seat.getNumber()+"号座位";
                params.put("point",point);
                //params.put("seatNum",seat.getNumber());
                params.put("date",r.getDate().toString());
                logger.info(r.getDate().toString());
                logger.info(format.format(r.getDate()));
                //params.put("times",r.getTime());
                params.put("state",checkState(r.getMark()));
                params.put("mark",r.getMark());
                result.add(params);
            }
        }

        return result;
    }

    @Override
    public Map<String, Object> getSignInfo(String sid) {
        Map<String,Object> params = new HashMap<>();
        Record record = new Record();
        record.setSid(sid);
        record.setMark(1);
        List<Record> list = recordMapper.select(record);
        if (list != null && list.size()>0){
            record = list.get(0);
        }
        ReadingRoom readingRoom = readingRoomMapper.selectByPrimaryKey(record.getRid());
        //params.put("readingRoom",readingRoom);
        Seat seat = seatMapper.selectByPrimaryKey(record.getSeat());
        String point = readingRoom.getFloor()+"F  "+readingRoom.getRoom()+
                seat.getNumber()+"号座位";
        params.put("point",point);
        params.put("date",record.getDate().toString());
        return params;
    }

    private String checkState(Integer mark) {
        String reStr = "";
        switch (mark){
            case 0:
                reStr = "已入座";
                break;
            case 1:
                reStr = "已预定";
                break;
            case 2:
                reStr = "超时未签到";
                break;
            case 3:
                reStr = "已取消";
                break;
        }
        return reStr;
    }
}
