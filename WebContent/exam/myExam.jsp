<%--
  Created by IntelliJ IDEA.
  User: zhonglunsheng
  Date: 2017/8/30
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="table table-bordered table-hover">
    <tr>
        <th>序号</th>
        <th>试卷名称</th>
        <th>考试日期</th>
        <th>单选题得分</th>
        <th>多选题得分</th>
        <th>总分</th>
    </tr>
    <c:forEach var="exam" items="${s_examList }" varStatus="status">
        <tr>
            <td>${status.index+1 }</td>
            <td>${exam.paper.paperName }</td>
            <td><fmt:formatDate value="${exam.examDate }" type="date" pattern="yyyy-MM-dd"/></td>
            <td>${exam.singleScore }</td>
            <td>${exam.moreScore }</td>
            <td><font color="red">${exam.score }</font></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
