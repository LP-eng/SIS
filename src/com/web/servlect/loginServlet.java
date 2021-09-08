package com.web.servlect;

import com.web.dao.AllDataDao;
import com.web.dao.LoginDao;
import com.web.eneity.*;
import org.springframework.util.xml.StaxUtils;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/loginServlet")
public class loginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        User user = new User(id, password);
        try {
            User user1 = new User();
            user1 = LoginDao.login(user);
            int type = -1;
            if (user1 != null){
                type = user1.getType();
                request.setAttribute("User", user1);  //传入用户姓名
            }

            if (type==0){
                request.getRequestDispatcher("manager.jsp").forward(request, response);
            }
            else if (type==1){

                request.setAttribute("Headmaster", user1);

                //传入请假信息
                //获取当日的请假信息
                Date myDate = new Date();  //获取当前时间
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formater.format(myDate);
                java.sql.Date date = new java.sql.Date(myDate.getTime());
                int flag = 1;
                List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();
                try {
                    leaveInfoList = AllDataDao.getAllLeaveInfo(date);
                    if (leaveInfoList.size() == 0) {
                        flag = 0; //flag为0表示当天无数据
                    }
                    request.setAttribute("leaveInfo", leaveInfoList);
                    request.setAttribute("choose_date", date);
                    request.setAttribute("flag", flag);

                    //也要将数据传入到页面上
                    List<ClassLeaveInfo> classLeaveInfos = new ArrayList<ClassLeaveInfo>();
                    classLeaveInfos = LoginDao.getAllInfo(date);

                    request.setAttribute("classLeaveInfos", classLeaveInfos);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.getRequestDispatcher("headmaster.jsp").forward(request, response);
            }else if (type==2){
                request.getRequestDispatcher("officesDirector.jsp").forward(request, response);
            }else if (type==3){
                GradeDean gradeDean = new GradeDean();
                gradeDean = LoginDao.getGradeDean(id);
                request.setAttribute("GradeDean", gradeDean);

                List<HeadTeacher> headTeachers = new ArrayList<HeadTeacher>();
                headTeachers = LoginDao.getTeachaerListInfo(gradeDean.getGd_grade());
                request.setAttribute("Headteachers", headTeachers);

                //传入请假信息
                //获取当日的请假信息
                Date myDate = new Date();  //获取当前时间
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formater.format(myDate);
                java.sql.Date date = new java.sql.Date(myDate.getTime());
                int flag = 1;
                List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();
                try {
                    leaveInfoList = AllDataDao.getGradeLeaveInfo(date, gradeDean.getGd_grade());
                    if (leaveInfoList.size() == 0) {
                        flag = 0; //flag为0表示当天无数据
                    }
                    request.setAttribute("leaveInfo", leaveInfoList);
                    request.setAttribute("choose_date", date);
                    request.setAttribute("flag", flag);

                    //也要将数据传入到页面上
                    List<ClassLeaveInfo> classLeaveInfos = new ArrayList<ClassLeaveInfo>();
                    classLeaveInfos = LoginDao.getGradeInfo(date, gradeDean.getGd_grade());

                    request.setAttribute("classLeaveInfos", classLeaveInfos);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.getRequestDispatcher("gradeDean.jsp").forward(request, response);
            }else if (type==4){
                //传入班主任信息
                HeadTeacher headTeacher = new HeadTeacher();
                headTeacher = LoginDao.getTeacher(id);
                request.setAttribute("HeadTeacher", headTeacher);

                //获取当日的请假信息
                Date myDate = new Date();  //获取当前时间
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formater.format(myDate);
                java.sql.Date date = new java.sql.Date(myDate.getTime());
                int flag = 1;
                List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();
                try {
                    leaveInfoList = AllDataDao.getLeaveInfo(date, headTeacher.getGrade(), headTeacher.getClass1());
                    if (leaveInfoList.size() == 0){
                        flag = 0; //flag为0表示当天无数据
                    }
                    request.setAttribute("leaveInfo", leaveInfoList);
                    request.setAttribute("choose_date", dateString);
                    request.setAttribute("flag", flag);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //获取学生信息
                List<Student> studentList = new ArrayList<Student>();
                studentList = LoginDao.getAllStudent(user1.getId());
                //System.out.println("学生的个数： " + studentList.size());
                request.setAttribute("studentListInfo", studentList);
                request.getRequestDispatcher("headteacher.jsp").forward(request, response);
            }else {
                request.setAttribute("warningInfo", type);  //传入警告信息
                response.sendRedirect("index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
}
