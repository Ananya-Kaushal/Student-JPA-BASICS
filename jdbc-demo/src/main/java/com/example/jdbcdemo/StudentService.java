package com.example.jdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student)
    {
        studentRepository.save(student);
        return "Successfully Added the record!!";
    }

    public  Student getStudent(int AdmNo)
    {
        Optional<Student> optionalStudent= studentRepository.findById(AdmNo);
        if(optionalStudent.isPresent())
            return  optionalStudent.get();
        return null;
    }

    public boolean deleteStudentRecord(int AdmNo) {
        Optional<Student> student=studentRepository.findById(AdmNo);
        boolean flag=false;
        if (student.isPresent())
        {
            studentRepository.deleteById(AdmNo);
            flag=true;
        }
        return flag;
    }

    public Optional<Student> updateAge(int admNo, int age) {
        Optional<Student> student=studentRepository.findById(admNo);
        if (student.isPresent())
        {
            student.get().setAge(age);
            return student;
        }
        return null;
    }

    public int getTheTotalStudent() {
        int count=0;
        for (Student student:studentRepository.findAll())
        {
            if(student.getAge()>25)count++;
        }
        return count;
    }

    public int getStudentWithName(String name) {
        int count=0;
        for (Student student:studentRepository.findAll())
        {
            if(student.getName().equals(name))
                count++;
        }
        return count;
    }

    public List<Student> getTheTotalStudentList() {
        List<Student> students=new ArrayList<>();
        for (Student student:studentRepository.findAll())
        {
            if(student.getAge()>25)students.add(student);
        }
        return students;
    }

    public List<Student> getStudentWithNameList(String name) {
        List<Student> students=new ArrayList<>();
        for (Student student:studentRepository.findAll())
        {
            if(student.getName().equals(name))students.add(student);
        }
        return students;
    }

    public List<Student> findByAgeGreaterThan(int age) {
        List<Student> students=studentRepository.findByAgeGreaterThan(age);
        return students;
    }

    public List<Student> findByName(String name) {
        List<Student> students=studentRepository.findByName(name);
        return students;
    }

    public String deleteAllRecords() {
        studentRepository.deleteAll();
        return "All the records are deleted";
    }
}
