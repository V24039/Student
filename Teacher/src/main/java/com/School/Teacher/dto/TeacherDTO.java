package com.School.Teacher.dto;

import com.School.Teacher.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
	
	private Integer teacherId;
	private String teacherName;
	private String email;
	private String phone;
	private String password;
	private List<Integer> studentsId = new ArrayList<Integer>();
	
	public TeacherDTO entityToDto(Teacher teacher){
		TeacherDTO dto = new TeacherDTO();
		BeanUtils.copyProperties(teacher, dto);
		return dto;
	}

	public Teacher dtoToEntity(TeacherDTO dto){
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(dto,teacher);
		return teacher;
	}
}