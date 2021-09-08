package com.web.eneity;

public class HeadTeacher {
    private String index;
    private String name;
    private String phone;
    private String grade;
    private String class1;

    public HeadTeacher(){ }

    public HeadTeacher(String index, String name, String phone, String grade, String class1){
        this.index = index;
        this.name = name;
        this.phone = phone;
        this.grade = grade;
        this.class1 = class1;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGrade() {
        return grade;
    }

    public String getClass1() {
        return class1;
    }
}
