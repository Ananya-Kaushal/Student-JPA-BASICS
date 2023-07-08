package com.example.jdbcdemo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)//changes the attributes and methods to private
@NoArgsConstructor//default constructor
@AllArgsConstructor//parameterised constructor
@Getter//builds getter for all attributes
@Setter//builds setter for all attributes
@Entity//this is a model class
public class Student {
    @Id
    int AdmNo;

    String name;

    int age;
}
