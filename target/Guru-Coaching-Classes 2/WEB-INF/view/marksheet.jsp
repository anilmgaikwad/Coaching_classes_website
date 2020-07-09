<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>

<head>
<title>Save Exam Result</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<%-- <link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css"> --%>

<style type="text/css">
body {
	font-family: Arial;
	font-size: 10pt;
}

table {
	border: 1px solid #ccc;
	border-collapse: collapse;
}

table th {
	background-color: #F7F7F7;
	color: #333;
	font-weight: bold;
}

table th, table td {
	padding: 5px;
	border: 1px solid #ccc;
}
</style>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Exam Result Management</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<form:form id="formId" action="NoAction" modelAttribute="marksheet"
				method="POST">
				<form:hidden path="studentId" />
				<br />
				<h3>${marksheet.instituteName}</h3>
				<h4>Name of Exam: ${marksheet.examName}</h4>
				<h4>Name of Student: ${marksheet.studentName}</h4>
				<h4>Roll Number: ${marksheet.rollNumber}</h4>
				<br>

				<table id="student_marksheet" cellspacing="0" cellpadding="0">
					<tr>
						<td style="text-align:left" colspan=${marksheet.col_span}>Name of Institute: ${marksheet.instituteName}</td>
						<td style="text-align:left" colspan=${marksheet.col_span}>Name of Exam: ${marksheet.examName}</td>
					</tr>
					<tr>
						<td style="text-align:left" colspan=${marksheet.col_span}>Name of Student: ${marksheet.studentName}</td>
						<td style="text-align:left" colspan=${marksheet.col_span}>Roll Number: ${marksheet.rollNumber}</td>
					</tr>
					<tr>
						<c:forEach var="aValue" items="${marksheet.row1}">
							<th>${aValue}</th>
						</c:forEach>
					<tr>
						<c:forEach var="aValue" items="${marksheet.row2}">
							<td>${aValue}</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach var="aValue" items="${marksheet.row3}">
							<td>${aValue}</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach var="aValue" items="${marksheet.row4}">
							<td>${aValue}</td>
						</c:forEach>
					</tr>
				</table>
				<c:url var="myExamResultsLink"
					value="/studentwrapper/myExamResultsList">
					<c:param name="studentId" value="${marksheet.studentId}" />
				</c:url>
				<p>
					<a href="${myExamResultsLink}">Back to Exam result List</a>
				</p>
			</form:form>

			<div style=""></div>



		</div>
	</div>
	<br />
	<input type="button" id="btnExport" value="Export" onclick="Export()" />
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
	<script type="text/javascript">
		function Export() {

			html2canvas(document.getElementById('student_marksheet'), {
				onrendered : function(canvas) {
					var data = canvas.toDataURL();
					var docDefinition = {
						content : [ {
							image : data,
							width : 500
						} ]
					};
					pdfMake.createPdf(docDefinition).download("Table.pdf");
				}
			});
		}
	</script>
</body>

</html>










