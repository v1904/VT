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
	<h3><fmt:message key="local.messages.teacher"/> №${userId}:</h3>
	<c:forEach items="${teachers}" var="teacher">
		<c:if test="${userId == teacher.id}">
			<form action="Controller" method ="post">
				<input type ="hidden" name ="command" value ="update"/>
				<input type ="hidden" name ="type" value ="Teacher"/>
				<input type ="hidden" name ="id" value ="${userId}"/>
				<fmt:message key="local.info.name"/>: <input id="name" type ="text" name ="name" value="<c:out value="${teacher.name}"/>">
				<br><br>
				<fmt:message key="local.info.lastname"/>: <input id="lastName" type ="text" name ="lastName" value="<c:out value="${teacher.lastName}"/>">
				<br><br>
				<fmt:message key="local.info.age"/>: <input id="age" type ="text" name ="age" value=<c:out value="${teacher.age}"/>>
				<br>
				<c:forEach items="${teacher.coursesId}" var="courseId">
					<c:forEach items="${coursess}" var="course">
						<c:if test="${course.id == courseId}">
							<br>
							№ <c:out value="${course.id}"/>
							&nbsp; <fmt:message key="local.info.c_name"/>: <c:out value="${course.name}"/>
							&nbsp; <fmt:message key="local.info.schedule"/>: <c:out value="${course.schedule}"/>
						</c:if>
					</c:forEach>
				</c:forEach>
				<br><br>
				<input type ="submit" value ="<fmt:message key="local.actions.change"/>"/>
			</form>
		</c:if>
	</c:forEach>
</body>
</html>