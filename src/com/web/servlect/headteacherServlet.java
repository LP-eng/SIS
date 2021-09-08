package com.web.servlect;

import com.web.dao.AllDataDao;
import com.web.dao.InsertDao;
import com.web.dao.LoginDao;
import com.web.eneity.HeadTeacher;
import com.web.eneity.LeaveInfo;
import com.web.eneity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/headteacherServlet")
public class headteacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        long index = new Date().getTime();  //时间戳用作id
        java.sql.Date date = new java.sql.Date(index);
        String li_name = request.getParameter("li_name");
        String li_stu_id = request.getParameter("li_stu_id");
        String li_reason = request.getParameter("li_reason");
        String li_date = request.getParameter("date");
        String stu_index = request.getParameter("stu_index");
        String stu_name = request.getParameter("stu_name");
        String stu_gender = request.getParameter("stu_gender");
        String stu_grade = request.getParameter("stu_grade");
        String stu_class = request.getParameter("stu_class");
        String stu_type = request.getParameter("stu_type");

        HeadTeacher headTeacher = new HeadTeacher();
        try {
            headTeacher = LoginDao.getTeacher(LoginDao.current_user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("HeadTeacher", headTeacher);  //传入班主任信息
        request.setAttribute("User", LoginDao.current_user);  //传入用户姓名

        //添加学生信息
        if (stu_index != null){
            try {
                InsertDao.insertStudentInfo(new Student(stu_index,stu_name,stu_gender,stu_grade,stu_class,stu_type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (li_date == null){
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            li_date = formater.format(new Date());
        }

        //添加请假信息
        if (li_name != null) {
            try {
                InsertDao.insertLeaveInfo(new LeaveInfo(index, date, li_stu_id, li_name, li_reason, headTeacher.getGrade(), headTeacher.getClass1()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //查看请假信息
        java.util.Date dateUtil = tranToDate(li_date);
        java.sql.Date date_sql = new java.sql.Date(dateUtil.getTime());
        int flag = 1;
        List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();
        try {
            leaveInfoList = AllDataDao.getLeaveInfo(date_sql, headTeacher.getGrade(), headTeacher.getClass1());
            if (leaveInfoList.size() == 0) {
                flag = 0; //flag为0表示当天无数据
            }
            request.setAttribute("leaveInfo", leaveInfoList);
            request.setAttribute("choose_date", li_date);
            request.setAttribute("flag", flag);

            //也要将学生数据传入到页面上
            List<Student> studentList = new ArrayList<Student>();
            studentList = LoginDao.getAllStudent("4");
            System.out.println("学生的个数： " + studentList.size());
            request.setAttribute("studentListInfo", studentList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("headteacher.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    //将字符串转换为Date数据
    public static java.util.Date tranToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  //M要大写
        java.util.Date date = null;
        try {
            date = (java.util.Date) format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
