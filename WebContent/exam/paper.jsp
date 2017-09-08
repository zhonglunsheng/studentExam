<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	var examTime = 20*60;
	var userTime= 0,remainTime=examTime;

	function showCount() {
        if (remainTime<0){
            $("#myForm").submit();
        }
		userTime+=1;
		remainTime-=1;

		var hourU = Math.floor(userTime/3600);
		var minuteU = Math.floor((userTime-hourU*3600)/60);
		var secondU = Math.floor((userTime-minuteU*60-hourU*3600));
        document.getElementById("useTime").innerHTML=format(hourU)+":"+format(minuteU)+":"+format(secondU);

        var hourR = Math.floor(remainTime/3600);
        var minuteR = Math.floor((remainTime-hourR*3600)/60);
        var secondR = Math.floor((remainTime-minuteR*60-hourU*3600));
        document.getElementById("remainTime").innerHTML=format(hourR)+":"+format(minuteR)+":"+format(secondR);

    }
    
    function format(timeNumber) {
		if (timeNumber<10){
			return "0"+timeNumber;
		}
			return timeNumber;
    }

    window.setInterval(showCount,1000);
</script>
</head>
<body>
<div class="data_list">
	<div class="data_info">
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;考试时间：<strong>20分钟</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			计时：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="useTime" style="font-weight: bold;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			剩余时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="remainTime" style="font-weight: bold;"></span></p>
		<hr/>
		<p class="examTitle">${paper.paperName}&nbsp;&nbsp;考试卷</p>
		<p class="examScoreInfo">(&nbsp;满分120&nbsp;&nbsp;单选题60分&nbsp;&nbsp;多选题60分&nbsp;)</p>
	</div>
	<div class="data_exam_content">
		<form action="exam!add" method="post" id="myForm">
			<input type="hidden" name="exam.paper.id" value="${paper.id}"/>
			<input type="hidden" name="exam.student.id" value="${currentStu.id}"/>
		<strong><big>一，单选题</big></strong>(每题20分，答错不得分)<br/><br/>
		<c:forEach var="s" items="${squestionList}" varStatus="status">
			<strong>[&nbsp;${status.index+1 }&nbsp;]&nbsp;${s.subject }</strong><br/><br/>
			<label class="radio">
				<input type="radio" name="id-r-${s.id }" value="A"/>
				${s.optionA }
			</label>
			<label class="radio">
				<input type="radio" name="id-r-${s.id }" value="B"/>
				${s.optionB }
			</label>
			<label class="radio">
				<input type="radio" name="id-r-${s.id }" value="C"/>
				${s.optionC }
			</label>
			<label class="radio">
				<input type="radio" name="id-r-${s.id }" value="D"/>
				${s.optionD }
			</label>
			<br/>
		</c:forEach>
		<br/>
		<strong><big>一，多选题</big></strong>(每题30分，答错不得分)<br/><br/>
		<c:forEach var="m" items="${mquestionList}" varStatus="status">
			<strong>[&nbsp;${status.index+1 }&nbsp;]&nbsp;${m.subject }</strong><br/><br/>
			<label class="checkbox">
				<input type="checkbox" name="id-c-${m.id }" value="A"/>
				${m.optionA }
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-${m.id }" value="B"/>
				${m.optionB }
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-${m.id }" value="C"/>
				${m.optionC }
			</label>
			<label class="checkbox">
				<input type="checkbox" name="id-c-${m.id }" value="D"/>
				${m.optionD }
			</label>
			<br/>
		</c:forEach>
		<button class="btn btn-primary" type="submit">交卷</button>
		</form>
	</div>
</div>
</body>
</html>