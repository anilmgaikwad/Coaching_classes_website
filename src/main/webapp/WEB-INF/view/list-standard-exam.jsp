<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Standards</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>List of Standards</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
	
			<!--  add a search box -->
			<form:form action="search" method="GET">
				Search Exam Results: <input type="text" name="theSearchName" />

				<input type="submit" value="Search" class="add-button" />
			</form:form>
			<!--  add our html table here -->
			<table>
				<tr>
					<th>Standard Name</th>
					<th>Exam Name</th>
		
					<th>Action</th>
					<c:forEach var="tempStandardExamPair" items="${theStandardExamPairList}">
					
			
					<!-- construct an "delete" link with customer id -->
					<c:url var="publishLink" value="/standard/publishExamResult">
						<c:param name="standardId" value="${tempStandardExamPair.standard.id}" />
						<c:param name="examId" value="${tempStandardExamPair.exam.id}" />
					</c:url>
						<tr>
							<td>${tempStandardExamPair.standard.name}
							<td>${tempStandardExamPair.exam.name}
						
							<td>
							<!-- display the update link -->
							
							<a href="${publishLink}"
							   onclick="if (!(confirm('Are you sure you want to publish this result?'))) return false">Publish result</a>
						</td>
						</tr>
					</c:forEach>
			</table>
			<security:authorize access="hasRole('TEACHER')">
				<!-- Add a link to point to /teachers ... this is for the teachers -->

				<p>
					<a href="${pageContext.request.contextPath}/teachers">Teacher's
						Dashboard </a> (Only for Teacher peeps)
				</p>
			</security:authorize>



			<security:authorize access="hasRole('ADMIN')">
				<!-- Add a link to point to /systems ... this is for the admins -->
				<hr>
				<p>
					<a href="${pageContext.request.contextPath}/admins">Admin's
						Dashboard</a> (Only for Admin peeps)
				</p>
			</security:authorize>
		</div>
	</div>
</body>
</html>