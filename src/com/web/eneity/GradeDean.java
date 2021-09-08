package com.web.eneity;

public class GradeDean {
    private String gd_id;
    private String gd_name;
    private String gd_phone;
    private String gd_grade;

    public GradeDean(){ }

    public GradeDean(String gd_id, String gd_name, String gd_phone, String gd_grade){
        this.gd_id = gd_id;
        this.gd_name = gd_name;
        this.gd_phone = gd_phone;
        this.gd_grade = gd_grade;
    }

    public void setGd_id(String gd_id) {
        this.gd_id = gd_id;
    }

    public void setGd_name(String gd_name) {
        this.gd_name = gd_name;
    }

    public void setGd_phone(String gd_phone) {
        this.gd_phone = gd_phone;
    }

    public void setGd_grade(String gd_grade) {
        this.gd_grade = gd_grade;
    }

    public String getGd_id() {
        return gd_id;
    }

    public String getGd_name() {
        return gd_name;
    }

    public String getGd_phone() {
        return gd_phone;
    }

    public String getGd_grade() {
        return gd_grade;
    }
}
