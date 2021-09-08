package com.web.dao;

import com.web.eneity.HeadTeacher;
import com.web.eneity.LeaveInfo;
import com.web.eneity.Student;
import com.web.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertDao {
    //插入请假信息
    public static void insertLeaveInfo(LeaveInfo info) throws Exception {
        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();

        String sql = "insert into leave_info (li_index, li_time, li_stu_id, li_name, li_reason, li_stu_grade, li_stu_class) values (?,?,?,?,?,?,?);";
        //对sql语句进行预处理
        PreparedStatement past = conn.prepareStatement(sql);
        past.setLong(1, info.getIndex());
        past.setDate(2, info.getDate());
        past.setString(3, info.getId());
        past.setString(4, info.getName());
        past.setString(5, info.getReason());
        past.setString(6, info.getStu_grade());
        past.setString(7, info.getStu_class());

        //System.out.println(past);
        //执行sql语句
        past.executeUpdate();
        //System.out.println("执行语句成功！");
    }

    //插入学生信息
    public static void insertStudentInfo(Student student) throws Exception {
        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();

        String sql = "insert into student(stu_index,stu_name,stu_gender,stu_grade,stu_class,stu_type) values (?,?,?,?,?,?);";
        //对sql语句进行预处理
        PreparedStatement past = conn.prepareStatement(sql);
        past.setString(1, student.getIndex());
        past.setString(2, student.getName());
        past.setString(3, student.getGender());
        past.setString(4, student.getGrade());
        past.setString(5, student.getClass1());
        past.setString(6, student.getType());

        //System.out.println(past);
        //执行sql语句
        past.executeUpdate();
    }

    //插入班主任信息
    public static int insertHeadteacherInfo(HeadTeacher headTeacher) throws Exception {
        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();

        //先判断数据库里是否已经有该老师的信息
        String sql1 = "select count(*) from headteacher where ht_id=?";
        PreparedStatement prst1 = conn.prepareStatement(sql1);
        prst1.setString(1, headTeacher.getIndex());

        ResultSet rs1 = prst1.executeQuery();
        if (rs1.next()){
            if (rs1.getInt(1) != 0){
                //表示已存在该用户
                return 0;
            }
        }
        rs1.close();
        prst1.close();

        //插入到班主任表格中
        String sql = "insert into headteacher(ht_id,ht_name,ht_phone,ht_grade,ht_classe) values (?,?,?,?,?);";
        //对sql语句进行预处理
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, headTeacher.getIndex());
        prst.setString(2, headTeacher.getName());
        prst.setString(3, headTeacher.getPhone());
        prst.setString(4, headTeacher.getGrade());
        prst.setString(5, headTeacher.getClass1());
        //执行sql语句
        prst.executeUpdate();
        prst.close();

        //同时再用户表格中插入数据，默认密码为123456
        String sql2 = "insert into `user`(id,password,`type`,`name`) values (?,?,?,?);";
        //对sql语句进行预处理
        PreparedStatement prst2 = conn.prepareStatement(sql2);
        prst2.setString(1, headTeacher.getIndex());
        prst2.setString(2, "123456");
        prst2.setInt(3, 4);
        prst2.setString(4, headTeacher.getName());
        //执行sql语句
        prst2.executeUpdate();
        prst2.close();

        return 1;
    }

}
