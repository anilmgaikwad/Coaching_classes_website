<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/normalize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style2.css">
</head>
<body>


	<!-- HEADER -->
	<header>
		
		<!-- NAVIGATION -->
		<p>
			<a href="${pageContext.request.contextPath}/home">Access
				Secure Site (requires login)</a>
		</p>

		<nav id="main-navigation">
			<ul>
				<li><a href="#services">Services</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#testimonies">Testimonies</a></li>
			</ul>
		</nav>


	</header>


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
					<a href="${pageContext.request.contextPath}/coursewrapper/list">Course
						link</a>
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
							src="${pageContext.request.contextPath}/resources/image/students/student1.png"
							alt="Rob Merrill">
						<h5>Sunil Patil</h5>
						<span>Title</span>
					</div>
				</div>

				<div class="testimonial-box">
					<div class="testimonial-content">First in NTS examination.</div>
					<div class="testimonial-author">
						<img
							src="${pageContext.request.contextPath}/resources/image/students/student3.png"
							alt="Rob Merrill">
						<h5>Atul Shinde</h5>
						<span>Title</span>
					</div>
				</div>

				<div class="testimonial-box">
					<div class="testimonial-content">Third in NTS examination.</div>
					<div class="testimonial-author">
						<img
							src="${pageContext.request.contextPath}/resources/image/students/student2.png"
							alt="Rob Merrill">
						<h5>Nilam Doke</h5>
						<span>Title</span>
					</div>
				</div>
			</div>
		</section>



	</div>

	<!-- FOOTER -->
	<footer id="footer">
		<img
			src="${pageContext.request.contextPath}/resources/image/phone.png"
			alt="my avatar"> <span>Contact us:9881793577<br />&copy;
			2020
		</span>
	</footer>


</body>
</html>


