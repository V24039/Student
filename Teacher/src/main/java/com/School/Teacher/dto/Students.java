package com.School.Teacher.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class Students {
	
	private Integer studentId;
	private HashMap<String, Integer> subjectMarks;
}
