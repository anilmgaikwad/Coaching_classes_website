<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>

<head>
<title>Save Exam Result</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Exam Result Management</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<h3>Exam Result</h3>

			<form:form action="saveExamResult"
				modelAttribute="examDetailswrapper" method="POST">
				<form:hidden path="id" />
				<form:hidden path="teacher_id" />
				<form:hidden path="subject_id" />
				<br />
				<h3>Exam Name: ${examDetailswrapper.examName}</h3>
				<h3>Total marks: ${examDetailswrapper.totalmarks}</h3>
				<table>
					<tr>
						<th>No.</th>
						<th>Student Name</th>
						<th>Marks obtained</th>
						<th>Presenty Status</th>
						<c:forEach items="${examDetailswrapper.examresultwrappers}"
							var="examresultwrapper" varStatus="status">

							<tr>
								<td align="center">${status.count}</td>
								<td><form:input
										path="examresultwrappers[${status.index}].studentname"
										readonly="true" size="8" disabled="true" /> <form:hidden
										path="examresultwrappers[${status.index}].studentname" /></td>
								<td><input
									name="examresultwrappers[${status.index}].marksobtained"
									value="${examresultwrapper.marksobtained}" /></td>
								<%-- <td><input
								name="examresultwrappers[${status.index}].presentyStatus"
								value="${examresultwrapper.presentyStatus}" /></td> --%>

								<td><form:checkbox
										path="examresultwrappers[${status.index}].presentyStatus"
										value="${examresultwrapper.presentyStatus}" /></td>

							</tr>
						</c:forEach>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				</table>
				<c:url var="listExamResultsLink"
					value="/teacherwrapper/listExamResult">
					<c:param name="teacherId" value="${examDetailswrapper.teacher_id}" />
				</c:url>
				<p>
					<a href="${listExamResultsLink}">Back to List Exam result </a>
				</p>

			</form:form>

			<div style=""></div>



		</div>
	</div>
</body>

</html>










