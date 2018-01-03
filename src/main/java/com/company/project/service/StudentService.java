package com.company.project.service;
import com.company.project.model.Student;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/01/02.
 */
public interface StudentService extends Service<Student> {

    public Student login (String sid,String psw);
}
