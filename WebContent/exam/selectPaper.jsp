<%--
  Created by IntelliJ IDEA.
  User: zhonglunsheng
  Date: 2017/8/29
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${pageContext.request.contextPath}/style/exam.css" rel="stylesheet">
<script type="text/javascript">
    function checkForm() {
        var paperId = $("#paperId").val();
        if(paperId==null || paperId==""){
            alert("请选择试卷");
            return false;
        }
        return true;
    }
</script>
<div class="span4 offset3">
    <form action="paper!getDetailPaper" method="post" onsubmit="return checkForm()">
        <strong>请选择考试试卷:</strong>&nbsp;&nbsp;
        <select name="paperId" id="paperId">
            <option value="">请选择。。。</option>
            <c:forEach items="${paperList}" var="paper">
                <option value="${paper.id}">${paper.paperName}</option>
            </c:forEach>
        </select>
        <p class="submit"><button class="btn btn-primary" type="submit">确认</button>&nbsp;&nbsp;
            <button class="btn btn-primary" type="button" onclick="javascript:history.back()">取消</button>
        </p>
    </form>
</div>
