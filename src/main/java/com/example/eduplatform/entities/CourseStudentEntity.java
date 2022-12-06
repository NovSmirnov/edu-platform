package com.example.eduplatform.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course_student")
public class CourseStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "course_student_id")
    private Long courseStudentId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity courseEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity studentEntity;

//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "date")
    private Calendar date;

    @Column(name = "is_active")
    private boolean isActive;
}
