package edu.ujs.lsm.web;

import edu.ujs.lsm.core.Result;
import edu.ujs.lsm.core.ResultCode;
import edu.ujs.lsm.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * @author Howe
 * @version V1.0
 * @Description: TODO
 * @date 2018-1-11
 */
@RestController
@RequestMapping("/api")
public class RESTfulController {

    @Resource
    private StudentService studentService;



    /*
    *
    * @sid 学号
    * @rid 阅览室ID
    * @seNum 座位号
    */
    @RequestMapping("/signIn")
    public Object signIn(
            @RequestParam String sid,
            @RequestParam Integer rid,
            @RequestParam String seNum
    ) throws ParseException {
        Result result = new Result();
        boolean flag = studentService.singIn(sid,rid,seNum);
        if (flag){
            result.setCode(ResultCode.SUCCESS);
            result.setMessage("签到成功！");
            return result;
        }
        result.setCode(ResultCode.FAIL);
        result.setMessage("签到失败！");
        return result;
    }

}
