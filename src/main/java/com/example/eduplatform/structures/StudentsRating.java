package com.example.eduplatform.structures;

import com.example.eduplatform.dto.StudentDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentsRating extends StudentDto {
    private long studentId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int groupNumber;
    private boolean isActive;
    private List<String> courseNames; // Название курса
    private List<Double> ratings; // Средний балл по каждому курсу на данный момент
    private List<Boolean> isDone; //Признак выполнения

    public StudentsRating(StudentDto studentDto, List<String> courseNames, List<Double> ratings) {
        this.ratings = new ArrayList<>();
        this.isDone = new ArrayList<>();

        setStudent(studentDto);
        this.courseNames = courseNames;
        roundAndSetRatings(ratings);
        for(Double avGrade : ratings) {
            if (avGrade < 70) {
                isDone.add(false);
            } else {
                isDone.add(true);
            }
        }
    }

    private void setStudent(StudentDto studentDto) {
        this.studentId = studentDto.getStudentId();
        this.firstName = studentDto.getFirstName();
        this.lastName = studentDto.getLastName();
        this.patronymic = studentDto.getPatronymic();
        this.groupNumber = studentDto.getGroupNumber();
        this.isActive = studentDto.isActive();
    }

    private void roundAndSetRatings(List<Double> ratings) {
        BigDecimal grade;
        for (Double averageGrade : ratings) {
            grade = new BigDecimal(averageGrade);
            Double formattedGrade = grade.setScale(2, RoundingMode.HALF_UP).doubleValue();
            this.ratings.add(formattedGrade);
        }
    }
}
