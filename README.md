Структура базы данных:
![DBdiagram](https://user-images.githubusercontent.com/94735019/206008361-57e22c1f-5bb3-4514-8a88-11f6203a7b5a.JPG)

Для добавления студента производится POST-запрос по адресу http://localhost:8080/platform/student, содержащий следующий в теле следующий json:

{

    "firstName": "Vasily",
    
    "lastName": "Petrov",
    
    "patronymic": "Sergeevich",
    
    "groupNumber": 1,
    
    "active": true
    
}

Id студента генерируется автоматически

Для изменения сущности студента производится PUT-запрос по адресу http://localhost:8080/platform/student, содержащий в теле следующий json: 

{

    "studentId": 1,
    
    "firstName": "Vasily",
    
    "lastName": "Petrov",
    
    "patronymic": "Sergeevich",
    
    "groupNumber": 1,
    
    "active": true
    
}


Для добавления курса производится POST-запрос по адресу http://localhost:8080/platform/course, содержащий в теле следующий json: 

{

    "courseName": "Statictic-1",
    
    "startDate": "2022-12-04T07:16:34.000+00:00",
    
    "finishDate": "2023-03-04T07:00:00.000+00:00",
    
    "active": true
    
}

Id курса генерируется автоматически


Для изменения сущности курса производится PUT-запрос по адресу http://localhost:8080/platform/course, содержащий в теле следующий json: 

{

    "courseId": 2,
    
    "courseName": "Statictic-1",
    
    "startDate": "2022-12-04T07:16:34.000+00:00",
    
    "finishDate": "2023-03-04T07:00:00.000+00:00",
    
    "active": true
    
}

Для записи студента на курс производится POST-запрос по адресу http://localhost:8080/platform/course_student, содержащий в теле следующий json: 

{

    "courseDto": {
    
        "courseId": 2
        
    },
    
    "studentDto": {
    
        "studentId": 1
        
    },
    
    "date": "2022-12-04T10:00:00.000+00:00",
    
    "active": true
    
}

В json указываются Id студента и курса, на который он будет записан.
Одновременно с этим приложение создаёт некоторое количество сущностей в таблице grade, которое соответствует количеству занятий(предметов) в курсе. Поля для оценок будут null и будут заполняться во время обучения. По ним и будет в дальнейшем рассчитываться средний балл конкретного студента на каждом курсе, на котором он обучается.

Для добавления занятия (предмета) в курс производится POST-запрос по адресу http://localhost:8080/platform/subject, содержащий в теле следующий json: 

{

    "subjectName": "Basic-12",
    
    "course": {
    
        "courseId": 3
        
    },
    
    "date": "2022-12-20T08:00:00.000+00:00",
    
    "maxGrade": 100.0,
    
    "students": null
    
}

Для изменения занятия (предмета) в курс производится PUT-запрос по адресу http://localhost:8080/platform/subject, содержащий в теле следующий json: 

{

    "subjectId": 2,
    
    "subjectName": "Basic-52",
    
    "course": {
    
        "courseId": 3,
        
        "courseName": "Mathematic",
        
        "startDate": "2022-12-15T04:00:00.000+00:00",
        
        "finishDate": "2023-03-04T04:00:00.000+00:00",
        
        "active": true
        
    },
    
    "date": "2022-12-20T08:00:00.000+00:00",
    
    "maxGrade": 90.0
    
}

Для получения среднего балла студента по всем курсам, на которые он записан производится POST-запрос по адресу http://localhost:8080/platform/grades, содержащий в теле следующий json:

{

    "studentId": 1,
    
}

Дамп базы данных прикладываю в в папку resources

http://localhost:8080/ прописывается при условии развёртывания прилдожения на локальной машине на порту 8080.
