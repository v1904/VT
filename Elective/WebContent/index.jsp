<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

	<style>
	  	.box {
        	display: flex;
       		flex-wrap: wrap;
        	flex-direction: row;
        	margin:-10px;
    	}
    	.box>* {
        	margin: 5px;
      	}
	</style>

</head>
<body>
	<%@ page import="myapp.*" %>
	<%
		if(session.getAttribute("status") == null || session.getAttribute("status").toString().equals("Error"))
			session.setAttribute("status", MyUserStruct.MyStatus.Guest.name());
	%>
	<fmt:setLocale value="${local}" />
	<fmt:setBundle basename="localization.local"/>
	<div class="box">
		<div align="left" style="flex-basis: 85%">
			<h3 style="margin-left: 5%">
				<fmt:message key="local.messages.hello"/>, 
				<c:choose>
					<c:when test="${status == 'Student'}">
        				<fmt:message key="local.types.student"/> №${userId}
    				</c:when>
    				<c:when test="${status == 'Teacher'}">
        				<fmt:message key="local.types.teacher"/> №${userId}
    				</c:when>
    				<c:when test="${status == 'Admin'}">
       			 		<fmt:message key="local.types.admin"/>
    				</c:when>
    				<c:otherwise>
        				<fmt:message key="local.types.guest"/>
    				</c:otherwise>
				</c:choose>
			</h3>
		</div>
		<div>
			<fmt:message key="local.locbutton.name.ru" var="ru_button" />
			<form action="Controller" method="post">
				<input type ="hidden" name ="command" value ="local"/>
				<input type="hidden" name="local" value="ru" />
				<input type="submit" value="${ru_button}" /><br />
			</form>
		</div>
		<div>
			<fmt:message key="local.locbutton.name.en" var="en_button" />
			<form action="Controller" method="post">
				<input type ="hidden" name ="command" value ="local"/>
				<input type="hidden" name="local" value="en" />
				<input type="submit" value="${en_button}" /><br />
			</form>
		</div>
	</div>
	
	<ul>
		<li><a href="authorize"><fmt:message key="local.actions.log_in"/></a></li>
		<c:if test="${status == 'Admin'}">
			<li><a href="create"><fmt:message key="local.actions.create"/></a></li>
			<li><a href="update"><fmt:message key="local.actions.update"/></a></li>
		</c:if>
		<c:if test="${status == 'Student'}">
			<li><a href="Controller?command=studentHome"><fmt:message key="local.actions.update"/></a></li>
		</c:if>
		<c:if test="${status == 'Teacher'}">
			<li><a href="Controller?command=teacherHome"><fmt:message key="local.actions.update"/></a></li>
		</c:if>
		<li><a href="read"><fmt:message key="local.actions.read"/></a></li>
		
	</ul>
</body>
</html>