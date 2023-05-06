package com.School.Teacher.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMsg> exceptionHandler(Exception exception){
		ErrorMsg  error = new ErrorMsg();
		error.setMsg(exception.getMessage());
		error.setCode(HttpStatus.BAD_GATEWAY.value());
		
		return new ResponseEntity<ErrorMsg>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(TeacherExceptions.class)
	public ResponseEntity<ErrorMsg> TeacherException(TeacherExceptions exception){
		ErrorMsg  error = new ErrorMsg();
		error.setMsg(exception.getMessage());
		error.setCode(HttpStatus.BAD_GATEWAY.value());
		
		return new ResponseEntity<ErrorMsg>(error, HttpStatus.OK);
	}
}
