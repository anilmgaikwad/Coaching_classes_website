<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Save Presenty record</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Presenty record Management</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Presenty record</h3>

		<form:form action="savePresentyrecord"
			modelAttribute="presentyrecordwrapper" method="POST">
			<form:hidden path="subjectId" />
			<%-- <form:hidden path="id" /> --%>
			<table>
				<tbody>
					
					<tr>
						<td><label>Date of Presenty:</label></td>
						<td><fmt:formatDate value="${date_of_day}" var="dateString"
								pattern="dd/MM/yyyy" /> <form:input path="date_of_day"
								value="${date_of_day}" /></td>
					</tr>
					<%-- <tr>
						<td><form:label path="presentStudentList">Student Attendance</form:label></td>
						<td><form:checkboxes
								items="${presentyrecordwrapper.studentNames}"
								path="presentStudentList" /></td>
					</tr> --%>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<div style=""></div>

		<p>
			<a
				href="${pageContext.request.contextPath}/subjectwrapper/list">Back
				to List</a>
		</p>

	</div>

</body>

</html>










