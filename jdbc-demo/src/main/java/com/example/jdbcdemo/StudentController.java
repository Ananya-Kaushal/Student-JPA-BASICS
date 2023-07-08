package com.example.jdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student)
    {
        String response=studentService.addStudent(student);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("AdmNo") int AdmNo)
    {
        Student student=studentService.getStudent(AdmNo);
        if(student==null)
        {
            return new ResponseEntity<>("Invalid Admission No",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }
    //delete a student by AdmNo;
    @DeleteMapping("/delete-by-AdmNo/{id}")
    public ResponseEntity deleteStudentRecord(@PathVariable int AdmNo)
    {
        Student student=studentService.getStudent(AdmNo);
        if(student==null)
        {
            return new ResponseEntity<>("Invalid Admission No",HttpStatus.NOT_FOUND);
        }
        else {
            studentService.deleteStudentRecord(AdmNo);
        }
        return  new ResponseEntity<>(student,HttpStatus.FOUND);
    }
    //Update the age of student based on AdmNo and age;
    @PutMapping("/update-age")
    public ResponseEntity updateAge(@RequestParam("age") int age,@RequestParam("AdmNo") int AdmNo)
    {
        Optional<Student> student=studentService.updateAge(AdmNo,age);
        return  new ResponseEntity<>(student,HttpStatus.OK);
    }
    //Find all the student whose age > 25;
    @GetMapping("/get-the-total")
    public int getTheTotalStudent()
    {
        int count=studentService.getTheTotalStudent();
        return count;
    }
    //Find all the students with the name X;
    @GetMapping("/get-student-with-name/{name}")
    public int getStudentWithName(@PathVariable String name)
    {
        int count=studentService.getStudentWithName(name);
        return  count;
    }
    //Find all the student whose age > 25 List of student;
    @GetMapping("/get-the-total-List")
    public ResponseEntity getTheTotalStudentList()
    {
        List<Student> student=studentService.getTheTotalStudentList();
        return new ResponseEntity<>("Valid",HttpStatus.FOUND);
    }
    //Find all the students with the name X List of Student;
    @GetMapping("/get-student-with-name-List/{name}")
    public ResponseEntity getStudentWithNameList(@PathVariable String name)
    {
        List<Student> student=studentService.getStudentWithNameList(name);
        return  new ResponseEntity<>("Valid",HttpStatus.FOUND);
    }

    //Find all the student whose age > 25 List of student;
    @GetMapping("/get-the-student-greater/{age}")
    public ResponseEntity findByAgeGreaterThan(@PathVariable int age)
    {
        List<Student> students= studentService.findByAgeGreaterThan(age);
        if(students.isEmpty())
        {
            return new ResponseEntity<>("No Student Record found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students,HttpStatus.OK);
    }
    //Find all the students with the name X List of Student;
    @GetMapping("/get-the-student-with-name/{name}")
    public ResponseEntity findByName(@PathVariable String name)
    {
        List<Student> students= studentService.findByName(name);
        if (students.isEmpty())
        {
            return  new ResponseEntity<>("No Student Record found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students,HttpStatus.OK);
    }
    //delete all the students;
    @DeleteMapping("delete-all-records")
    public ResponseEntity deleteAllRecords()
    {
        String response=studentService.deleteAllRecords();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
