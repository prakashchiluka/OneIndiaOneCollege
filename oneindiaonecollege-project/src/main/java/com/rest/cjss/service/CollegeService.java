package com.rest.cjss.service;

import com.rest.cjss.entity.CourseEntity;
import com.rest.cjss.entity.FacultyEntity;
import com.rest.cjss.entity.StudentEntity;
import com.rest.cjss.entity.SubjectEntity;
import com.rest.cjss.exception.CourseNotFoundException;
import com.rest.cjss.exception.FacultyNotFoundException;
import com.rest.cjss.exception.StudentNotAvailableException;
import com.rest.cjss.exception.SubjectNotFoundException;
import com.rest.cjss.model.CourseModel;
import com.rest.cjss.model.FacultyModel;
import com.rest.cjss.model.StudentModel;
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
       else if(!faculty.isPresent()){
            throw new FacultyNotFoundException("Please provide valid Faculty Id.");
        }
       else
        throw new SubjectNotFoundException("please provide valid Subject Id.");
    }


    public CourseEntity addCourse(CourseEntity course) {
        course.setSubjects(new ArrayList<>());
        course.setStudents(new ArrayList<>());
        return courseRepository.save(course);
    }

    public CourseEntity addSubjectToCourse(int subId,int cId)throws SubjectNotFoundException,CourseNotFoundException{
        Optional<CourseEntity> course = courseRepository.findById(cId);
        Optional<SubjectEntity> subject = subjectRepo.findById(subId);

        if(course.isPresent() && subject.isPresent()){
            CourseEntity entity = course.get();
            SubjectEntity subjectEntity = subject.get();

            subjectEntity.setCourseEntities(entity);
            entity.getSubjects().add(subjectEntity);
            return    courseRepository.save(entity);
        }
        else{
            if(!course.isPresent()){
                throw new CourseNotFoundException("No course available given Course Id,please provide Existing Coure Id");
            }else{
                throw new SubjectNotFoundException("No Subject available given Subject Id,please provide Existing Subject Id");
            }
        }
    }


    public StudentEntity addStudent(StudentEntity student){
        student.setCourseList(new ArrayList<>());
        return studentRepository.save(student);
    }

    public CourseEntity addCourseToStudent(int cId,int sId)throws StudentNotAvailableException,CourseNotFoundException{
        Optional<StudentEntity> student= studentRepository.findById(sId);
        Optional<CourseEntity> course = courseRepository.findById((cId));

        if(student.isPresent() && course.isPresent()){
            StudentEntity studentEntity = student.get();
            CourseEntity courseEntity = course.get();

            studentEntity.getCourseList().add(courseEntity);
            courseEntity.getStudents().add(studentEntity);
            return courseRepository.save(courseEntity);
        }
        else if(!student.isPresent()){
            throw new StudentNotAvailableException("No student available with the given Student Id, please provide the existing Student id");
        }
        throw new CourseNotFoundException("No Course available with the given Course Id, please provide the existing Course id");
    }

    public List<CourseModel> getCoursesByStreamAndSubject(String stream,String subName)throws CourseNotFoundException{
        List<CourseEntity> entities= courseRepository.findByStreamAndSubjectsSubjectName(stream,subName).get();
        if(entities.size()>0){
            List<CourseModel> courseModels = new ArrayList<>();
            entities.stream()
                    .forEach(course -> {
                        CourseModel model = new CourseModel();
                        model.setSubjects(new ArrayList<>());

                        model.setCourseId(course.getCourseId());
                        model.setCourseName(course.getCourseName());
                        model.setStream(course.getStream());
                        course.getSubjects().stream()
                                .forEach(sub->{
                                    model.getSubjects().add(sub.getSubjectName());
                                });
                        courseModels.add(model);
                    });
            return courseModels;
        }
        else throw new CourseNotFoundException("No course available on the provide subject "+subName+" in stream :"+stream);
    }

 //--------------------------------------------------------------------------------------------------------


    public List<StudentModel> getStudentsByCourseSubName(String subName)throws StudentNotAvailableException {

        List<CourseEntity> entities=courseRepository.findBySubjectsSubjectName(subName).get();

        if(entities.size()>0) {
            List<StudentModel> models = new ArrayList<>();

            entities.forEach(course -> {
                course.getStudents().stream().forEach(student -> {
                    StudentModel studentModel = new StudentModel();
                    studentModel.setSubjects(new ArrayList<>());
                    studentModel.setCourses(new ArrayList<>());

                    studentModel.setId(student.getStudentId());
                    studentModel.setName(student.getName());
                    studentModel.setStream(student.getStream());
                    studentModel.setCollege(student.getCollege());
                    studentModel.setCourses(new ArrayList<>());
                    studentModel.setSubjects(new ArrayList<>());
                    student.getCourseList().stream()
                            .forEach(cid -> {
                                studentModel.getCourses().add(cid.getCourseName());
                                cid.getSubjects().stream()
                                        .forEach(sub -> {
                                            studentModel.getSubjects().add(sub.getSubjectName());
                                        });
                            });
                    models.add(studentModel);
                });
            });
            return models;
        }
        else throw new StudentNotAvailableException("No Student present with the given details..!");
    }
//--------------------------------------------------------------------------------------------------------
    public FacultyModel getCoursesByFacultyId(int id){
        Optional<FacultyEntity> optionalFaculty = facultyRepository.findById(id);
        if(optionalFaculty.isPresent()){
            FacultyEntity faculty = optionalFaculty.get();
            List<CourseEntity> courseEntities= courseRepository.findBySubjectsFacultyEntitiesFacultyId(id).get();


            FacultyModel facultyModel = new FacultyModel();
            facultyModel.setCourses(new ArrayList<>());
            facultyModel.setSubjects(new ArrayList<>());

            facultyModel.setId(faculty.getFacultyId());
            facultyModel.setName(faculty.getName());
            facultyModel.setCollege(faculty.getCollage());
            faculty.getSubjects().stream()
                    .forEach(subject -> {
                        facultyModel.getSubjects().add(subject.getSubjectName());
                    });
            if(courseEntities.size()>0) {
                courseEntities.stream()
                        .forEach(course -> {
                            facultyModel.getCourses().add(course.getCourseName());
                        });
            }else{
                facultyModel.getCourses().add("Not registered in anu course");
            }
            return facultyModel;
        }
        else throw new FacultyNotFoundException("No Faculty present with the provided id");
    }
}
