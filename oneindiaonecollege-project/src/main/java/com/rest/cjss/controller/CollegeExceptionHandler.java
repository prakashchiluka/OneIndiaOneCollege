package com.rest.cjss.controller;

import com.rest.cjss.exception.CourseNotFoundException;
import com.rest.cjss.exception.FacultyNotFoundException;
import com.rest.cjss.exception.StudentNotAvailableException;
import com.rest.cjss.exception.SubjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CollegeExceptionHandler {

    @ExceptionHandler(StudentNotAvailableException.class)
    public ResponseEntity<String> studentNotAvailableExceptionHandler(StudentNotAvailableException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> courseNotFoundException(CourseNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(FacultyNotFoundException.class)
    public ResponseEntity<String> facultyNotFoundException(FacultyNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<String> subjectNotFoundException(SubjectNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
