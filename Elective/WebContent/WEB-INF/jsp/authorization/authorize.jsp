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

 	<h3><fmt:message key="local.messages.sing_in"/></h3>
	<c:if test="${status == 'Error'}">
		<h4 style="color:red"><fmt:message key="local.messages.wrongpol"/></h4>
	</c:if>
	<form action="Controller" method ="post">
		<input type ="hidden" name ="command" value ="authorize"/>
		
		<fmt:message key="local.messages.login"/>: <input id="login" type ="text" name ="login" value=""/>
		<br/><br/>
		<fmt:message key="local.messages.password"/>: <input id="password" type ="text" name ="password" value ="" />
		<br/><br/>
		<input type ="submit" value ="<fmt:message key="local.actions.send"/>"/>
	</form>
</body>
</html>