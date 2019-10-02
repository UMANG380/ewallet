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
<body data-fade-in="true">
<div style="position: relative;animation-name: example;
  animation-duration: 4s;
  animation-delay: 2s;">
<nav class="navbar navbar-expand-lg navbar-light"  style="background-color:#1a2849;">
  <a class="navbar-brand" href="#" style="font-size:30px;font-family:sans-serif;color:white">Online Wallet</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation"
     >
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse " id="navbarNav">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="login" style="font-size:15px;font-family:sans-serif;color:white">Login</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="registration" style="font-size:15px;font-family:sans-serif;color:white">SignUp</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#features" style="font-size:15px;font-family:sans-serif;color:white">Features</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#" style="font-size:15px;font-family:sans-serif;color:white">Contact</a>
      </li>
    </ul>
  </div>
</nav>
</div>
<section class="bg-danger hero-fullscreen parallax" data-overlay-dark="7">
<div class="bd-example" style="background-color:cyan;padding-top:40px;padding-bottom:60px">
  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" style="padding-top: 10px">
      <div class="carousel-item active" style="padding-left: 100px;padding-right: 100px">
        <img src="<c:url value="/resources/images/onlinewalletimg.jpg"/>"  width="50px" height="300px"  class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>First slide label</h5>
          <p style="color:red">Nulla vitae elit libero, a pharetra augue mollis interdum.</p> 	
        </div>
      </div>
      <div class="carousel-item" style="padding-left: 100px;padding-right: 100px">
        <img src="<c:url value="/resources/images/secondimg.png"/>" width="50px" height="300px" class="d-block w-100 bg-danger" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>Second slide label</h5>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        </div>
      </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>
</div>
</section>
<section id="features">
<div class="row" style="background-color:#52de97">
  <div class="col-sm-6" style="font-size:35px;font-family:sans-serif;color:white">
    <div class="card">
      <div class="card-body" style="background-color:#1a2849">
        <h5 class="card-title">Special title treatment</h5>
        <p class="card-text" style="font-family: cursive;font-size:20px">With supporting text below as a natural lead-in to additional content.</p>
        <a href="registration" class="btn btn-primary">SignUp</a>
      </div>
    </div>
  </div>
  <div class="col-sm-6" >
    <div class="card" style="font-size:30px;font-family:sans-serif;color:white">
      <div class="card-body" style="background-color:#1a2849">
        <h5 class="card-title">Special title treatment</h5>
        <p class="card-text" style="font-family: cursive;font-size:20px">With supporting text below as a natural lead-in to additional content.</p>
        <a href="registration" class="btn btn-primary">SignUp</a>
      </div>
    </div>
  </div>
</div>
</section>
<!-- Footer -->
<footer class="page-footer font-small cyan darken-3">
  <!-- Copyright -->
  <div class="footer-copyright text-center py-3" style="background-color:#52de97">© 2018 Copyright:
    <a href="https://mdbootstrap.com/education/bootstrap/"> MDBootstrap.com</a>
  </div>
  <!-- Copyright -->

</footer>
<!-- Footer -->
</body>
</html>