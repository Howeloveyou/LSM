package edu.ujs.lsm.dao;

import edu.ujs.lsm.core.Mapper;
import edu.ujs.lsm.model.Student;
import org.springframework.stereotype.Repository;

@Repository("studentMapper")
public interface StudentMapper extends Mapper<Student> {
}