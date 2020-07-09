<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		<h3>Save Exam Result</h3>

		<form:form action="createExamResult" modelAttribute="teacherwrapper"
			method="POST">
			<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>Select Subject:</label></td>
						<td><form:select multiple="true"
								path="examDetails.subjectName">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${teacherwrapper.subjectNames}" />
							</form:select></td>


					</tr>

					<tr>
						<td><label>Select Exam:</label></td>
						<td><form:select multiple="false"
								path="examDetails.examName">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${examNames}" />
							</form:select></td>


					</tr>

					<tr>
						<td><label>Set Total Marks:</label></td>
						<td><form:input path="examDetails.totalmarks" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<div style=""></div>

		<p>
			<a href="${pageContext.request.contextPath}/teacherwrapper/manageExamResult">Back
				to List</a>
		</p>

	</div>

</body>

</html>










