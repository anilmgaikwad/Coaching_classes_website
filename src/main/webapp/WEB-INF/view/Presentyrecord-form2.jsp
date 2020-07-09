<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<title>Save Presenty record</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Presenty record Management</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Presenty record</h3>

		<form:form action="savePresentyrecord"
			modelAttribute="presentyrecordwrapper" method="POST">
			<form:hidden path="id" />
			<form:hidden path="subjectId" />
			<br />
			<p>

				<fmt:formatDate value="${date_of_day}" var="date_of_day"
					pattern="dd/MM/yyyy" />
				Date of Presenty:
				<form:input path="date_of_day" value="${date_of_day}"
					id="datepicker" />
			</p>

			<input type=checkbox name='selectAllCheck'
				onClick='javascript:funcSelectAll()' value='Select All'>Select All </input>

			<TABLE border="1">
				<tr>
					<th><B>Save Presenty record </B></th>
				</tr>

				<c:forEach var="presentywrapper" items="${presentywrappers}">
					<tr>
						<td><input type=checkbox
							name="${presentywrapper.studentName}"> <c:out
								value="${presentywrapper.status}" /> </input></td>
					</tr>
				</c:forEach>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save" /></td>
				</tr>
				</form:form>

				<div style=""></div>

				<p>
					<a href="${pageContext.request.contextPath}/subjectwrapper/list">Back
						to List</a>
				</p>

				</div>
</body>
<script language="javascript">
	function funcSelectAll() {
		if (document.forms[0].selectAllCheck.checked == true) {
			for (var a = 0; a < document.forms[0].lang.length; a++) {
				document.forms[0].lang[a].checked = true;
			}
		} else {
			for (var a = 0; a < document.forms[0].lang.length; a++) {
				document.forms[0].lang[a].checked = false;
			}
		}

	}
</script>

</html>










