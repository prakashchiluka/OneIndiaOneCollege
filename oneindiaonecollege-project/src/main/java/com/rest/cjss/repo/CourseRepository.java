package com.rest.cjss.repo;

import com.rest.cjss.entity.CourseEntity;
import com.rest.cjss.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
    public Optional<List<CourseEntity>> findByStreamAndSubjectsSubjectName(String stream,String subjectName);

    public Optional<List<CourseEntity>> findBySubjectsSubjectName(String subName);

    public  Optional<List<CourseEntity>> findBySubjectsFacultyEntitiesFacultyId(int id);
}
