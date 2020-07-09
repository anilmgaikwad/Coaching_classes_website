<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Guru Coaching Classes Home Page</title>
	<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>

	<hr>
	
	<p>
	<h2>Welcome to Guru Coaching Classes!</h2>
	</p>
	
	<hr>
	
	<!-- display user name and role -->
	
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
	</p>
	
	<hr>
	
	
	<security:authorize access="hasRole('TEACHER')">
	<!-- Add a link to point to /teachers ... this is for the teachers -->
	
	<p>
		<a href="${pageContext.request.contextPath}/teachers">Teacher's Dashboard </a>
		(Only for Teacher peeps)
	</p>
	</security:authorize>
	
	
	
	<security:authorize access="hasRole('ADMIN')">
	<!-- Add a link to point to /systems ... this is for the admins -->
		<hr>
	<p>
		<a href="${pageContext.request.contextPath}/admins">Admin's Dashboard</a>
		(Only for Admin peeps)
	</p>
	</security:authorize>
	
	
	
	<security:authorize access="hasRole('STUDENT')">
		<!-- Add a link to point to /students -->
		<hr>
	<p>
		<a href="${pageContext.request.contextPath}/students">Students Access</a>
		(Only for Students peeps)
	</p>
	</security:authorize>
	
	<hr>
	
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









