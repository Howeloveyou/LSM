package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.StudentMapper;
import edu.ujs.lsm.model.Student;
import edu.ujs.lsm.service.StudentService;
import edu.ujs.lsm.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-4
 * @version V1.0
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
        if (list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
