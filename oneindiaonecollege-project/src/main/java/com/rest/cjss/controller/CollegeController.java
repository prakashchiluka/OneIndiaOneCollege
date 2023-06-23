package com.rest.cjss.controller;

import com.rest.cjss.entity.CourseEntity;
import com.rest.cjss.entity.FacultyEntity;
import com.rest.cjss.entity.StudentEntity;
import com.rest.cjss.entity.SubjectEntity;
import com.rest.cjss.exception.StudentNotAvailableException;
import com.rest.cjss.model.CourseModel;
import com.rest.cjss.model.FacultyModel;
import com.rest.cjss.model.StudentModel;
import com.rest.cjss.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CollegeController {

    @Autowired
    private CollegeService service;

    @PostMapping("/add-subject")
    public ResponseEntity<String> addSubject(@RequestBody SubjectEntity subject){
        service.addSubject(subject);
        return ResponseEntity.status(HttpStatus.OK).body("Subject added successfully");
    }

    @PostMapping("/add-faculty")
    public ResponseEntity<String> addFaculty(@RequestBody FacultyEntity faculty){
         service.addFaculty(faculty);
         return ResponseEntity.status(HttpStatus.OK).body("Faculty successfully registered ");
    }

    @PutMapping("/assign-subject-to-faculty/{subId}/{facId}")
    public ResponseEntity<String> assignSubjectToFaculty(@PathVariable(value = "subId") int subId, @PathVariable(value = "facId") int facId){
        service.assignSubjectToFaculty(subId, facId);
        return ResponseEntity.status(HttpStatus.OK).body("Faculty is registered to Subject");
    }

    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestBody CourseEntity course){
         service.addCourse(course);
         return ResponseEntity.status(HttpStatus.OK).body("Course successfully registered ");
    }

    @PutMapping("/addSubjectToCourse/{subId}/{cId}")
    public ResponseEntity<String> addSubjectToCourse(@PathVariable int subId, @PathVariable int cId){
        service.addSubjectToCourse(subId,cId);
        return ResponseEntity.status(HttpStatus.OK).body("Subject Successfully added to the given Course");
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentEntity student){
        service.addStudent(student);
        return  ResponseEntity.status(HttpStatus.OK).body("Student successfully registered ");
    }

    @PutMapping("/addCourseToStudent/{cId}/{sId}")
    public ResponseEntity<String> addCourseToStudent(@PathVariable int cId, @PathVariable int sId){
        service.addCourseToStudent(cId,sId);
        return  ResponseEntity.status(HttpStatus.OK).body("Student successfully registered in the Course");

    }


    @GetMapping("/getCoursesByStreamAndSubject/{stream}/{subName}")
    public ResponseEntity<List<CourseModel>> getCoursesByStreamAndSubject(@PathVariable String stream, @PathVariable String subName){
       List<CourseModel> courseModels = service.getCoursesByStreamAndSubject(stream,subName);
       return ResponseEntity.status(HttpStatus.OK).body(courseModels);
    }

    @GetMapping("/getStudentsByCourseSubName/{subName}")
    public ResponseEntity<List<StudentModel>>  getStudentsByCourseSubName(@PathVariable String subName){
        List<StudentModel> students=service.getStudentsByCourseSubName(subName);
            return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("/getCoursesByFacultyId/{id}")
    public ResponseEntity<FacultyModel> getCoursesByFacultyId(@PathVariable int id){
        FacultyModel facultyModel = service.getCoursesByFacultyId(id);
        return ResponseEntity.status(HttpStatus.OK).body(facultyModel);
    }

    @GetMapping("/getCoursesByStreamAndSubjectAndFacultyId/{stream}/{subName}/{facId}")
    public ResponseEntity<List<CourseModel>> getCoursesByStreamAndSubjectAndFacultyId(@PathVariable String stream,@PathVariable String subName,@PathVariable int facId){
      List<CourseModel> courseModels= service.getCoursesByStreamAndSubjectAndFacultyId(stream,subName,facId);
      return ResponseEntity.status(HttpStatus.OK).body(courseModels);
    }
}
