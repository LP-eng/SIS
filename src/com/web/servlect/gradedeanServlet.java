package com.web.servlect;

import com.web.dao.AllDataDao;
import com.web.dao.InsertDao;
import com.web.dao.LoginDao;
import com.web.eneity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gradedeanServlet")
public class gradedeanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gd_date = request.getParameter("date");
        String ht_name = request.getParameter("ht_name");
        String ht_phone = request.getParameter("ht_phone");
        String ht_grade = request.getParameter("ht_grade");
        String ht_class = request.getParameter("ht_class");

        if (ht_name != null){
            int x;
            try {
                x = InsertDao.insertHeadteacherInfo(new HeadTeacher(ht_phone,ht_name,ht_phone,ht_grade,ht_class));
                request.setAttribute("insertInfo", x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //传入用户信息
        GradeDean gradeDean = new GradeDean();
        try {
            gradeDean = LoginDao.getGradeDean(LoginDao.current_user.getId());
            request.setAttribute("GradeDean", gradeDean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //传入用户信息
        request.setAttribute("User", LoginDao.current_user);  //传入用户姓名

        //传入该年级班主任信息
        List<HeadTeacher> headTeachers = new ArrayList<HeadTeacher>();
        try {
            headTeachers = LoginDao.getTeachaerListInfo(gradeDean.getGd_grade());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("Headteachers", headTeachers);

        //传入请假信息
        java.util.Date dateUtil = headteacherServlet.tranToDate(gd_date);
        java.sql.Date date_sql = new java.sql.Date(dateUtil.getTime());
        int flag = 1;
        List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();
        try {
            leaveInfoList = AllDataDao.getGradeLeaveInfo(date_sql, gradeDean.getGd_grade());
            if (leaveInfoList.size() == 0) {
                flag = 0; //flag为0表示当天无数据
            }
            request.setAttribute("leaveInfo", leaveInfoList);
            request.setAttribute("choose_date", gd_date);
            request.setAttribute("flag", flag);

            //也要将数据传入到页面上
            List<ClassLeaveInfo> classLeaveInfos = new ArrayList<ClassLeaveInfo>();
            classLeaveInfos = LoginDao.getGradeInfo(date_sql, gradeDean.getGd_grade());

            request.setAttribute("classLeaveInfos", classLeaveInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.getRequestDispatcher("gradeDean.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
