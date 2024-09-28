<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Header.jsp"%>
<html>
<head>
<title>View Users</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	border: 1px solid #ccc;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
/* Alternate row colors */
tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:nth-child(odd) {
	background-color: #fff;
}

a {
	text-decoration: none;
	color: #007bff;
}

a:hover {
	color: #0056b3;
}
</style>
</head>
<body>	

	<!-- Display the message if it exists -->
	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<div
		style="color: <%=message.contains("successfully") ? "green" : "red"%>;">
		<%=message%>
	</div>
	<%
	} else {
	%>
	<h1>List of Users</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Country</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${usersList}">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.country}</td>
					<td><a
						href="<%=request.getContextPath()%>/userManagement?action=EditUser&id=${user.id}">Edit</a>
						| <a
						href="<%=request.getContextPath()%>/userManagement?action=DeleteUser&id=${user.id}"
						onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%
	}
	%>

</body>
</html>