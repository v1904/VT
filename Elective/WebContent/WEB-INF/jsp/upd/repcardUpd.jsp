<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:setLocale value="${local}" />
	<fmt:setBundle basename="localization.local"/>

	<a href="index.jsp"><fmt:message key="local.messages.main"/></a>
	<br/>
	
	<c:if test="${status != 'Guest'}">
	<c:if test="${status != 'Error'}">
	<c:if test="${status != 'null'}">
	
	<h3><fmt:message key="local.messages.reportcard"/> №<%= request.getParameter("id") %>:</h3>
	<c:forEach items="${repcards}" var="repcard">
		<c:if test="${param.id == repcard.studentId}">
			<c:forEach items="${repcard.grades}" var="grade">
				<fmt:message key="local.messages.course"/>: ${grade.courseId}
				&nbsp; <fmt:message key="local.info.grade"/>: ${grade.grade}
				&nbsp; <fmt:message key="local.info.comment"/>: ${grade.comment}
				<c:if test="${status == 'Admin'}">
					&nbsp;<a href="Controller?command=delete&type=Grade&id=${repcard.studentId}&courseId=${grade.courseId}"><fmt:message key="local.actions.delete"/></a>
				</c:if>
				<br><br>
			</c:forEach>
		</c:if>
	</c:forEach>
	
	<c:if test="${status != 'Student'}">
	
	<form action="Controller" method ="post">
		<input type ="hidden" name ="id" value ="${param.id}"/>
	
		<input type ="hidden" name ="command" value ="update"/>
		<input type ="hidden" name ="type" value ="ReportCard"/>
		<input type ="hidden" name ="studentId" value ="${param.id}"/>
		<fmt:message key="local.messages.course"/>: <input id="courseId" type ="text" name ="courseId" value=<c:out value=""/>>
		<br><br>
		<fmt:message key="local.info.grade"/>: <input id="grade" type ="text" name ="grade" value=<c:out value=""/>>
		<br><br>
		<fmt:message key="local.info.comment"/>: <textarea id="comment" name ="comment"></textarea>
		<br><br>
		<input type ="submit" value ="<fmt:message key="local.actions.addgrade"/>"/>
	</form>
	
	</c:if>
	
	</c:if>
	</c:if>
	</c:if>
	
</body>
</html>