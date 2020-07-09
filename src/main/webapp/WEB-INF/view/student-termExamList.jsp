<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Exams</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>List of Exams</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
	
			<!--  add a search box -->
			<form:form action="search" method="GET">
			
			</form:form>
			
			<!--  add our html table here -->
			<table>
				<tr>
					<th>Exam Name</th>
		
					<th>Action</th>
					<c:forEach var="tempExam" items="${studentwrapper.termExamsList}">
					
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/studentwrapper/showMyExamResult">
						<c:param name="termExamId" value="${tempExam.id}" />
						<c:param name="studentId" value="${studentwrapper.id}" />
					</c:url>
						<tr>
							<td>${tempExam.name}
							
						
							<td>
							<!-- display the update link -->
							<a href="${updateLink}">Get Result</a>
							
						</td>
						</tr>
					</c:forEach>
			</table>
			
			<security:authorize access="hasRole('COMMON')">
				<!-- Add a link to point to /systems ... this is for the admins -->
				
				<p>
					<a href="${pageContext.request.contextPath}/students"> Back to Student's
						Dashboard</a> 
				</p>
			</security:authorize>
		</div>
	</div>
</body>
</html>