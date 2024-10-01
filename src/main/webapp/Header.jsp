<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title>User Management System</title>
    <style>
        /* Style the header */
        header {
            background-color: #f0f0f0; /* Light background color */
            padding: 20px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }

        /* Style the navigation bar */
        nav {
            margin: 20px 0;
            text-align: center;
        }

        nav a {
            margin: 0 15px;
            text-decoration: none;
            font-size: 18px;
            color: #007bff; /* Link color */
        }

        nav a:hover {
            color: #0056b3; /* Hover color */
        }
    </style>
</head>
<body>
    <!-- Header section -->
    <header>
        User Management System
    </header>
      
      <h2>Welcome,  <span style="color:blue;">${username}</span>!  </h2> 
  	  <a href="logout">Logout</a>

    <!-- Navigation links -->
    <nav>
        <a href="AddNewUser.jsp">Add New User</a> 
        &nbsp; &nbsp; | &nbsp; &nbsp; 
        <a href="<%=request.getContextPath()%>/userManagement?action=ViewUsers">View Users</a>
    </nav>

</body>
</html>
