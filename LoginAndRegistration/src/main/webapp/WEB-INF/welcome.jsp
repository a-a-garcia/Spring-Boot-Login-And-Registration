<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%> <!-- Tells us meta info, what language we're using.. etc.. we can add edits later. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/js/script.js"></script>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Authentication - Login and Registration</title>
</head>
<body>
	<div class="wrapper">
		<div class="card">
			<div class="card-header">
				<h2>Welcome, <c:out value="${user.userName}">!</c:out></h2>
			</div>
			<div class="card-body">
				<p>This is your dashboard. Nothing to see here yet.</p>
				<a href="/logout">logout</a>
			</div>
		</div>	
	</div>
</body>
</html>