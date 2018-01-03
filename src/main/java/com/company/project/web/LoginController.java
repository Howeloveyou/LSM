package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.model.Student;
import com.company.project.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/toLogin")
    public Object toLogin(
            @RequestParam String sid,
            @RequestParam String psw){

        Result result = new Result();
        Map<String,String> paramMap = new HashMap();
        if (StringUtils.isNotEmpty(sid) && !"".equals(sid)&&StringUtils.isNotEmpty(psw) && !"".equals(psw.trim())){
            Student student = studentService.login(sid,psw);
            if (student != null){
                paramMap.put("sname",student.getSname());
                paramMap.put("sid",sid);
                result.setCode(ResultCode.SUCCESS);
                result.setMessage("登录成功！");
                result.setData(paramMap);
                return result;
            }
        }
        logger.info("登录失败");
        result.setCode(ResultCode.FAIL);
        result.setMessage("账号或密码错误");
        return result;

    }

}
