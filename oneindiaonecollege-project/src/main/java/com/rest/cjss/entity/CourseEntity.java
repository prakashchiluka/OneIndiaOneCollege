package com.rest.cjss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;
    private String courseName;
    private String branch;
    private String stream;

    @OneToMany(mappedBy = "courseEntities",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<SubjectEntity> subjects;

    @JsonIgnore
    @ManyToMany(mappedBy = "courseList")
    private  List<StudentEntity> students;
}
