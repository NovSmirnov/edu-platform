package com.example.eduplatform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(name = "date")
    private Calendar date;

    @Column(columnDefinition = "max_grade")
    private double maxGrade;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "subjects")
    private Set<StudentEntity> students;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject")
    private Set<GradeEntity> grades;


}
