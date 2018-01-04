package com.company.project.web;


import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.model.ReadingRoom;
import com.company.project.service.ReadingRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController{

    @Resource
    ReadingRoomService readingRoomService;

    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @RequestMapping("/getRoomList")
    public Object getRoomList() throws Exception{
        Result result = new Result();
        List<ReadingRoom>list = readingRoomService.getRoomList();
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("查询成功！");
        result.setData(list);
        return result;
    }
}
