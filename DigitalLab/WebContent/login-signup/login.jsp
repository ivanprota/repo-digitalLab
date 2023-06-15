<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<head>

		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<title>Accedi</title>
		
		<link type="text/css" rel="stylesheet" href="../css/login-signup.css">

	</head>

	<body>

		<section>

		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>  
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>
		    <span></span>

		    <div class="signin">
		    	<div class="content">
		        	<h2>Accedi</h2>
		        	<div class="form">
		          		<form action="<%= request.getContextPath()%>/LoginServlet" method="POST">
				          <div class="inputBox">
				            <input type="text" name="username" required>
				            <i>Username</i>
				          </div>
				          <div class="inputBox">
				            <input type="password" name="password" required>
				            <i>Password</i>
				          </div>
				          <div class="links">
				            <a href="#">Password dimenticata</a>
				            <a href="<%= request.getContextPath()%>/login-signup/signup.jsp">Registrati</a>
				          </div>
				          <div class="inputBox">
				            <input type="submit" value="Accedi">
				          </div>
		          		</form>
		        	</div>
		      	</div>
		    </div>
		   </section>

	</body>

</html>