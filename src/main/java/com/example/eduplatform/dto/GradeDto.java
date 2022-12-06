package com.example.eduplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GradeDto {
    private Long gradeId;
    private StudentDto student;
    private SubjectDto subject;
    private double grade;
}
