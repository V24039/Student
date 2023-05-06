package com.School.Student.dto;

import java.util.HashMap;

import com.School.Student.entity.Student;

import lombok.Data;

@Data
public class StudentDTO {

	private Integer studentId;
	private HashMap<String, Integer> subjectsMarks;
	private String password;
	private String name;
	private Integer phone;
	private String grade;
	private String email;

	public StudentDTO entityToDto(Student student) {
		
		StudentDTO dto = new StudentDTO();
		dto.setGrade(student.getGrade());
		dto.setName(student.getName());
		dto.setPhone(student.getPhone());
		dto.setStudentId(student.getStudentId());
		dto.setSubjectsMarks(student.getSubjectsMarks());
		dto.setPassword(student.getPassword());
		dto.setEmail(student.getEmail());
		return dto;
	}

	public Student dtoToEntity(StudentDTO dto) {

		Student student = new Student();
		student.setName(dto.getName());
		student.setGrade(dto.getGrade());
		student.setPassword(dto.getPassword());
		student.setPhone(dto.getPhone());
		student.setEmail(dto.getEmail());
		
		return student;
	}
}
