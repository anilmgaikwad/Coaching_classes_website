<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video Player</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-style.css">
</head>
<body>
	<video width="900" height="750" autoplay id="myVideo">
		<source
			src="${pageContext.request.contextPath}/resources/video/movie.mp4"
			type="video/mp4">
		<source
			src="${pageContext.request.contextPath}/resources/video/movie.ogg"
			type="video/ogg">
		Your browser does not support the video tag.
	</video>
	<p>
		<button onclick="playVid()">Play</button>
		<button onclick="pauseVid()">Pause</button>
	</p>
	<script>
		var x = document.getElementById("myVideo");

		function playVid() {
			x.play();
		}

		function pauseVid() {
			x.pause();
		}
	</script>
</body>
</html>