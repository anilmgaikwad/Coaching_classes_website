<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<title>Save Video</title>

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
			<h2>Video Management</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Video</h3>

		<form:form action="saveVideoInfo" modelAttribute="teacherwrapper"
			method="POST">
			<form:hidden path="id" />
		
			<form:hidden path="video_id" />
			<br />
			<table>
				<tbody>

					<tr>
						<td><label>Subjects:</label></td>
						<td><form:select path="subjectName_OfVideo">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${teacherwrapper.subjectNames}" />
							</form:select></td>

					</tr>
					<tr>
						<td><label> Video Name:</label></td>
						<td><form:input path="video_Name" /></td>
					</tr>

<tr>
						<td><label> View Status:</label></td>
						<td><form:checkbox path = "bVideoViewStatus" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>

			<c:url var="listOfvideoLink"
				value="/teacherwrapper/listSubjectVidoes">
				<c:param name="id" value="${teacherwrapper.id}" />
			</c:url>

			<p>
				<a href="${listOfvideoLink}">Back to List Videos </a> 
			</p>
		</form:form>

		<div style=""></div>



	</div>

</body>

</html>










