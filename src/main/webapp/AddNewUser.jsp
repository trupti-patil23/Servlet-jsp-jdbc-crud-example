<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ include file="Header.jsp" %>
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
	<div class="container">
		<h1>Add New User</h1>
		
		<form action="AddNewUser">
			<label for="username">Username:</label> 
			<input type="text"
				id="username" name="username" required> <label for="email">Email:</label>
			<input type="email" id="email" name="email" required> <label
				for="country">Country:</label> <input type="text" id="country"
				name="country" required>

			<div class="actions">
				<button type="submit">Add User</button>
			</div>
		</form>
	</div>

</body>
</html>