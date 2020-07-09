<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Save Teacher</title>

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
		<h3>Save Teacher</h3>

		<form:form action="saveTeacher" modelAttribute="teacherwrapper"
			method="POST">
			<form:hidden path="id" />
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
							<td><form:select  path="gender">
								<form:option value="Male" label="Male" />
								<form:option value="Female" label="Female" />
							</form:select></td>
					</tr>

					<tr>
						<td><label> ID Number:</label></td>
						<td><form:input path="id_number" /></td>
					</tr>
					<tr>
						<td><label>Mobile Number:</label></td>
						<td><form:input path="mobile_number" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" /></td>
					</tr>
					<tr>
						<td><label>Date of Birth:</label></td>
						<td><fmt:formatDate value="${address.dateOfBirth}" var="address.dateOfBirth" pattern="dd/MM/yyyy" />
                        <form:input path="dateOfBirth" value="${address.dateOfBirth}" id="datepicker"/> </td>
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
						<td><label>Subjects:</label></td>
							<td><form:select multiple="true" path="subjectNames">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${subjectList}" />
							</form:select></td>

						
					</tr>
				<%-- 	<tr>
						<td><label> User Name:</label></td>
						<td><form:input path="user.username" /></td>

						<td><label> Password:</label></td>
						<td><form:input path="user.password" /></td>
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
			<a href="${pageContext.request.contextPath}/teacherwrapper/list">Back
				to List</a>
		</p>

	</div>
</body>
</html>