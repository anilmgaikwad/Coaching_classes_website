<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Document</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/normalize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>


	<!-- HEADER -->
	<header> <!-- LOGO -->
	<h2 id="page-header">
		<img
			src="${pageContext.request.contextPath}/resources/image/profile.jpg"
			alt="profile pic"> Name | Portfolio
	</h2>
	<!-- NAVIGATION --> <nav id="main-navigation">
	<ul>
		<li><a href="#services">Services</a></li>
		<li><a href="#about">About</a></li>
		<li><a href="#testimonies">Testimonies</a></li>
	</ul>
	</nav> </header>


	<!-- MAIN -->
	<div id="page-main">
		<!-- HERO -->

		<div class="hero">
			<div class="hero-content">
				<h1 class="hero-title">Guru Coaching classes</h1>
				<p>Guides towards your destination</p>
			</div>

		</div>


		<!-- SERVICES -->

		<div id="services" class="site-section">

			<div class="section-header">
				<h2>Key Services</h2>
			</div>
			<div class="site-section-inside">
				<div class="service-box">
					<img
						src="${pageContext.request.contextPath}/resources/image/Courses.png">
					<h4>Our Courses</h4>
					<p>We offer differnt courses for each standard.</p>
				</div>

				<div class="service-box">
					<img
						src="${pageContext.request.contextPath}/resources/image/Teachers.png">
					<h4>Our Staff</h4>
					<p>We have skilled and trained teachers staff.</p>
				</div>

				<div class="service-box">
					<img
						src="${pageContext.request.contextPath}/resources/image/Academics.png">
					<h4>Academics</h4>
					<p>We have plannned academic time table for each subject.</p>
				</div>
			</div>

		</div>



		<!-- ABOUT -->

		<div id="about" class="section-header about">
			<h2>About us</h2>
			<p>Our coaching improves retention of learning, offering
				opportunities to talk about what has been learned and to apply
				learning in to action. Our coaching creates a common approach to
				working together on activities and solving problems and great
				questions and listening encourages students to work together. This
				provides opportunities to learn from each other and develop the
				skills of collaboration and knowledge sharing. Our coaching approach
				offers opportunities for learners to make great choices and
				decisions for themselves, creating personal responsibility and
				accountability. Students are far more likely to follow through their
				own choice, than one you have made for them. By expecting them to
				make a good choice or decision you are also empowering learners to
				do what is right for themselves.</p>
		</div>


		<!-- TESTIMONIALS -->
		<section id="testimonies"
			class="site-section testimonials-section-secondary">
		<div class="site-section-inside">
			<div class="section-header">
				<h2>Testimonials</h2>
			</div>

			<div class="testimonial-box">
				<div class="testimonial-content">First in MTS examination.</div>
				<div class="testimonial-author">
					<img
						src="${pageContext.request.contextPath}/resources/image/students/Student1.png"
						alt="Rob Merrill">
					<h5>Atul Shinde</h5>
					<span>First in MTS examination.</span>
				</div>
			</div>

			<div class="testimonial-box">
				<div class="testimonial-content">First in NTS examination.</div>
				<div class="testimonial-author">
					<img
						src="${pageContext.request.contextPath}/resources/image/students/Student3.png"
						alt="Rob Merrill">
					<h5>Amol Vaidya</h5>
					<span>First in NTS examination</span>
				</div>
			</div>

			<div class="testimonial-box">
				<div class="testimonial-content">Third in MTS examination.</div>
				<div class="testimonial-author">
					<img
						src="${pageContext.request.contextPath}/resources/image/students/student2.png"
						alt="Rob Merrill">
					<h5>Nilam Doke</h5>
					<span>Third in MTS examination</span>
				</div>
			</div>
		</div>
		</section>



	</div>
	<div>
		<!-- Add a logout button -->
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">

			<input type="submit" value="Logout" />

		</form:form>
	</div>
	<!-- FOOTER -->
	<footer id="footer"> <img
		src="${pageContext.request.contextPath}/][']/image/phone.png"
		alt="my avatar"> <span>Contact us:9881793577<br />&copy;
		2020
	</span> </footer>
</body>
</html>