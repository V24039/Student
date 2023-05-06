package com.School.Teacher.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

	@Id
	@GeneratedValue
	private Integer teacherId;
	
	@NotBlank(message = "{teacher.no_name}")	
	private String teacherName;
	
	@Email(message = "{teacher.no_email}")	
	private String email;
	
	@Pattern(regexp = "[0-9]{10}" , message = "{teacher.no_phone}")
	private String phone;
	
	private String password;
	
	@ElementCollection
	private List<Integer> studentsId = new ArrayList<Integer>();
	
}
