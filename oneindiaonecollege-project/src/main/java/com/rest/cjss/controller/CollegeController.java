package com.rest.cjss.controller;

import com.rest.cjss.entity.CourseEntity;
import com.rest.cjss.entity.FacultyEntity;
import com.rest.cjss.entity.StudentEntity;
import com.rest.cjss.entity.SubjectEntity;
import com.rest.cjss.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollegeController {

    @Autowired
    private CollegeService service;

    @PostMapping("/add-subject")
    public SubjectEntity addSubject(@RequestBody SubjectEntity subject){
        return service.addSubject(subject);
    }

    @PostMapping("/add-faculty")
    public FacultyEntity addFaculty(@RequestBody FacultyEntity faculty){
        return service.addFaculty(faculty);
    }

    @PutMapping("/assign-subject-to-faculty/{subId}/{facId}")
    public SubjectEntity assignSubjectToFaculty(@PathVariable(value = "subId") int subId,@PathVariable(value = "facId") int facId){
        return service.assignSubjectToFaculty(subId, facId);
    }

    @PostMapping("/addCourse")
    public CourseEntity addCourse(@RequestBody CourseEntity course){
        return service.addCourse(course);
    }

    @PutMapping("/addSubjectToCourse/{subId}/{cId}")
    public CourseEntity addSubjectToCourse(@PathVariable int subId,@PathVariable int cId){
        return service.addSubjectToCourse(subId,cId);
    }

    @PostMapping("/addStudent")
    public StudentEntity addStudent(@RequestBody StudentEntity student){
        return service.addStudent(student);
    }

    @PutMapping("/addCourseToStudent/{cId}/{sId}")
    public CourseEntity addCourseToStudent(@PathVariable int cId,@PathVariable int sId){
        return service.addCourseToStudent(cId,sId);
    }


    @GetMapping("/getCoursesByStreamAndSubject/{stream}/{subName}")
    public List<CourseEntity> getCoursesByStreamAndSubject(@PathVariable String stream,@PathVariable String subName){
        return service.getCoursesByStreamAndSubject(stream,subName);
    }

    @GetMapping("/getStudentsByCourseSubName/{subName}")
    public List<StudentEntity>  getStudentsByCourseSubName(@PathVariable String subName){
        return service.getStudentsByCourseSubName(subName);
    }

    @GetMapping("/getCoursesByFacultyId/{id}")
    public List<CourseEntity> getCoursesByFacultyId(@PathVariable int id){
        return service.getCoursesByFacultyId(id);
    }
}
