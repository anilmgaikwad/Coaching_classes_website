<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Students</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>List of Students</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="Add Student"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />
			<!--  add a search box -->
			<form:form action="search" method="GET">
				Search student: <input type="text" name="theSearchName" />

				<input type="submit" value="Search" class="add-button" />


			</form:form>
			<p>
				<button onclick="sortTable()">Sort by</button>
				<select id="filterOption">
					<option value="0">Name</option>
					<option value="1">Roll Number</option>
				</select>
			</p>
			<!--  add our html table here -->
			<table id="myTable">
				<tr>
					<th>Student Name</th>
					<th>Student Roll Number</th>
					<th>Student Courses</th>
					<th>Student Standard batches</th>
					<th>Action</th>
					<c:forEach var="tempStudent" items="${students}">

						<!-- construct an "update" link with customer id -->
						<c:url var="updateLink" value="/studentwrapper/showFormForUpdate">
							<c:param name="studentId" value="${tempStudent.id}" />
						</c:url>
						<!-- construct an "delete" link with customer id -->
						<c:url var="deleteLink" value="/studentwrapper/delete">
							<c:param name="studentId" value="${tempStudent.id}" />
						</c:url>
						<c:url var="feeTableLink" value="/studentwrapper/feeTable">
							<c:param name="studentId" value="${tempStudent.id}" />
						</c:url>
						<tr>
							<td>${tempStudent.full_name}
							<td>${tempStudent.roll_number}
							<td><c:forEach var="listValue"
									items="${tempStudent.courseNames}">
									<li>${listValue}</li>
								</c:forEach>
							<td><c:forEach var="listValue"
									items="${tempStudent.standardBatches}">
									<li>${listValue}</li>
								</c:forEach>
							<td>
								<!-- display the update link --> <a href="${updateLink}">Update</a>
								<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
								<security:authorize access="hasRole('ADMIN')">
								<a href="${feeTableLink}">Fee table</a>
								</security:authorize>
						</tr>
					</c:forEach>
			</table>
			<script>
function sortTable() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("myTable");
  switching = true;
  /*Make a loop that will continue until
  no switching has been done:*/
  
  var filterOption = document.getElementById("filterOption");
  var val = filterOption.value;
  
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("TD")[val];
      y = rows[i + 1].getElementsByTagName("TD")[val];
      //check if the two rows should switch place:
      if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
        //if so, mark as a switch and break the loop:
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}
</script>
			<security:authorize access="hasRole('TEACHER')">
				<!-- Add a link to point to /teachers ... this is for the teachers -->

				<p>
					<a href="${pageContext.request.contextPath}/teachers">Teacher's
						Dashboard </a> (Only for Teacher peeps)
				</p>
			</security:authorize>



			<security:authorize access="hasRole('ADMIN')">
				<!-- Add a link to point to /systems ... this is for the admins -->
				<hr>
				<p>
					<a href="${pageContext.request.contextPath}/admins">Admin's
						Dashboard</a> (Only for Admin peeps)
				</p>
			</security:authorize>
		</div>
	</div>
</body>
</html>