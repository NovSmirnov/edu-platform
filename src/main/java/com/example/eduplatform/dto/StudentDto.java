package com.example.eduplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto {
    private long studentId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int groupNumber;
    private boolean isActive;
    private List<CourseDto> courseDtoList;
}
