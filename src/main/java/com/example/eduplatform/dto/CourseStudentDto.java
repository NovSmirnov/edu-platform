package com.example.eduplatform.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseStudentDto {
    private Long courseStudentId;
    private CourseDto courseDto;
    private StudentDto studentDto;
    private Calendar date;
    private boolean isActive;
}
