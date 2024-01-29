package com.School.Student.entity;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Integer studentId;
    
    @NotBlank(message = "{student.no_name}")
    private String name;
    
	@Email(message = "{student.no_email}")
	private String email;

	private Integer phone;
	
	private String password;
    private HashMap<String, Integer> subjectsMarks;
    private String grade;
    
}
