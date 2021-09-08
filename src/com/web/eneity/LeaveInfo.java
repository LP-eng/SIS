package com.web.eneity;

import java.sql.Date;

public class LeaveInfo {
    private long index;
    private Date date;
    private String id;
    private String name;
    private String reason;
    private String stu_grade;
    private String stu_class;

    public LeaveInfo (){
    }

    public LeaveInfo(Date date, String id, String name, String reason){
        this.date = date;
        this.id = id;
        this.name = name;
        this.reason = reason;
    }

    public LeaveInfo(Date date, String id, String name, String reason, String stu_grade, String stu_class){
        this.date = date;
        this.id = id;
        this.name = name;
        this.reason = reason;
        this.stu_grade = stu_grade;
        this.stu_class = stu_class;
    }

    public LeaveInfo(long index, Date date, String id, String name, String reason, String stu_grade, String stu_class){
        this.index = index;
        this.date = date;
        this.id = id;
        this.name = name;
        this.reason = reason;
        this.stu_grade = stu_grade;
        this.stu_class = stu_class;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStu_grade(String stu_grade) {
        this.stu_grade = stu_grade;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getIndex() {
        return index;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    public String getStu_grade() {
        return stu_grade;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void print(){
        System.out.println(date + "  " + name + "  " + reason);
    }
}
