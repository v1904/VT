<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	
	<form action="Controller" method ="get">
		<input type ="hidden" name ="command" value ="baseUpdate"/>
		<select id="type" name="type">
    		<option value="Student"><fmt:message key="local.messages.student"/></option>
    		<option value="Teacher"><fmt:message key="local.messages.teacher"/></option>
    		<option value="Course"><fmt:message key="local.messages.course"/></option>
  		</select>
  		<br/><br/>
		<input type ="submit" value ="<fmt:message key="local.actions.send"/>"/>
	</form>
</body>
</html>