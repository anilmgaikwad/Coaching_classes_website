<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Save Course</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Course Management</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Course</h3>

		<form:form action="saveCourse" modelAttribute="coursewrapper"
			method="POST">
			<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>Course title:</label></td>
						<td><form:input path="title" /></td>
					</tr>

					<tr>
						<td><label>Course fee:</label></td>
						<td><form:input path="fee" /></td>
					</tr>

					<tr>
						<td><label>Course standard:</label></td>
							<td><form:select multiple="true" path="standardNames">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${standardList}" />
							</form:select></td>

						<%-- <form:select path="standard" name="standard">
							<c:forEach var="item" items="${standardList}">
								<option class="Standard" value = "${item.key.id}" > ${item.value}</option>
							</c:forEach>
						</form:select> --%>
						
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
			<a href="${pageContext.request.contextPath}/coursewrapper/list">Back
				to List</a>
		</p>

	</div>

</body>

</html>










