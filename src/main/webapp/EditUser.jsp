<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="Header.jsp"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New User</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

.container {
	max-width: 600px;
	margin: 0 auto;
}

h1 {
	text-align: center;
}

form {
	display: flex;
	flex-direction: column;
	margin-top: 20px;
}

label, input {
	margin: 10px 0;
}

input {
	padding: 8px;
	font-size: 14px;
}

.actions {
	margin-top: 20px;
}

.actions button {
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
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
	}
	%>

	<div class="container">
		<h1>Edit User</h1>
		<form action="<%=request.getContextPath()%>/userManagement" method="post">
			<input type="hidden" name="action" value="SaveUser"> 
			<input type="hidden" name="id" value="${user.id}"> 
			<label
				for="username">User Name:</label> 
				<input type="text" id="username"  name="username" value="${user.name}" required/> 
				
				
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" value="${user.email}" required/> 
				
				<label for="country">Country:</label> 
				<input type="text" id="country" name="country"  value="${user.country}" required/>

			<div class="actions">
				<button type="submit">Save User</button>
			</div>
		</form>
	</div>
</body>
</html>