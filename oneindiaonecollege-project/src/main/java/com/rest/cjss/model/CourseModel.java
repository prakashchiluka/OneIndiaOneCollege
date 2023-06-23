package com.rest.cjss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseModel {
    private int courseId;
    private  String courseName;
    private String stream;
    private List<String> subjects;
}
