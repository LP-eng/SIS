package com.web.eneity;

public class Student {
    private String index;
    private String name;
    private String gender;
    private String grade;
    private String class1;
    private String type;

    public Student (){
    }

    public Student(String name, String gender, String grade, String class1, String type){
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.class1 = class1;
        this.type = type;
    }

    public Student(String index, String name, String gender, String grade, String class1, String type){
        this.index = index;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.class1 = class1;
        this.type = type;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getGrade() {
        return grade;
    }

    public String getClass1() {
        return class1;
    }

    public String getType() {
        return type;
    }
}
