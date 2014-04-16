<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet" media="screen">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/message_fr.js"></script>
<script type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>
<script type="text/javascript">
		$(function() {
			 $.datepicker.setDefaults($.datepicker.regional['<spring:message code="label.regional" />']);
			 $(".datepicker").datepicker();
			 $(".datepicker").datepicker('option', 'dateFormat', '<spring:message code="label.pattern" />');
		});
</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Computer Database</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="dashboard"><spring:message
								code="label.dashboard" /></a></li>
					<li><a href="addcomputer"><spring:message
								code="label.addComputer" /></a></li>
					<li><a href="?lang=en">en</a></li>
					<li><a href="?lang=fr">fr</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>