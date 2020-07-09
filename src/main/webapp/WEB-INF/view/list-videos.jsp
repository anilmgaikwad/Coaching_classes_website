<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Videos</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>List of Videos</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">

			<!--  add our html table here -->
			<table>
				<tr>
					<th>Subject Name</th>
					<th>Video Name</th>
					<th>View Status</th>
					<th>Action</th>
					<c:forEach var="tempVideoInfo"
						items="${teacherwrapper.listOfVideos}">

						<!-- construct an "update" link with customer id -->
						<c:url var="updateLink"
							value="/teacherwrapper/showFormForVideoUpdate">
							<c:param name="videoInfoId" value="${tempVideoInfo.id}" />
							<c:param name="teacherwrapperId" value="${teacherwrapper.id}" />
						</c:url>
						<!-- construct an "delete" link with customer id -->
						<c:url var="deleteLink" value="/teacherwrapper/deleteVideo">
							<c:param name="videoInfoId" value="${tempVideoInfo.id}" />
							<c:param name="teacherwrapperId" value="${teacherwrapper.id}" />
						</c:url>
						<tr>
							<td>${tempVideoInfo.subject_name}
							<td>${tempVideoInfo.name}
							<td><c:if test="${tempVideoInfo.view_status}">
								Visible</c:if>
								<c:if test="${!tempVideoInfo.view_status}">
								Hidden</c:if>
								</td>
							<td>
								<!-- display the update link --> <a href="${updateLink}">Update</a>
								<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to remove this video?'))) return false">Delete</a>
							</td>
						</tr>
					</c:forEach>
				<tr>
					<c:url var="addLink" value="/teacherwrapper/showFormForVideoAdd">
						<c:param name="teacherwrapperId" value="${teacherwrapper.id}" />
					</c:url>
					<a href="${addLink}">Add New Video</a>
				</tr>
			</table>
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