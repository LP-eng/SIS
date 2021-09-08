package com.web.servlect;

import com.web.dao.AllDataDao;
import com.web.dao.LoginDao;
import com.web.eneity.ClassLeaveInfo;
import com.web.eneity.LeaveInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/headmasterServlet")
public class headmasterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hm_date = request.getParameter("date");


        //传入用户信息
        request.setAttribute("User", LoginDao.current_user);  //传入用户姓名

        //传入请假信息
        java.util.Date dateUtil = headteacherServlet.tranToDate(hm_date);
        java.sql.Date date_sql = new java.sql.Date(dateUtil.getTime());
        int flag = 1;
        List<LeaveInfo> leaveInfoList = new ArrayList<LeaveInfo>();
        try {
            leaveInfoList = AllDataDao.getAllLeaveInfo(date_sql);
            if (leaveInfoList.size() == 0) {
                flag = 0; //flag为0表示当天无数据
            }
            request.setAttribute("leaveInfo", leaveInfoList);
            request.setAttribute("choose_date", hm_date);
            request.setAttribute("flag", flag);

            //也要将数据传入到页面上
            List<ClassLeaveInfo> classLeaveInfos = new ArrayList<ClassLeaveInfo>();
            classLeaveInfos = LoginDao.getAllInfo(date_sql);

            request.setAttribute("classLeaveInfos", classLeaveInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("headmaster.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
