package com.rest.cjss.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Student")
public class StudentEntity {

    @Id
    @GeneratedValue (strategy =GenerationType.AUTO)
    private int studentId;
    private String name;
    private  String branch;
    private String stream;
    private String college;
    @ManyToMany
    private List<CourseEntity> courseList;

}
