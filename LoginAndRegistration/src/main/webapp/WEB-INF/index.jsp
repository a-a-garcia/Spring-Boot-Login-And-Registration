<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%> <!-- Tells us meta info, what language we're using.. etc.. we can add edits later. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
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
<title>Authentication</title>
</head>
<body>
<div class="wrapper d-flex align-items-center">
	<form:form modelAttribute="newUser" action="/register">
	  <div class="card">
	  	<div class="card-header">Register</div>
		  <div class="form-group">
		  	<form:errors path="userName" class="error"/>
		    <form:label for="userName" path="userName">User Name</form:label>
		    <form:input type="text" class="form-control" id="userName" path="userName"></form:input>
		  </div>
		  <div class="form-group">
		  	<form:errors path="email" class="error"/>
		    <form:label for="email" path="email">Email</form:label>
		    <form:input type="email" class="form-control" id="email" path="email"></form:input>
		  </div>
		  <div class="form-group">
		  	<form:errors path="password" class="error"/>
		    <form:label for="password1" path="password">Password</form:label>
		    <form:input type="password" class="form-control" id="password1" path="password"></form:input>
		  </div>
		  <div class="form-group">
		  	<form:errors path="confirm" class="error"/>
		    <form:label for="confirm" path="confirm">Confirm Password</form:label>
		    <form:input type="password" class="form-control" id="confirm" path="confirm"></form:input>
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</form:form>
	<form:form modelAttribute="newLogin" action="/login">
		<div class="card">
			<div class="card-header">Log In</div>
			<c:if test="${not empty loginError}">
				    <p class="error">${loginError}</p>
				</c:if>
			  <div class="form-group">
			  	<form:errors path="email" class="error"/>
			    <form:label for="exampleInputEmail1" path="email">Email address</form:label>
			    <form:input type="email" class="form-control" id="exampleInputEmail1" path="email"></form:input>
			  </div>
			  <div class="form-group">
			  	<form:errors path="password" class="error"/>
			    <form:label for="password1" path="password">Password</form:label>
			    <form:input type="password" class="form-control" id="password1" path="password"></form:input>
			  </div>
			  <button type="submit" class="btn btn-primary">Login</button>
		</div>
	</form:form>
</div>
</body>
</html>