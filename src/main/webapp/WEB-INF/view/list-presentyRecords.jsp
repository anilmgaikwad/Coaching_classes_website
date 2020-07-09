<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Presenty records</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>List of Presenty records</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<!--  add a search box -->
			<form:form action="search" method="GET">
				Search Presenty record: <input type="text" name="theSearchName" />

				<input type="submit" value="Search" class="add-button" />
			</form:form>

			<!--  add our html table here -->
			<table>
				<tr>
					<th>Subject Name</th>
					<th>Date</th>
					<th>Action</th>
					<c:forEach var="tempPresentyrecord"
						items="${subjectwrapper.presentyrecordwrappers}">

						<!-- construct an "update" link with customer id -->
						<c:url var="updateLink"
							value="/presentyrecordwrapper/showFormForUpdate">
							<c:param name="presentyRecordId" value="${tempPresentyrecord.id}" />
							<c:param name="subjectID" value="${subjectwrapper.id}" />
						</c:url>
						<!-- construct an "delete" link with customer id -->
						<c:url var="deleteLink" value="/presentyrecordwrapper/delete">
							<c:param name="presentyRecordId" value="${tempPresentyrecord.id}" />
							<c:param name="subjectID" value="${subjectwrapper.id}" />
						</c:url>
						<tr>
							<td>${tempPresentyrecord.subjectName}
							<td>${tempPresentyrecord.date_of_day}
							<td>
								<!-- display the update link --> <a href="${updateLink}">Update</a>
								<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this presenty record?'))) return false">Delete</a>
						</tr>
					</c:forEach>
				<tr>
					<c:url var="addLink" value="/presentyrecordwrapper/showFormForAdd">
						<c:param name="subjectwrapperId" value="${subjectwrapper.id}" />
					</c:url>
					<a href="${addLink}">Add New</a>
				</tr>
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
