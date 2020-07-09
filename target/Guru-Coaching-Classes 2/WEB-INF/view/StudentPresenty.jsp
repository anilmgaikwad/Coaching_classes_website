<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Presenty</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Student Presenty</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<form:form action="saveStudentPresenty"
				modelAttribute="presentyrecordwrapper" method="POST">
				<form:hidden path="id" />
				<!--  add our html table here -->
				<table>
				<tbody>
					<tr>
					<%-- <c:url var="updateLink" value="/presentyrecordwrapper/saveStudentPresenty">
							<c:param name="presentyRecordId" value="${presentyrecordwrapper.id}" />
						</c:url>
						<td><form:label path="presentStudentList">Student Attendance</form:label></td>
						<td><form:checkboxes items="${presentyrecordwrapper.studentNames}"
								path="presentStudentList" /></td>
								
									<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a> --%>
							<td><form:label path="presentStudentList">Student Attendance</form:label></td>
						<td><form:checkboxes items="${presentyrecordwrapper.studentNames}"
								path="presentStudentList" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
					
				</table>
				<!-- <input type="submit" value="Submit" /> -->
			</form:form>

		</div>
	</div>
</body>
</html>
