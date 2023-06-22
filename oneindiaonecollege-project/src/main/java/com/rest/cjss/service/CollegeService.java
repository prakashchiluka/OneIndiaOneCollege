package com.rest.cjss.service;

import com.rest.cjss.entity.CourseEntity;
import com.rest.cjss.entity.FacultyEntity;
import com.rest.cjss.entity.StudentEntity;
import com.rest.cjss.entity.SubjectEntity;
import com.rest.cjss.repo.CourseRepository;
import com.rest.cjss.repo.FacultyRepository;
import com.rest.cjss.repo.StudentRepository;
import com.rest.cjss.repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class CollegeService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepo subjectRepo;

    public SubjectEntity addSubject(SubjectEntity subject){
        subject.setFacultyEntities(new HashSet<>());
        return subjectRepo.save(subject);
    }

    public FacultyEntity addFaculty(FacultyEntity faculty){
        faculty.setSubjects(new ArrayList<>());
        return facultyRepository.save(faculty);
    }

    public SubjectEntity assignSubjectToFaculty(int subId, int facId){
        Optional<FacultyEntity> faculty = facultyRepository.findById(facId);
        Optional<SubjectEntity> subject = subjectRepo.findById(subId);

        if(faculty.isPresent() && subject.isPresent()){
                FacultyEntity entity = faculty.get();
                SubjectEntity subjectEntity = subject.get();

                entity.getSubjects().add(subjectEntity);
                subjectEntity.getFacultyEntities().add(entity);
             return    subjectRepo.save(subjectEntity);
        }
        return null;
    }


    public CourseEntity addCourse(CourseEntity course){
        course.setSubjects(new ArrayList<>());
        course.setStudents(new ArrayList<>());
        return courseRepository.save(course);
    }

    public CourseEntity addSubjectToCourse(int subId,int cId){
        Optional<CourseEntity> course = courseRepository.findById(cId);
        Optional<SubjectEntity> subject = subjectRepo.findById(subId);

        if(course.isPresent() && subject.isPresent()){
            CourseEntity entity = course.get();
            SubjectEntity subjectEntity = subject.get();

            subjectEntity.setCourseEntities(entity);
            entity.getSubjects().add(subjectEntity);
            return    courseRepository.save(entity);
        }
        return null;
    }


    public StudentEntity addStudent(StudentEntity student){
        student.setCourseList(new ArrayList<>());
        return studentRepository.save(student);
    }

    public CourseEntity addCourseToStudent(int cId,int sId){
        Optional<StudentEntity> student= studentRepository.findById(sId);
        Optional<CourseEntity> course = courseRepository.findById((cId));

        if(student.isPresent() && course.isPresent()){
            StudentEntity studentEntity = student.get();
            CourseEntity courseEntity = course.get();

            studentEntity.getCourseList().add(courseEntity);
            courseEntity.getStudents().add(studentEntity);
            return courseRepository.save(courseEntity);
        }
        return null;
    }

    public List<CourseEntity> getCoursesByStreamAndSubject(String stream,String subName){
        return courseRepository.findByStreamAndSubjectsSubjectName(stream,subName).get();
    }

    public List<StudentEntity> getStudentsByCourseSubName(String subName){
        List<CourseEntity> entities=courseRepository.findBySubjectsSubjectName(subName).get();
        List<StudentEntity> studentEntities= new ArrayList<>();
        entities.forEach(x-> studentEntities.addAll(x.getStudents()));
        return studentEntities;
    }

    public List<CourseEntity> getCoursesByFacultyId(int id){
        return courseRepository.findBySubjectsFacultyEntitiesFacultyId(id).get();
    }
}
