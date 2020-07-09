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
			<form:hidden path="id" />
			<table>
				<tbody>

					<tr>
						<td><label>Date of Presenty:</label></td>
						<td><fmt:formatDate value="${date_of_day}" var="dateString"
								pattern="dd/MM/yyyy" /> <form:input path="date_of_day"
								value="${date_of_day}" /></td>
					</tr>
					<tr>
						<%-- <td><form:label path="presentStudentList">Student Attendance</form:label></td>
						<td><form:checkboxes
								items="${presentyrecordwrapper.studentNames}"
								path="presentStudentList" /></td> --%>

					</tr>
					<tr>
						<c:forEach var="presentywrapper" items="${presentywrappers}">
							<tr>
							
								<input type="checkbox"
									value="${presentywrapper.studentName}"> <c:out
										value="${presentywrapper.status}" />
								</input>
							
							</tr>
					</c:forEach>
</tr>
					<%-- 	<c:forEach items="${presentywrappers}" var="presentywrapper" varStatus="i">
						<tr>
							<td><c:out value="${presentywrapper.studentName}" /></td>
							<td><form:checkbox path="presentywrapper.status" value="Present" />Present
							</td>
													</tr>
					</c:forEach> --%>

					<%-- 	<c:forEach items="${presentywrappers}" var="presentywrapper" 
							varStatus="loop">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input"
									id="presentywrapper.id" name="presentywrapper.name"> <label
									class="custom-control-label" for="customCheck1">${presentywrapper.status}</label>
							</div>
						</c:forEach> --%>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>



			<%-- <c:forEach var="presentywrapper" items="${presentywrappers}">
				<div>

					<input type="checkbox" value="${presentywrapper.studentName}">
					<c:if test="${presentywrapper.status}">checked</c:if>
					</input>

				</div>
			</c:forEach>
 --%>
		</form:form>

		<div style=""></div>

		<p>
			<a href="${pageContext.request.contextPath}/subjectwrapper/list">Back
				to List</a>
		</p>

	</div>

</body>

</html>










