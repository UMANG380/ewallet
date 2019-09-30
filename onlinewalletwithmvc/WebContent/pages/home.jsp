<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Wallet</title>
<link rel="stylesheet" href="<c:url value="/webjars/css/bootstrap.min.css"/>" > 
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

	  <a class="navbar-brand" href="#">Home</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>

	  <div class="collapse navbar-collapse " id="navbarSupportedContent">
	    <ul class="navbar-nav mr-4">
	      
	      <li class="nav-item">
	        <a class="nav-link" data-value="about" href="#">About</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link " data-value="login" href="#">Login</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link " data-value="signup" href="#">SignUp</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link " data-value="contact" href="#">Contact</a>
	      </li>
	    </ul>
	    
	  </div>
</nav>
</body>
</html>