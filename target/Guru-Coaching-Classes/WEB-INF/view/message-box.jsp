<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
<title>Save Subject</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">

<style type="text/css">
.topleft {
	position: absolute;
	top: 8px;
	left: 16px;
	font-size: 18px;
}
</style>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Message Box</h2>
		</div>
	</div>

	<div id="container">
		<h3>Post Message</h3>

		<form:form action="postMessage" modelAttribute="message" method="POST">
			<table>
				<tbody>
					<tr>
						<td><label>To:</label></td>
						<td><form:input path="receipents_str"
								style="font-size:12pt;height:20px;width:400px;" /></td>
					</tr>

					<tr>
						<td><label>Message:</label></td>
						<td><form:input path="message" size="150"
								style="font-size:12pt;height:300px;width:400px;text-align:left;"
								 /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Send" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<security:authorize access="hasRole('ADMIN')">
			<!-- Add a link to point to /systems ... this is for the admins -->
			<hr>
			<p>
				<a href="${pageContext.request.contextPath}/admins">Admin's
					Dashboard</a> (Only for Admin peeps)
			</p>
		</security:authorize>

	</div>

</body>

</html>










