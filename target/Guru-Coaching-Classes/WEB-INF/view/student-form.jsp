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
<style>
.error {
	color: red
}
</style>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Student Registration</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Student</h3>

		<form:form action="savestudent2" modelAttribute="studentwrapper"
			method="POST">
			<form:hidden path="id" />
			
						<!-- Place for messages: error, alert etc ... -->
			<div class="form-group">
				<div class="col-xs-15">
					<div>

						<!-- Check for registration error -->
						<c:if test="${registrationError != null}">

							<div class="alert alert-danger col-xs-offset-1 col-xs-10">
								${registrationError}</div>

						</c:if>

					</div>
				</div>
			</div>
			
			<table>
				<tbody>
					<tr>
						<td><label> First Name:</label></td>
						<td><form:input path="first_name" /></td>

						<td><label> Middle Name:</label></td>
						<td><form:input path="middle_name" /></td>

						<td><label> Last Name:</label></td>
						<td><form:input path="last_name" /></td>
					</tr>

					<tr>
						<td><label> Gender:</label></td>
						<td><form:select path="gender">
								<form:option value="Male" label="Male" />
								<form:option value="Female" label="Female" />
							</form:select></td>
					</tr>

					<tr>
						<td><label> Roll Number:</label></td>
						<td><form:input path="roll_number" /></td>
					</tr>
					<tr>
						<td><label>Mobile Number 1:</label></td>
						<td><form:input path="mobile_number1" /></td>
						
							<td><label>Mobile Number 2:</label></td>
						<td><form:input path="mobile_number2" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" /></td>
					</tr>
					<tr>
						<td><label>Date of Birth:</label></td>
						<td><fmt:formatDate value="${address.dateOfBirth}"
								var="address.dateOfBirth" pattern="dd/MM/yyyy" /> <form:input
								path="dateOfBirth" value="${address.dateOfBirth}"
								id="datepicker" /></td>
					</tr>
					<%--  <tr class="controls">
            <input type="text" path="dateOfBirth" class= "date" name = "dateOfBirth" value = "<fmt:formatDate value="" pattern="dd-mm-yyyy" />"/>
        </tr> --%>

					<tr>
						<td><label> Building Name/Lane:</label></td>
						<td><form:input path="address.line1" /></td>

						<td><label> Street/Nagar:</label></td>
						<td><form:input path="address.line2" /></td>

						<td><label> Village/City:</label></td>
						<td><form:input path="address.city" /></td>
					</tr>

					<tr>
						<td><label> Taluka:</label></td>
						<td><form:input path="address.taluka" /></td>

						<td><label> Pincode:</label></td>
						<td><form:input path="address.pincode" /></td>

						<td><label> State:</label></td>
						<td><form:input path="address.state" /></td>
					</tr>

					<tr>
						<td><label>Courses:</label></td>
						<td><form:select multiple="true" path="courseNames">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${courseList}" />
							</form:select></td>


					</tr>

					<tr>
						<td><label> User Name:</label></td>
						<form:errors path="username" cssClass="error" />
						<td><form:input path="username" /></td>

						<td><label> Password:</label></td>
						<td><form:errors path="password" cssClass="error" />
						<form:password path="password" placeholder="password (*)" class="form-control" /></td>

						<td><label> Re-enter Password:</label></td>
						<td><form:errors path="matchingPassword" cssClass="error" />
						<form:password path="matchingPassword" placeholder="matchingPassword (*)" class="form-control" /></td>
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