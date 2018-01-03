package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Student;
import org.springframework.stereotype.Repository;

@Repository("studentMapper")
public interface StudentMapper extends Mapper<Student> {
}