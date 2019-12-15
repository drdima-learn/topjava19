<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Users</title>
	</head>
	<body>
		<h3><a href="index.html">Home</a></h3>
		<hr>
		<h2>Users</h2>
		<a href="users?action=create">Add Meal</a>
		<br><br>
		<table border="1" cellpadding="8" cellspacing="0">
			<thead>
				<tr>
					<th>Date</th>
					<th>Description</th>
					<th>Calories</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${users}" var="user">
				<jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>

					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.caloriesPerDay}</td>
					<td>${user.enabled}</td>
					<td>${user.email}</td>
					<td>${user.registered.toString()}</td>
					<td>${user.roles}</td>
					<td><a href="users?action=update&id=${user.id}">Update</a></td>
					<td><a href="users?action=delete&id=${user.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		</section>
	</body>
</html>