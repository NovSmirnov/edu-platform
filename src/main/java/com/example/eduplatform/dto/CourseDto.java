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
public class CourseDto {
    private Long courseId;
    private String courseName;
    private Calendar startDate;
    private Calendar finishDate;
    private boolean isActive;
}
