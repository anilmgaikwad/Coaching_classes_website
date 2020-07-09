<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>

<head>
<title>Teachers Home Page</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>
	<h2>Teacher's Dashboard</h2>
	<!-- LOGO -->
	<h2 id="page-header">
		<img
			src="${pageContext.request.contextPath}/resources/image/Teachers.png"
			alt="profile pic">
	</h2>
	<form:form action="saveTeacher2" modelAttribute="teacherwrapper"
		method="POST">
		<form:hidden path="id" />
		<div id="header">
			<h4>Teacher Name: ${teacherwrapper.full_name}</h4>
			<h4>
				User:
				<security:authentication property="principal.username" />
				| Role(s):
				<security:authentication property="principal.authorities" />
			</h4>
		</div>
		<c:url var="createExamResultsLink"
			value="/teacherwrapper/createExamDetails">
			<c:param name="teacherId" value="${teacherwrapper.id}" />
		</c:url>
		<c:url var="listExamResultsLink"
			value="/teacherwrapper/listExamResult">
			<c:param name="teacherId" value="${teacherwrapper.id}" />
		</c:url>
		<c:url var="mySubjectListLink" value="/teacherwrapper/mySubjectList">
			<c:param name="teacherId" value="${teacherwrapper.id}" />
		</c:url>

		<c:url var="listOfvideoLink" value="/teacherwrapper/listSubjectVidoes">
			<c:param name="id" value="${teacherwrapper.id}" />
		</c:url>

		<hr>
		<p>
			<a href="${pageContext.request.contextPath}/studentwrapper/list">Manage
				Students </a> (Only for Teacher peeps)
		</p>


		<p>
			<a href="${mySubjectListLink}">Manage Subjects </a> (Only for Teacher
			peeps)
		</p>

		<p>
			<a href="${pageContext.request.contextPath}/exam/list">Manage
				Exams </a> (Only for Teacher peeps)
		</p>
		<p>
			<a href="${listExamResultsLink}">List Exam result </a> (Only for
			Teacher peeps)
		</p>
		<p>
			<a href="${createExamResultsLink}">Create Exam result </a> (Only for
			Teacher peeps)
		</p>

		<p>
			<a href="${listOfvideoLink}">List Videos </a> (Only for Teacher
			peeps)
		</p>


	</form:form>

	<hr>

	<a href="${pageContext.request.contextPath}/home">Back to Home Page</a>

</body>

</html>









