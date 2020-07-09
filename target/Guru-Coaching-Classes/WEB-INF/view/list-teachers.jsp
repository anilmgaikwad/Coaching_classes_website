<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Teachers</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>List of Teachers</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="Add Teacher"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />
			<!--  add a search box -->
			<form:form action="search" method="GET">
				Search teacher: <input type="text" name="theSearchName" />

				<input type="submit" value="Search" class="add-button" />
			</form:form>

			<!--  add our html table here -->
			<table>
				<tr>
					<th>Teacher Name</th>
					<th>Teacher ID Number</th>
					<th>Teacher Subjects</th>
					<th>Standard</th>
					<th>Action</th>
					<c:forEach var="tempTeacher" items="${teachers}">

						<!-- construct an "update" link with customer id -->
						<c:url var="updateLink" value="/teacherwrapper/showFormForUpdate">
							<c:param name="teacherId" value="${tempTeacher.id}" />
						</c:url>
						<!-- construct an "delete" link with customer id -->
						<c:url var="deleteLink" value="/teacherwrapper/delete">
							<c:param name="teacherId" value="${tempTeacher.id}" />
						</c:url>
						<tr>
							<td>${tempTeacher.full_name}
							<td>${tempTeacher.id_number}
							<td><c:forEach var="listValue"
									items="${tempTeacher.subjectNames}">
									<li>${listValue}</li>
								</c:forEach>
							<td>${tempTeacher.standardName}
							<td>
								<!-- display the update link --> <a href="${updateLink}">Update</a>
								<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false">Delete</a>
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