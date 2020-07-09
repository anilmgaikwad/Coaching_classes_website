<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Subject</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>Subject Management</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Subject</h3>
	
		<form:form action="saveSubject" modelAttribute="subjectwrapper" method="POST">
		<form:hidden path="id"/>
			<table>
				<tbody>
					<tr>
						<td><label>Subject name:</label></td>
						<td><form:input path="name" /></td>
					</tr>
				
					<tr>
						<td><label>Syllabus term1:</label></td>
						<td><form:input path="syllabus_term1" /></td>
					</tr>
					<tr>
						<td><label>Syllabus term2:</label></td>
						<td><form:input path="syllabus_term2" /></td>
					</tr>
					<tr>
						<td><label>Syllabus term3:</label></td>
						<td><form:input path="syllabus_term3" /></td>
					</tr>
					<tr>
						<td><label>Syllabus term4:</label></td>
						<td><form:input path="syllabus_term4" /></td>
					</tr>
					<tr>
						<td><label>Courses selection:</label></td>
							<td><form:select multiple="true" path="courseNames">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${courseList}" />
							</form:select></td>
					</tr>
					<tr>
						<td><label>Teachers selection:</label></td>
							<td><form:select multiple="true" path="teacherNames">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${teacherList}" />
							</form:select></td>
					</tr>
<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/subjectwrapper/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










