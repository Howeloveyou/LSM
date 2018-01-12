package edu.ujs.lsm.service;
import edu.ujs.lsm.model.Student;
import edu.ujs.lsm.core.Service;

import java.text.ParseException;


/**
 * Created by CodeGenerator on 2018/01/02.
 */
public interface StudentService extends Service<Student> {
    Student login (String sid, String psw);

    int singIn(String sid, Integer rid, String seNum) throws ParseException;
}
