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
	<c:if test="${param.type == 'Student'}">
		<h3><fmt:message key="local.lists.students"/>:</h3>
		<c:forEach items="${students}" var="student">
			<p>
				№ <c:out value="${student.id}"/>
				&nbsp; <fmt:message key="local.info.name"/>: <c:out value="${student.name}"/>
				&nbsp; <fmt:message key="local.info.lastname"/>: <c:out value="${student.lastName}"/>
				&nbsp; <fmt:message key="local.info.age"/>: <c:out value="${student.age}"/>
				<c:if test="${status=='Teacher'}">
					&nbsp;<a href="Controller?command=repcardUpd&id=${student.id}"><fmt:message key="local.messages.reportcard"/></a>
				</c:if>
				<c:if test="${status=='Admin'}">
					&nbsp;<a href="Controller?command=delete&type=Student&id=${student.id}"><fmt:message key="local.actions.delete"/></a>
				</c:if>
			</p>
		</c:forEach>
	</c:if> 
	<c:if test="${param.type == 'Teacher'}">
		<h3><fmt:message key="local.lists.teachers"/>:</h3>
		<c:forEach items="${teachers}" var="teacher">
			<p>
				№ <c:out value="${teacher.id}"/>
				&nbsp; <fmt:message key="local.info.name"/>: <c:out value="${teacher.name}"/>
				&nbsp; <fmt:message key="local.info.lastname"/>: <c:out value="${teacher.lastName}"/>
				&nbsp; <fmt:message key="local.info.age"/>: <c:out value="${teacher.age}"/>
				<c:if test="${status=='Admin'}">
					&nbsp;<a href="Controller?command=delete&type=Teacher&id=${teacher.id}"><fmt:message key="local.actions.delete"/></a>
				</c:if>
			</p>
		</c:forEach>
	</c:if> 
	<c:if test="${param.type == 'Course'}">
		<h3><fmt:message key="local.lists.courses"/>:</h3>
		<c:forEach items="${coursess}" var="course">
			<p>
				№ <c:out value="${course.id}"/>
				&nbsp; <fmt:message key="local.info.c_name"/>: <c:out value="${course.name}"/>
				&nbsp; <fmt:message key="local.info.schedule"/>: <c:out value="${course.schedule}"/>
				<c:if test="${status=='Student'}">
					&nbsp;<a href="Controller?command=request&id=${course.id}"><fmt:message key="local.actions.sing_up"/></a>
				</c:if>
				<c:if test="${status=='Admin'}">
					&nbsp;<a href="Controller?command=delete&type=Course&id=${course.id}"><fmt:message key="local.actions.delete"/></a>
				</c:if>
			</p>
		</c:forEach>
	</c:if> 
	
	<c:if test="${param.command == 'create'}">
		<h3><fmt:message key="local.actions.createsmtelse"/>?</h3>
		<c:import url="/create"/>
	</c:if>
</body>
</html>