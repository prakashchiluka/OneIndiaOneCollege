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
public class FacultyModel {
    private int id;
    private String name;
    private String college;
    private List<String> subjects;
    private List<String> courses;
}
