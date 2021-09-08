package com.web.dao;

import com.web.eneity.LeaveInfo;
import com.web.eneity.Student;
import com.web.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AllDataDao {
    //得到一个班级的请假信息
    public static List<LeaveInfo> getLeaveInfo(Date date, String li_stu_grade, String li_stu_class) throws Exception{
        //构造最后所得信息
        List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();

        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();
        String sql = "select * from leave_info where li_time=? and li_stu_grade=? and li_stu_class=?";
        //对sql语句进行预处理
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setDate(1, date);
        prst.setString(2, li_stu_grade);
        prst.setString(3, li_stu_class);

        //执行sql语句
        ResultSet rs = prst.executeQuery();

        while (rs.next()){
            LeaveInfo leaveInfo = new LeaveInfo(rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5));
            leaveInfoList.add(leaveInfo);
        }

        //返回查询的数据
        return leaveInfoList;
    }

    //得到一个年级的请假信息
    public static List<LeaveInfo> getGradeLeaveInfo(Date date, String li_stu_grade) throws Exception{
        //构造最后所得信息
        List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();

        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();
        String sql = "select * from leave_info where li_time=? and li_stu_grade=?";
        //对sql语句进行预处理
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setDate(1, date);
        prst.setString(2, li_stu_grade);

        //执行sql语句
        ResultSet rs = prst.executeQuery();

        while (rs.next()){
            LeaveInfo leaveInfo = new LeaveInfo(rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            leaveInfoList.add(leaveInfo);
        }

        //返回查询的数据
        return leaveInfoList;
    }

    //得到当日所有的请假信息
    public static List<LeaveInfo> getAllLeaveInfo(Date date) throws Exception{
        //构造最后所得信息
        List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();

        //获取一个数据库连接
        Connection conn = JDBCUtil.getCon();
        String sql = "select * from leave_info where li_time=?";
        //对sql语句进行预处理
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setDate(1, date);

        //执行sql语句
        ResultSet rs = prst.executeQuery();

        while (rs.next()){
            LeaveInfo leaveInfo = new LeaveInfo(rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            leaveInfoList.add(leaveInfo);
        }

        //返回查询的数据
        return leaveInfoList;
    }
}
