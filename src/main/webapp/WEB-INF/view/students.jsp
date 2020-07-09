<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Students Home page</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>
<body>
	<h2>Student's Dashboard</h2>
	<!-- LOGO -->
	<h2 id="page-header">
		<img
			src="${pageContext.request.contextPath}/resources/image/Students.png"
			alt="profile pic">
		<%-- User: <security:authentication property="principal.username" /> | Role(s): <security:authentication property="principal.authorities" /> --%>
	</h2>
	<form:form action="noAction" modelAttribute="studentwrapper"
		method="POST">
		<form:hidden path="id" />
		<div id="header">
			<h4>Student Name: ${studentwrapper.full_name}</h4>
			<h4>
				User:
				<security:authentication property="principal.username" />
				| Role(s):
				<security:authentication property="principal.authorities" />
			</h4>
		</div>
		<c:url var="myExamResultsLink"
			value="/studentwrapper/myExamResultsList">
			<c:param name="studentId" value="${studentwrapper.id}" />
		</c:url>

	<%-- 	<c:url var="myVideoLink" value="/studentwrapper/video">
			<c:param name="studentId" value="${studentwrapper.id}" />
		</c:url> --%>

		<hr>
		<p>
			<a href="${myExamResultsLink}">List my Exam result </a> (Only for Student peeps)
		</p>

		<hr>
	<%-- 	<p>
			<a href="${myVideoLink}">Video </a> (Only for Student peeps)
		</p> --%>


		<table>
			<tr>
				<th>Subject Name</th>
				<th>Video Name</th>
				<th>Action</th>
				<c:forEach var="tempVideoinfo"
					items="${studentwrapper.listOfVideos}">

					<!-- construct an "update" link with customer id -->
					<c:url var="viewLink" value="/studentwrapper/showVideo">
						<c:param name="VideoinfoId" value="${tempVideoinfo.id}" />
						<c:param name="studentId" value="${studentwrapper.id}" />
					</c:url>

					<tr>
						<td>${tempVideoinfo.subject_name}</td>
						<td>${tempVideoinfo.name}</td>
						<td>
							<!-- display the update link --> <a href="${viewLink}">View</a>
					</tr>
				</c:forEach>
		</table>

	</form:form>


	<hr>

	<a href="${pageContext.request.contextPath}/home">Back to Home Page</a>

</body>
</html>