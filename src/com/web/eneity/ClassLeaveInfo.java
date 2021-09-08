package com.web.eneity;

public class ClassLeaveInfo {
    private String cl_grade;
    private String cl_class;
    private String cl_headteacher;
    private int cl_total;
    private int cl_actual;
    private int cl_leave;

    public ClassLeaveInfo(){ }

    public ClassLeaveInfo(String cl_class, String cl_headteacher){
        this.cl_class = cl_class;
        this.cl_headteacher = cl_headteacher;
    }

    public ClassLeaveInfo(String cl_grade, String cl_class, String cl_headteacher){
        this.cl_grade = cl_grade;
        this.cl_class = cl_class;
        this.cl_headteacher = cl_headteacher;
    }

    public ClassLeaveInfo(String cl_class, String cl_headteacher, int cl_total, int cl_actual, int cl_leave){
        this.cl_class = cl_class;
        this.cl_headteacher = cl_headteacher;
        this.cl_total = cl_total;
        this.cl_actual = cl_actual;
        this.cl_leave = cl_leave;
    }

    public void setCl_class(String cl_class) {
        this.cl_class = cl_class;
    }

    public void setCl_grade(String cl_grade) {
        this.cl_grade = cl_grade;
    }

    public void setCl_headteacher(String cl_headteacher) {
        this.cl_headteacher = cl_headteacher;
    }

    public void setCl_total(int cl_total) {
        this.cl_total = cl_total;
    }

    public void setCl_actual(int cl_actual) {
        this.cl_actual = cl_actual;
    }

    public void setCl_leave(int cl_leave) {
        this.cl_leave = cl_leave;
    }

    public String getCl_class() {
        return cl_class;
    }

    public String getCl_headteacher() {
        return cl_headteacher;
    }

    public int getCl_total() {
        return cl_total;
    }

    public int getCl_actual() {
        return cl_actual;
    }

    public int getCl_leave() {
        return cl_leave;
    }

    public String getCl_grade() {
        return cl_grade;
    }
}
