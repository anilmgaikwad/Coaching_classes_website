<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Save Student</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
</script>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Student Management</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Student</h3>

		<form:form action="savefeeinstallment" modelAttribute="studentwrapper"
			method="POST">
			<form:hidden path="id" />
			<form:hidden path="theFeeinstallmentwrapper.id" />
			<table>
				<tbody>

					<tr>
						<td><label>Amount:</label></td>
						<td><form:input path="theFeeinstallmentwrapper.amount" /></td>
					</tr>


					<tr>
						<td><label>Date of day:</label></td>
						<td><fmt:formatDate
								value="${theFeeinstallmentwrapper.date_of_day}"
								var="theFeeinstallmentwrapper.date_of_day" pattern="dd-MM-yyyy"
								type="both" /> <form:input type="text"
								path="theFeeinstallmentwrapper.date_of_day"
								value="${theFeeinstallmentwrapper.date_of_day}" id="datepicker" />
						</td>

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
			<a href="${pageContext.request.contextPath}/studentwrapper/list">Back
				to List</a>
		</p>

	</div>
</body>
</html>