package edu.ujs.lsm.web;


import edu.ujs.lsm.core.Result;
import edu.ujs.lsm.core.ResultCode;
import edu.ujs.lsm.model.ReadingRoom;
import edu.ujs.lsm.model.Record;
import edu.ujs.lsm.model.Seat;
import edu.ujs.lsm.service.RecordService;
import edu.ujs.lsm.service.ReservationService;
import edu.ujs.lsm.service.SeatService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-9
 * @version V1.0
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController{

    @Resource
    ReservationService reservationService;

    @Resource
    SeatService seatService;

    @Resource
    RecordService recordService;

    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    /*
    *获取阅览室列表
    *
    */
    @RequestMapping("/getRoomList")
    public Object getRoomList(
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam(required=false,defaultValue="1") Integer page,
            @RequestParam(required=false,defaultValue="9") Integer pageSize
    ) throws Exception{
        logger.info(date.toString());
        logger.info(time);
        Result result = new Result();
        PageHelper.startPage(page,pageSize);
        List<ReadingRoom> list = reservationService.getRoomList(date,time);
        PageInfo<ReadingRoom> pageInfo = new PageInfo<>(list);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("查询成功！");
        result.setData(pageInfo);
        return result;
    }

    /*
    *根据阅览室ID和时间获取座位
    *
    */
    @RequestMapping("/getSeatList")
    public Object getSeatList(
            @RequestParam Integer rid,
            @RequestParam String date
    ) throws Exception{
        logger.info(rid.toString());
        logger.info(date);
        Result result = new Result();
        List<Seat> list = seatService.getSeatList(rid,date);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("查询成功!");
        result.setData(list);
        return result;
    }

    /*
    * 预约逻辑
    *
    * @sid 学号ID
    * @rid 阅览室ID
    * @seid 座位ID
    * @resid 预定ID
    */
    @RequestMapping("/toReservation")
    public Object toReservation(
            @RequestParam String sid,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam Integer rid,
            @RequestParam Integer seid,
            @RequestParam Integer resid
    )throws Exception{
        Result result = new Result();
        Record record = new Record();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date relDate = format.parse(date);
        boolean flag = reservationService.changeSurplus(resid,time);
        logger.info(Boolean.toString(flag));
        if (flag){
            seatService.changeState(seid,time);
            record.setDate(new java.sql.Date(relDate.getTime()));
            record.setSid(sid);
            record.setTime(time);
            record.setRid(rid);
            record.setSeat(seid);
            record.setMark(1);
            recordService.save(record);
            result.setCode(ResultCode.SUCCESS);
            result.setMessage("预约成功!");
            return result;
        }
        result.setCode(ResultCode.FAIL);
        result.setMessage("预约失败，请重新预约~");
        return result;
    }




}
