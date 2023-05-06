package com.School.Student.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.School.Student.dto.ChangePassword;
import com.School.Student.dto.StudentDTO;
import com.School.Student.exceptions.StudentExceptions;
import com.School.Student.service.StudentService;
/**
 * @author Venu
 *
 *  Create a student
 *  Get student marks
 *  View Student profile
 *  Update the student profile
 *  Change password
 */
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@GetMapping(value = "/{id}", produces = "application/json" )
	public ResponseEntity<HashMap<String, Integer>> viewReport(@PathVariable Integer id) throws StudentExceptions{
		HashMap<String, Integer> dto = service.viewReport(id);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
		
	@GetMapping(value="/profile/{id}", produces = "application/json")
	public ResponseEntity<StudentDTO> viewProfile(@PathVariable Integer id) throws StudentExceptions{
		StudentDTO dto = service.viewProfile(id);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@PostMapping(value = "/create",  consumes="application/json")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> create(@RequestBody StudentDTO dto) throws StudentExceptions{
		return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
	}
	
	@PostMapping(value = "/update",  consumes="application/json")
	public ResponseEntity<String> update(@RequestBody StudentDTO dto) throws StudentExceptions{
		return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
	}
	
	@PutMapping(value="/changePassword/{id}")
	public ResponseEntity<String> changePassword(@PathVariable Integer id, @RequestBody ChangePassword password) throws StudentExceptions{
		String msg = service.changePassword(id, password);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateMarks/{id}", consumes = "application/json")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> updateMarks(@PathVariable Integer id, @RequestBody HashMap<String, Integer> subjectsMarks ) throws StudentExceptions {
		
		String msg = service.updateMarks(id, subjectsMarks);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
