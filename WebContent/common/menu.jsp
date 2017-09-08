<%--
  Created by IntelliJ IDEA.
  User: zhonglunsheng
  Date: 2017/8/29
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    function logOut() {
        if (confirm("是否退出系统？")){
            window.location.href="student!logOut";
        }
    }

    function logOut2() {
        if (confirm("是否退出系统？")){
            window.location.href="manager!logOut";
        }
    }
</script>
<div class="navbar">
    <div class="navbar-inner">
        <a class="brand" href="main.jsp">首页</a>
        <ul class="nav">
            <c:choose>
                <c:when test="${currentManager.flag==1}">
                    <li><a href="${pageContext.request.contextPath}/student!list">考生信息管理</a></li>
                    <li><a href="${pageContext.request.contextPath}/exam!examList">考生成绩查询</a></li>
                    <li><a href="${pageContext.request.contextPath}/paper!paperList">试卷管理</a></li>
                    <li><a href="${pageContext.request.contextPath}/question!questionList">题目管理</a></li>
                    <li><a href="javascript:logOut2()">退出系统</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="paper!list">在线考试</a></li>
                    <li><a href="exam!list?exam.student.id=${currentStu.id}">成绩查询</a></li>
                    <li><a href="student!update">修改密码</a></li>
                    <li><a href="javascript:logOut()">退出系统</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>