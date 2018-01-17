package edu.ujs.lsm.web;

import edu.ujs.lsm.core.Result;
import edu.ujs.lsm.core.ResultCode;
import edu.ujs.lsm.dao.RecordMapper;
import edu.ujs.lsm.model.Record;
import edu.ujs.lsm.service.RecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Howe
 * @version V1.0
 * @Description: TODO
 * @date 2018-1-11
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Resource
    RecordService recordService;

    @RequestMapping("/getRecord")
    public Object getRecord(@RequestParam String sid){
        Result result = new Result();
        List<Map> params = recordService.getRecord(sid);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("查询成功！");
        result.setData(params);
        return result;
    }

    @RequestMapping("/getSignInfo")
    public Object getSignInfo(@RequestParam String sid){
        Result result = new Result();
        Map<String,Object> params = recordService.getSignInfo(sid);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("查询成功！");
        result.setData(params);
        return result;
    }
}
