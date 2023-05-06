package com.School.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.School.Student.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>  {

}
