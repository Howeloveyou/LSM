package com.company.project.service.impl;

import com.company.project.dao.StudentMapper;
import com.company.project.model.Student;
import com.company.project.service.StudentService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/02.
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl extends AbstractService<Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    public Student login (String sid,String psw){
        Student student = new Student();
        student.setSid(sid);
        student.setPassword(psw);
        List<Student> list = studentMapper.select(student);
        if (list != null){
            return list.get(0);
        }
        return null;
    }
}
