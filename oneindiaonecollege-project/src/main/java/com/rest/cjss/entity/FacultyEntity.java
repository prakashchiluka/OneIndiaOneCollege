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
@Table(name="Faculty")
public class FacultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int   facultyId;
    private String name;
    private String collage;

    @JsonIgnore
    @ManyToMany(mappedBy = "facultyEntities")
    List<SubjectEntity> subjects;


}
