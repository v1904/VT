<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<style type="text/css"> 
		.selectt { 
			display: none; 
		} 
	</style> 
</head>
<body>
	<fmt:setLocale value="${local}" />
	<fmt:setBundle basename="localization.local"/>
	
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="beans.*" %>
	<%@ page import="myapp.*" %>
	<%
		Object temp = new ArrayList<Teacher>();
		Service.GetTeachers((ArrayList<Teacher>)temp);
		session.setAttribute("teachers", temp);
	%>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.2.min.js"></script>

	<script type="text/javascript"> 
            $(document).ready(function() { 
                $('input[type="checkbox"]').click(function() { 
                    var inputValue = $(this).attr("id"); 
                    $("." + inputValue).toggle(); 
                }); 
            }); 
    </script> 
		
	<h3><fmt:message key="local.messages.course"/> №<%= request.getParameter("id") %>:</h3>
	<c:forEach items="${coursess}" var="course">
		<c:if test="${param.id == course.id}">
			<form action="Controller" method ="post">
				<input type ="hidden" name ="command" value ="update"/>
				<input type ="hidden" name ="type" value ="Course"/>
				<input type ="hidden" name ="id" value ="${param.id}"/>
				<fmt:message key="local.info.name"/>: <input id="name" type ="text" name ="name" value="<c:out value="${course.name}"/>">
				<br><br>
				<fmt:message key="local.messages.teacher"/>: <select id="teacherId" name="teacherId">
					<c:forEach items="${teachers}" var="teacher">
						<option value="${teacher.id}"
							<c:if test="${course.teacherId == teacher.id}">
								selected="selected"
							</c:if>
						>${teacher}</option>
					</c:forEach>
  				</select>
				<br><br>
				<fmt:message key="local.info.schedule"/>: 
				<br>
					<c:forEach items="${course.schedule.days}" var="day">
						<c:choose>
     						<c:when test="${day.checked}">
								${day.day}: <input id="${day.day}" name="${day.day}" type="checkbox" checked="checked">
								<div class="${day.day} selectt" style="display:block"> 
									<br>
									<fmt:message key="local.info.hours"/>: <input id="${day.day}h" name="${day.day}h" value="${day.hours}">
									<fmt:message key="local.info.minutes"/>: <input id="${day.day}m" name="${day.day}m" value="${day.minutes}">
									<br>
								</div>
								<br>
							</c:when>
							<c:otherwise>
								${day.day}: <input id="${day.day}" name="${day.day}" type="checkbox">
								<div class="${day.day} selectt">
									<br> 
									<fmt:message key="local.info.hours"/>: <input id="${day.day}h" name="${day.day}h" value="">
									<fmt:message key="local.info.minutes"/>: <input id="${day.day}m" name="${day.day}m" value="">
									<br>
								</div>
								<br>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				<br>
				<input type ="submit" value ="<fmt:message key="local.actions.change"/>"/>
			</form>
		</c:if>
	</c:forEach>
</body>
</html>