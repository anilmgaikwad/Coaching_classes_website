<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>

<head>
<title>Fee table</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Fee table</h2>
		</div>
	</div>

	<div id="container">
		<h3>Fee table</h3>
		<form:form action="search" method="GET">
			<table>
				
				
					<tr>
						<td><label>Student Name:</label></td>
						<td>"${studentwrapper.full_name}"</td>
					</tr>

					<tr>
						<td><label>Total Fee of courses:</label></td>
						<td>"${studentwrapper.feetable.totalamount}"</td>
					</tr>
					<tr>
						<td><label>Remaining Fee of courses:</label></td>
						<td>"${studentwrapper.feetable.remaingamount}"</td>
					</tr>
					<tr>
						<c:url var="addLink" value="/studentwrapper/addNewFeeinstallment">
							<c:param name="studentId" value="${studentwrapper.id}" />
						</c:url>
						<td><label>Add new installment:</label></td>
						<td><a href="${addLink}">Add New</a></td>
					</tr>


				</tbody>

				<tr>
					<th>Date</th>
					<th>Amount paid</th>
					<th>Action</th>
					<c:forEach var="tempFeeinstallment"
						items="${studentwrapper.feetable.feeinstallments}">
						<!-- construct an "update" link with customer id -->
						<c:url var="updateLink"
							value="/studentwrapper/showFormForUpdateFeeinstallment">
							<c:param name="feeinstallmentId" value="${tempFeeinstallment.id}" />
							<c:param name="studentwrapperId" value="${studentwrapper.id}" />
						</c:url>
						<!-- construct an "delete" link with customer id -->
						<c:url var="deleteLink"
							value="/studentwrapper/deleteFeeInstallment">
							<c:param name="feeinstallmentId" value="${tempFeeinstallment.id}" />
							<c:param name="studentwrapperId" value="${studentwrapper.id}" />
						</c:url>
						<tr>
							<td>${tempFeeinstallment.date_of_day}
							<td>${tempFeeinstallment.amount}
							<td>
								<!-- display the update link --> <a href="${updateLink}">Update</a>
								<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this course?'))) return false">Delete</a>
						</tr>
					</c:forEach>
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










