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
<body onload="hide()">
	
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="beans.*" %>
	<%@ page import="myapp.*" %>
	<%
		Object temp = new ArrayList<Teacher>();
		Service.GetTeachers((ArrayList<Teacher>)temp);
		session.setAttribute("teachers", temp);
		if(session.getAttribute("status") == null || session.getAttribute("status").toString().equals("Error"))
			session.setAttribute("status", MyUserStruct.MyStatus.Guest.name());
	%>
	
	<fmt:setLocale value="${local}" />
	<fmt:setBundle basename="localization.local"/>

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.2.min.js"></script>

	<script type="text/javascript">
		function hide()
		{
			var select = document.getElementById('teacher');
        	select.style.visibility = 'hidden';
		}
		function visible()
		{
			var select = document.getElementById('teacher');
        	select.style.visibility = 'visible';
		}
	
        $(document).ready(function(){
            $("select").change(function(){
                var str=$(this).val();
                if(str == 'Student' || str == 'Teacher')
                {
                	hide();                	
                    var title1="<fmt:message key="local.info.name"/>: ";
                    var title2="<fmt:message key="local.info.lastname"/>: "; 
                    var title3="<fmt:message key="local.info.age"/>: "; 
                    $(".title1").html(title1);
                    $(".title2").html(title2);
                    $(".title3").html(title3);
                    $("#lastName").attr("type", "text");
                	$("#age").attr("type", "text");
                }
                else if(str == 'Course')
                {
                	visible();                	
                	var title1="<fmt:message key="local.info.c_name"/>: ";
                	var title2=""; 
                    var title3=""; 
                    $(".title1").html(title1);
                    $(".title2").html(title2);
                    $(".title3").html(title3);
                	$("#lastName").attr("type", "hidden");
                	$("#age").attr("type", "hidden");
                }
            });
        });         
    </script>

	<form action="Controller" method ="post">
		<input type ="hidden" name ="command" value ="create"/>
		<select id="type" name="type">
			<option value="Student" selected><fmt:message key="local.messages.student"/></option>
    		<option value="Teacher"><fmt:message key="local.messages.teacher"/></option>
    		<c:if test="${teachers.size() != 0}">
    			<option value="Course"><fmt:message key="local.messages.course"/></option>
    		</c:if>
  		</select>
			<br/><br/>
			<select id="teacher" name="teacher">
				<c:forEach items="${teachers}" var="teacher">
					<option value="${teacher.id}">${teacher}</option>
				</c:forEach>
  			</select>
  			<br/><br/>	  <span class="title1"><fmt:message key="local.info.name"/>: </span><br/>
		<input id="name" type ="text" name ="name" value=""/>
			<br/><br/><span class="title2"><fmt:message key="local.info.lastname"/>: </span><br/>
		<input id="lastName" type ="text" name ="lastName" value ="" />
			<br/><br/><span class="title3"><fmt:message key="local.info.age"/>: </span><br/>
		<input id="age" type ="text" name ="age" value ="" />
			<br/><br/>
		<input type ="submit" value ="<fmt:message key="local.actions.send"/>"/>
	</form>
</body>
</html>