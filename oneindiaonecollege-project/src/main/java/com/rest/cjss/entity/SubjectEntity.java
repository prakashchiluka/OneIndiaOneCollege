package com.rest.cjss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Subject")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subjectId;
    private String subjectName;
    private String branch;
    private String stream;

    @ManyToMany
    private Set<FacultyEntity> facultyEntities;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntities;
}
