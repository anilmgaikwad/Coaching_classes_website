<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<title>Save Customer</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Examination Management</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Examination</h3>

		<form:form action="saveExam" modelAttribute="exam" method="POST">
			<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>Exam name:</label></td>
						<td><form:input path="name" /></td>
					</tr>

					<tr>
						<td><label>Exam type:</label></td>
						<td><form:input path="type" /></td>
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
			<a href="${pageContext.request.contextPath}/exam/list">Back to
				List</a>
		</p>

	</div>

</body>

</html>










