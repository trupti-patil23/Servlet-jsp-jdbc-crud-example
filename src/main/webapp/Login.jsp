<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
 <style>
        /* Center the square form container */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f2f2f2;
        }

        /* Create a square box for the login form */
        .login-container {
            background-color: white;
            padding: 40px;
            width: 300px;
            height: 300px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
        }

        /* Style the input fields */
        input[type="text"], input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        /* Style the login button */
        input[type="submit"] {
            background-color: #acb0ac;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }
    </style>
</head>
<body>
	  <div class="login-container">
        <h2>Login (admin/admin only can login)</h2>
        
	        <!-- Display error message if login fails -->
	    <%
	        String error = request.getParameter("error");
	        if (error != null && error.equals("Invalid Credentials")) {
	    %>
	        <p style="color: red;">Invalid UserName or Password. Please try again.</p>
	    <%
	        }
	    %>

        <!-- Login Form -->
        <form action="<%=request.getContextPath()%>/login">
        	<input type="hidden" name="action" value="Login"> 
        	<label for="username">UserName:</label><br>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" required><br>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>