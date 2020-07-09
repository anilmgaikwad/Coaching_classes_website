<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teacher Registration</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
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
	<div>

		<div id="loginbox" style="margin-top: 100px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-12 col-sm-offset-2">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Register New Teacher</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form
						action="${pageContext.request.contextPath}/teacherwrapper/saveTeacher"
						modelAttribute="teacherwrapper" class="form-horizontal">

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



						<!-- First name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:errors path="first_name" cssClass="error" />
							<form:input path="first_name" placeholder="first name (*)"
								class="form-control col-md-2" />
							<span class="input-group-addon">-</span>
							<form:errors path="middle_name" cssClass="error" />
							<form:input path="middle_name" placeholder="middle name (*)"
								class="form-control col-md-2" />
							<span class="input-group-addon">-</span>
							<form:errors path="last_name" cssClass="error" />
							<form:input path="last_name" placeholder="last name (*)"
								class="form-control" />
						</div>

						<!-- Gender: -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:errors path="gender" cssClass="error" />
							<form:select path="gender" class="form-control">
								<form:option value="Male" label="Male" />
								<form:option value="Female" label="Female" />
							</form:select>
							<span class="input-group-addon">-</span>
							<form:errors path="id_number" cssClass="error" />
							<form:input path="id_number" placeholder="id number (*)"
								class="form-control col-xs-2 " />
							<span class="input-group-addon">-</span>
						</div>

						<!-- ID Number: -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>

							<form:errors path="email" cssClass="error" />
							<form:input path="email" placeholder="email (*)"
								class="form-control" />
								
							<span class="input-group-addon">-</span>
							
							<form:errors path="mobile_number" cssClass="error" />
							<form:input path="mobile_number" placeholder="mobile number (*)"
								class="form-control" />
								
							<span class="input-group-addon">-</span>
							
							<form:errors path="qualification" cssClass="error" />
							<form:input path="qualification" placeholder="qualification (*)"
								class="form-control" />
						</div>




						

						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:errors path="crmUser.userName" cssClass="error" />
							<form:input path="crmUser.userName" placeholder="username (*)"
								class="form-control" />
								<span class="input-group-addon">-</span>
								
								<form:errors path="crmUser.password" cssClass="error" />
							<form:password path="crmUser.password" placeholder="password (*)"
								class="form-control" />
								
								<span class="input-group-addon">-</span>
								
								<form:errors path="crmUser.matchingPassword" cssClass="error" />
							<form:password path="crmUser.matchingPassword"
								placeholder="confirm password (*)" class="form-control" />
								
						</div>

						
						<!-- Date of Birth: -->
						<div style="margin-top: 150px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
								<label>Date of Birth:</label>
							<form:errors path="dateOfBirth" cssClass="error" />
							<fmt:formatDate value="${address.dateOfBirth}"
								var="address.dateOfBirth" pattern="dd/MM/yyyy" />
							<form:input path="dateOfBirth" value="${address.dateOfBirth}"
								id="datepicker" />
						</div>


						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</div>

					</form:form>

				</div>

			</div>

		</div>

	</div>

</body>
</html>