package com.School.Teacher.controller;

import com.School.Teacher.dto.ChangePassword;
import com.School.Teacher.dto.TeacherDTO;
import com.School.Teacher.service.TeacherService;
import com.School.Teacher.utilities.TeacherExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Venu
 *
 *  Create a Teacher acc
 *  Get Teacher profile
 *  Add Student profile
 *  Update the teacher profile
 *  Change password
 *  Update student marks
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
		
	@PostMapping(value = "/create",  consumes="application/json")
	public ResponseEntity<String> create(@RequestBody TeacherDTO dto) throws TeacherExceptions{
		return new ResponseEntity<>(teacherService.create(dto), HttpStatus.OK);
	}
	
	@GetMapping(value="/profile/{id}", produces = "application/json")
	public ResponseEntity<TeacherDTO> viewProfile(@PathVariable Integer id) throws TeacherExceptions{
		TeacherDTO dto = teacherService.viewProfile(id);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@PostMapping(value = "/update",  consumes="application/json")
	public ResponseEntity<String> update(@RequestBody TeacherDTO dto) throws TeacherExceptions{
		return new ResponseEntity<>(teacherService.update(dto), HttpStatus.OK);
	}
	
	@PutMapping(value="/changePassword/{id}", consumes="application/json")
	public ResponseEntity<String> changePassword(@PathVariable Integer id, @RequestBody ChangePassword password) throws TeacherExceptions{
		String msg = teacherService.changePassword(id, password);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}

	@PutMapping(value="/addStudent/{id}")
	public ResponseEntity<String> addStudent(@PathVariable Integer id, @RequestBody TeacherDTO teacherDTO) throws TeacherExceptions{
		List<Integer> studentsId = teacherDTO.getStudentsId();
		String msg = teacherService.addStudent(id,studentsId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateMarks/{id}", consumes = "application/json")
	public ResponseEntity<String> updateMarks(@PathVariable Integer id, @RequestBody HashMap<String, Integer> subjectsMarks ) throws Exception{
		
		String msg = teacherService.updateMarks(id, subjectsMarks);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
