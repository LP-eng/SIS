<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2021/8/17
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>校长管理界面</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- 左边导航栏 -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <div style="position: fixed;">
            <!-- Sidebar - Brand -->
            <div class="sidebar-brand d-flex align-items-center justify-content-center">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">校长管理界面</div>
            </div>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="#chooseContent1">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>请假信息</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <li class="nav-item">
                <a class="nav-link" href="#chooseContent2">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>查看各班学生情况</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

        </div>
    </ul>

    <!-- 右边内容 -->
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <!-- 上方导航栏 -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- 搜索按钮 -->
                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">
                    <div class="topbar-divider d-none d-sm-block"></div>
                    <!-- Nav Item - 用户信息 -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${requestScope.User.name}</span>
                            <img class="img-profile rounded-circle" src="img/head.jpg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" style="pointer-events: none;">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                账号ID：${requestScope.User.id}
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                设置
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="index.jsp" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                退出
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>

            <!-- 右边主界面（查看请假信息） -->
            <div class="container-fluid" id="chooseContent1">
                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">请假信息</h1>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary"
                            style="display:inline-block;">${requestScope.choose_date} 请假信息</h6>
                        <div style="display:inline-block; margin-left: 60%;">
                            <form action="headmasterServlet" method="post">
                                <label for="choose_date">选择日期</label><input id="choose_date" name="date" type="date"
                                                                            value=""/>
                                <input class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
                                       id="choose_date_submit" type="submit" value="确定">
                            </form>
                        </div>
                    </div>

                    <c:if test="${requestScope.flag == 0}">
                        <div class="leave_info">
                            <h1 class="h3 mb-2 text-gray-800"
                                style="padding-top: 20%;text-align: center;padding-bottom: 20%">本日无请假信息</h1>
                        </div>
                    </c:if>

                    <c:if test="${requestScope.flag == 1}">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>时间</th>
                                        <th>姓名</th>
                                        <th>班级</th>
                                        <th>原因</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.leaveInfo}" var="data">
                                        <tr>
                                            <td>${data.date}</td>
                                            <td>${data.name}</td>
                                            <td>${data.stu_class}班</td>
                                            <td>${data.reason}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                </div>

            </div>

            <!-- 右边主界面（查看班级学生情况） -->
            <div class="container-fluid" id="chooseContent2">
                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">查看班级学生情况</h1>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">本校各个班级学生情况</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable1" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>年级</th>
                                    <th>班级</th>
                                    <th>班主任</th>
                                    <th>应到人数</th>
                                    <th>实到人数</th>
                                    <th>请假人数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.classLeaveInfos}" var="data">
                                    <tr>
                                        <td>${data.cl_grade}</td>
                                        <td>${data.cl_class} 班</td>
                                        <td>${data.cl_headteacher}</td>
                                        <td>${data.cl_total}</td>
                                        <td>${data.cl_actual}</td>
                                        <td>${data.cl_leave}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
    <!-- End of Content Wrapper -->

</div>

<!-- 回到页面最上方-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- 退出信息提示-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">确定要退出?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">如果您准备退出当前用户，请点击下方的“退出”按钮。</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
                <a class="btn btn-primary" href="index.jsp">退出</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>
<script src="js/basic.js"></script>

</body>

</html>