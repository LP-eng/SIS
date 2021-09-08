package com.web.dao;

import com.web.eneity.*;
import com.web.util.JDBCUtil;

import java.awt.desktop.PreferencesEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {

    public static User current_user = new User();

    //用户登录，成功返回用户
    public static User login(User user) throws Exception {
        int result = -1;
        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();

        String sql = "select * from user where id=? and password=?";
        //对sql语句进行预处理
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, user.getId());
        prst.setString(2, user.getPassword());

        //执行sql语句
        ResultSet rs = prst.executeQuery();

        if (rs.next()) {
            current_user.setId(rs.getString(1));
            current_user.setName(rs.getString(4));
            return new User(rs.getString(1), rs.getString(4), rs.getInt(3));
        }

        //System.out.println(result);

        return null;
    }

    //根据班主任id，得到该老师的信息
    public static HeadTeacher getTeacher(String ht_id) throws Exception {
        HeadTeacher headTeacher = new HeadTeacher();

        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();
        String sql = "select * from headteacher where ht_id=?";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, ht_id);

        ResultSet rs = prst.executeQuery();
        while (rs.next()) {
            headTeacher.setIndex(rs.getString(1));
            headTeacher.setName(rs.getString(2));
            headTeacher.setPhone(rs.getString(3));
            headTeacher.setGrade(rs.getString(4));
            headTeacher.setClass1(rs.getString(5));
        }

        return headTeacher;
    }

    //根据班主任id 得到该老师所在班级并得到其班上学生信息
    public static List<Student> getAllStudent(String ht_id) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        String ht_grade = "", ht_class = "";

        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();
        String sql = "select * from headteacher where ht_id=?";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, ht_id);

        ResultSet rs = prst.executeQuery();
        while (rs.next()) {
            ht_grade = rs.getString("ht_grade");
            ht_class = rs.getString("ht_class");
        }

        String sql1 = "select * from student where stu_grade=? and stu_class=?";
        PreparedStatement prst1 = conn.prepareStatement(sql1);
        prst1.setString(1, ht_grade);
        prst1.setString(2, ht_class);

        ResultSet rs1 = prst1.executeQuery();
        while (rs1.next()) {
            Student student = new Student(rs1.getString("stu_index"), rs1.getString("stu_name"), rs1.getString("stu_gender"), rs1.getString("stu_grade"), rs1.getString("stu_class"), rs1.getString("stu_type"));
            studentList.add(student);
        }

        return studentList;
    }

    //根据年级主任id，得到该年级主任的信息
    public static GradeDean getGradeDean(String gd_id) throws Exception {
        GradeDean gradeDean = new GradeDean();

        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();
        String sql = "select * from gradedean where gd_id=?";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, gd_id);

        ResultSet rs = prst.executeQuery();
        while (rs.next()) {
            gradeDean.setGd_id(rs.getString(1));
            gradeDean.setGd_name(rs.getString(2));
            gradeDean.setGd_phone(rs.getString(3));
            gradeDean.setGd_grade(rs.getString(4));
        }

        return gradeDean;
    }

    //根据年级主任的年级，得到该年级的班主任信息
    public static List<HeadTeacher> getTeachaerListInfo(String gd_grade) throws Exception {
        List<HeadTeacher> headTeachers = new ArrayList<HeadTeacher>();
        //先得到各个班级及其班主任信息
        Connection conn = JDBCUtil.getCon();
        String sql = "select * from headteacher where ht_grade=?";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, gd_grade);

        ResultSet rs = prst.executeQuery();
        while (rs.next()){
            HeadTeacher headTeacher = new HeadTeacher(rs.getString("ht_id"), rs.getString("ht_name"), rs.getString("ht_phone"), rs.getString("ht_grade"), rs.getString("ht_class"));
            headTeachers.add(headTeacher);
        }
        rs.close();
        prst.close();

        return headTeachers;
    }

    //根据年级主任的年级，得到该年级当日的班级情况信息
    public static List<ClassLeaveInfo> getGradeInfo(Date date, String gd_grade) throws Exception {
        List<ClassLeaveInfo> classLeaveInfos = new ArrayList<ClassLeaveInfo>();
        //先得到各个班级及其班主任信息
        Connection conn = JDBCUtil.getCon();
        String sql1 = "select * from headteacher where ht_grade=?";
        PreparedStatement prst1 = conn.prepareStatement(sql1);
        prst1.setString(1, gd_grade);

        ResultSet rs1 = prst1.executeQuery();
        while (rs1.next()){
            ClassLeaveInfo classLeaveInfo = new ClassLeaveInfo(rs1.getString("ht_class"), rs1.getString("ht_name"));
            classLeaveInfos.add(classLeaveInfo);
        }
        rs1.close();
        prst1.close();

        String sql2 = "select count(*) from student where stu_grade=? and stu_class=?";
        String sql3 = "select count(*) from leave_info where li_time=? and li_stu_grade=? and li_stu_class=?";
        for (ClassLeaveInfo classLeaveInfo : classLeaveInfos) {
            int total = 0, leave;
            //先查询各个班总人数
            PreparedStatement prst2 = conn.prepareStatement(sql2);
            prst2.setString(1, gd_grade);
            prst2.setString(2, classLeaveInfo.getCl_class());
            ResultSet rs2 = prst2.executeQuery();
            if (rs2.next()) {
                total = rs2.getInt(1);
                classLeaveInfo.setCl_total(total);
            }
            rs2.close();
            prst2.close();

            //在查询各个班当日请假人数
            PreparedStatement prst3 = conn.prepareStatement(sql3);
            prst3.setDate(1, date);
            prst3.setString(2, gd_grade);
            prst3.setString(3, classLeaveInfo.getCl_class());
            ResultSet rs3 = prst3.executeQuery();
            if (rs3.next()) {
                leave = rs3.getInt(1);
                classLeaveInfo.setCl_leave(leave);
                classLeaveInfo.setCl_actual(total - leave);
            }
            rs3.close();
            prst3.close();
        }

        return classLeaveInfos;
    }

    //校长登录账号，显示所有班级的情况的请假情况
    public static List<ClassLeaveInfo> getAllInfo(Date date) throws Exception {
        List<ClassLeaveInfo> classLeaveInfos = new ArrayList<ClassLeaveInfo>();
        //先得到各个班级及其班主任信息
        Connection conn = JDBCUtil.getCon();
        String sql1 = "select * from headteacher";
        PreparedStatement prst1 = conn.prepareStatement(sql1);

        ResultSet rs1 = prst1.executeQuery();
        while (rs1.next()){
            ClassLeaveInfo classLeaveInfo = new ClassLeaveInfo(rs1.getString("ht_grade"), rs1.getString("ht_class"), rs1.getString("ht_name"));
            classLeaveInfos.add(classLeaveInfo);
        }
        rs1.close();
        prst1.close();

        String sql2 = "select count(*) from student where stu_grade=? and stu_class=?";
        String sql3 = "select count(*) from leave_info where li_time=? and li_stu_grade=? and li_stu_class=?";
        for (ClassLeaveInfo classLeaveInfo : classLeaveInfos) {
            int total = 0, leave;
            //先查询各个班总人数
            PreparedStatement prst2 = conn.prepareStatement(sql2);
            prst2.setString(1, classLeaveInfo.getCl_grade());
            prst2.setString(2, classLeaveInfo.getCl_class());
            ResultSet rs2 = prst2.executeQuery();
            if (rs2.next()) {
                total = rs2.getInt(1);
                classLeaveInfo.setCl_total(total);
            }
            rs2.close();
            prst2.close();

            //在查询各个班当日请假人数
            PreparedStatement prst3 = conn.prepareStatement(sql3);
            prst3.setDate(1, date);
            prst3.setString(2, classLeaveInfo.getCl_grade());
            prst3.setString(3, classLeaveInfo.getCl_class());
            ResultSet rs3 = prst3.executeQuery();
            if (rs3.next()) {
                leave = rs3.getInt(1);
                classLeaveInfo.setCl_leave(leave);
                classLeaveInfo.setCl_actual(total - leave);
            }
            rs3.close();
            prst3.close();
        }

        return classLeaveInfos;
    }
}
