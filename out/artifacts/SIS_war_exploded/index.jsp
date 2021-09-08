<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2021/8/17
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//  Object waringInfo = request.getAttribute("warningInfo");
%>
<base href="<%=basePath%>>">
<html>
  <head>
    <meta charset="UTF-8">
    <title>学生请假信息管理系统</title>
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/base.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <body id="sandbox">
  <div id="content">
    <div id="content-block" class="main_content">
      <div class="head">
        <div style="margin-top: 10px; font-size: 24px; letter-spacing:2px;font-weight: bold;color: #004b9d; margin-bottom: 5px;text-align: center;">学生请假信息管理系统</div>
        <div style="clear: both"></div>
      </div>
      <form class="login-form" action="loginServlet" method="post" id="login">
        <div class="col-sm-offset-0 col-sm-18">
          <div class="alert alert-danger" id="warningInfo">
<%--            <input type="hidden" value= "<%=waringInfo%>" id="hiddenInfo">--%>
          </div>
        </div>
        <div class="form-group">
          <div style="position: relative;margin-bottom: 30px">
            <img src="img/user.png" alt="" width="18px" class="loginIcon">
            <input type="text" class="form-control" name="id" placeholder="请输入用户名">
          </div>
        </div>
        <div class="form-group">
          <div style="position: relative">
            <img src="img/pwd.png" alt="" width="17px" class="loginIcon">
            <input type="password" class="form-control" name="password" placeholder="请输入密码">
          </div>
        </div>
        <div class="form-group">
          <div style="position: relative">
            <select id="select" class="form-control">
              <option label="校长" value="1">校长</option>
              <option label="科室主任" value="2">科室主任</option>
              <option label="年级主任" value="3">年级主任</option>
              <option label="班主任" value="4">班主任</option>
            </select>
          </div>
        </div>
        <div class="form-group">
          <div>
            <button type="submit" class="submitBtn">登   录</button>
          </div>
        </div>
      </form>
    </div>
    <div class="footer" id="footer">
      <div class="footerContent">
        版权所有 © 2021
      </div>
    </div>
  </div>
  </body>
  <script src="js/basic.js"></script>
</html>
