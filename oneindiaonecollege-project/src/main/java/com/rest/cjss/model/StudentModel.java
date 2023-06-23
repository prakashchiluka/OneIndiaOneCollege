package com.rest.cjss.model;

import com.rest.cjss.entity.CourseEntity;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentModel {
     private int id;
     private String name;
     private String stream;
     private String college;
     private List<String> subjects;
     private List<String> courses;
}
