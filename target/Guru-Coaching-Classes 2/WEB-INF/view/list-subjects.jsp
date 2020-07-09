<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Subjects</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>List of Subjects</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="Add Subject"
				onclick="window.location.href='showSubjectFormForAdd'; return false;"
				class="add-button" />
			<!--  add a search box -->
			<form:form action="search" method="GET">
				Search subject: <input type="text" name="theSearchName" />

				<input type="submit" value="Search" class="add-button" />
			</form:form>

			<!--  add our html table here -->
			<table>
				<tr>
					<th>Subject Name</th>
					<th>Subject Syllabus term1</th>
					<th>Subject Syllabus term2</th>
					<th>Subject Syllabus term3</th>
					<th>Subject Syllabus term4</th>
					<th>Courses</th>
					<th>Teachers</th>
					<th>Presenty Records</th>
					<th>Action</th>
					<c:forEach var="tempSubject" items="${subjects}">

						<!-- construct an "update" link with customer id -->
						<c:url var="updateLink"
							value="/subjectwrapper/showSubjectFormForUpdate">
							<c:param name="subjectId" value="${tempSubject.id}" />
						</c:url>
						<!-- construct an "delete" link with customer id -->
						<c:url var="deleteLink" value="/subjectwrapper/delete">
							<c:param name="subjectId" value="${tempSubject.id}" />
						</c:url>
						<!-- construct an "delete" link with customer id -->
						<c:url var="presentyrecordsLink"
							value="/subjectwrapper/presentyrecords">
							<c:param name="subjectId" value="${tempSubject.id}" />
						</c:url>
						<tr>
							<td>${tempSubject.name}</td>
							<td>${tempSubject.syllabus_term1}</td>
							<td>${tempSubject.syllabus_term2}</td>
							<td>${tempSubject.syllabus_term3}</td>
							<td>${tempSubject.syllabus_term4}</td>
							<td><c:forEach var="aValue"
									items="${tempSubject.courseNames}">
									<c:out value="${aValue}" />
								</c:forEach></td>
							<td><c:forEach var="aValue"
									items="${tempSubject.teacherNames}">
									<c:out value="${aValue}" />
								</c:forEach></td>
							<td><a href="${presentyrecordsLink}">Presenty records</a></td>
							<!-- display the update link -->
							<td><a href="${updateLink}">Update</a> <a
								href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this subject?'))) return false">Delete</a>
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