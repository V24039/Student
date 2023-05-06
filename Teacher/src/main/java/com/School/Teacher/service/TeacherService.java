package com.School.Teacher.service;

import com.School.Teacher.dto.ChangePassword;
import com.School.Teacher.dto.TeacherDTO;
import com.School.Teacher.utilities.TeacherExceptions;

import java.util.HashMap;
import java.util.List;

public interface TeacherService {
	
	TeacherDTO viewProfile(Integer id) throws TeacherExceptions;

	String create(TeacherDTO dto) throws TeacherExceptions;

	String update(TeacherDTO dto) throws TeacherExceptions;

	String changePassword(Integer id, ChangePassword password) throws TeacherExceptions;

	String addStudent(Integer id, List<Integer> studentsId) throws TeacherExceptions;

	String updateMarks(Integer id, HashMap<String, Integer> subjectsMarks) throws TeacherExceptions, Exception;
}
