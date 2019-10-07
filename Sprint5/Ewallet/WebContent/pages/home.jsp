<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Wallet</title>
  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="<c:url value="/pages/styles/bootstrap.min.css"/>" >

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link rel="stylesheet" href="<c:url value="/pages/styles/one-page-wonder.min.css"/>">

<style>
/*!
 * Start Bootstrap - One Page Wonder v5.0.7 (https://startbootstrap.com/template-overviews/one-page-wonder)
 * Copyright 2013-2019 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-one-page-wonder/blob/master/LICENSE)
 */body{font-family:Lato}h1,h2,h3,h4,h5,h6{font-family:Catamaran;font-weight:800!important}.btn-xl{text-transform:uppercase;padding:1.5rem 3rem;font-size:.9rem;font-weight:700;letter-spacing:.1rem}.bg-black{background-color:#000!important}.rounded-pill{border-radius:5rem}.navbar-custom{padding-top:1rem;padding-bottom:1rem;background-color:rgba(0,0,0,.7)}.navbar-custom .navbar-brand{text-transform:uppercase;font-size:1rem;letter-spacing:.1rem;font-weight:700}.navbar-custom .navbar-nav .nav-item .nav-link{text-transform:uppercase;font-size:.8rem;font-weight:700;letter-spacing:.1rem}header.masthead{position:relative;overflow:hidden;padding-top:calc(7rem + 72px);padding-bottom:7rem;background:linear-gradient(0deg,#ff6a00 0,#ee0979 100%);background-repeat:no-repeat;background-position:center center;background-attachment:scroll;background-size:cover}header.masthead .masthead-content{z-index:1;position:relative}header.masthead .masthead-content .masthead-heading{font-size:4rem}header.masthead .masthead-content .masthead-subheading{font-size:2rem}header.masthead .bg-circle{z-index:0;position:absolute;border-radius:100%;background:linear-gradient(0deg,#ee0979 0,#ff6a00 100%)}header.masthead .bg-circle-1{height:90rem;width:90rem;bottom:-55rem;left:-55rem}header.masthead .bg-circle-2{height:50rem;width:50rem;top:-25rem;right:-25rem}header.masthead .bg-circle-3{height:20rem;width:20rem;bottom:-10rem;right:5%}header.masthead .bg-circle-4{height:30rem;width:30rem;top:-5rem;right:35%}@media (min-width:992px){header.masthead{padding-top:calc(10rem + 55px);padding-bottom:10rem}header.masthead .masthead-content .masthead-heading{font-size:6rem}header.masthead .masthead-content .masthead-subheading{font-size:4rem}}.bg-primary{background-color:#ee0979!important}.btn-primary{background-color:#ee0979;border-color:#ee0979}.btn-primary:active,.btn-primary:focus,.btn-primary:hover{background-color:#bd0760!important;border-color:#bd0760!important}.btn-primary:focus{box-shadow:0 0 0 .2rem rgba(238,9,121,.5)}.btn-secondary{background-color:#ff6a00;border-color:#ff6a00}.btn-secondary:active,.btn-secondary:focus,.btn-secondary:hover{background-color:#c50!important;border-color:#c50!important}.btn-secondary:focus{box-shadow:0 0 0 .2rem rgba(255,106,0,.5)}
 


</style>
</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Online Wallet</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="registration">Sign Up</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="login">Log In</a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="#features">Features</a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="#contact">About Us</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <header class="masthead text-center text-white">
   <!--   <img src="<c:url value="/resources/images/ewallet.png"/>">-->
    <div class="masthead-content">
      <div class="container">
        <img src="<c:url value="/resources/images/ewallet.png"/>">
         <h1 class="masthead-heading mb-0">Every penny saved</h1>
        <h2 class="masthead-subheading mb-0">is a penny earned</h2>
        <a href="#features" class="btn btn-primary btn-xl rounded-pill mt-5">Learn More</a>
      </div>
    </div>
    <div class="bg-circle-1 bg-circle"></div>
    <div class="bg-circle-2 bg-circle"></div>
    <div class="bg-circle-3 bg-circle"></div>
    <div class="bg-circle-4 bg-circle"></div>
  </header>

  <section id = "features">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 order-lg-2">
          <div class="p-5">
            <img class="img-fluid rounded-circle" src="<c:url value="/resources/images/happy.jpg"/>" alt="happy">
          </div>
        </div>
        <div class="col-lg-6 order-lg-1">
          <div class="p-5">
            <h2 class="display-4">Easy to use.</h2>
            <p>E-Wallet makes it easy for consumers to pay quickly with just a userId and password. Adding this facility to your checkout is simple and effective.</p>
          </div>
        </div>
      </div>
    </div>
  </section>

  <section>
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6">
          <div class="p-5">
            <img class="img-fluid rounded-circle" src="<c:url value="/resources/images/flash.jpeg"/>" alt="happy">
          </div>
        </div>
        <div class="col-lg-6">
          <div class="p-5">
            <h2 class="display-4">Fast and Furious!</h2>
            <p> A Single-click payment platform thats even mobile-friendly and transfer of money happens within few seconds.</p>
          </div>
        </div>
      </div>
    </div>
  </section>

  <section>
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 order-lg-2">
          <div class="p-5">
            <img class="img-fluid rounded-circle" src= "<c:url value="/resources/images/safe.jpg"/>" alt="happy">
          </div>
        </div>
        <div class="col-lg-6 order-lg-1">
          <div class="p-5">
            <h2 class="display-4">Safe and Secure</h2>
            <p>The service of tranfering money here is trustable and 100% secure.</p>
          </div>
        </div>
      </div>
    </div>
  </section>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Carousel indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- Wrapper for carousel items -->
    <!-- <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="<c:url value="/resources/images/payonline.jpg"/>" alt="happy" style="width: 100%; height: 120%;">
        </div>
        <div class="carousel-item">
            <img src="<c:url value="/resources/images/money.png"/>" alt="Second Slide" style="width: 70%;  height: 20%; padding-left: 200px" >
        </div>
        <div class="carousel-item">
            <img src="<c:url value="/resources/images/card.jpg"/>" style="width: 100%; height: 60%;">
        </div>
    </div>
    
    <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#myCarousel" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>
</div>
 -->

  <!-- Footer -->
  <footer class="py-5 bg-black" id ="contact">
    <div class="container">
    <p class="m-0 text-center text-white medium"><a href="https://www.linkedin.com/in/utkarsh-singh-08aba0134/">Utkarsh Singh</a> </p>
    <p class="m-0 text-center text-white medium"><a href="https://www.linkedin.com/in/mullagiri-venkatesh-70991310b/">Venkatesh M.</a></p>
    

      <p class="m-0 text-center text-white small">Copyright &copy; E-Wallet 2019</p>
    </div>
    <!-- /.container -->
  </footer>

 <!-- Bootstrap core JavaScript -->
  <script src="<c:url value="/pages/styles/jquery.min.js"/>"></script>
  <script src="<c:url value="/pages/styles/bootstrap.bundle.min.js"/>"></script>

</body>

</html>
