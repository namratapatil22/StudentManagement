
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #563d7c">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Student Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Students</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${student != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${student == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${student != null}">
            			Update Student
            		</c:if>
						<c:if test="${student == null}">
            			Add New Student
            		</c:if>
					</h2>
				</caption>

				<c:if test="${student != null}">
					<input type="hidden" name="id" value="<c:out value='${student.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Enter Full Name</label> <input type="text"
						value="<c:out value='${student.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Date of Birth</label> <input type="date"
						value="<c:out value='${student.dob}' />" class="form-control"
						name="dob">
				</fieldset>

				<fieldset class="form-group">
					<label>Date of Joining</label> <input type="date"
						value="<c:out value='${student.doj}' />" class="form-control"
						name="doj">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>