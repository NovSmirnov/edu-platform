package com.example.eduplatform.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Calendar;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectDto {
    private long subjectId;
    private String subjectName;
    private CourseDto course;
    private Calendar date;
    private double maxGrade;
    private Set<StudentDto> students;
}
