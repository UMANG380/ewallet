<%@page import="com.cg.onlinewallet.dto.WalletUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="registrationForm"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/webjars/css/bootstrap.min.css"/>" > 
<script type="text/javascript" src="<c:url value="/webjars/js/bootstrap.min.js"/>"> </script>
<style type="text/css">
body{
background-size:cover
}
</style>
<style type="text/css">
	
    .form-control{
		height: 40px;
		box-shadow: none;
		color: #969fa4;
	}
	.form-control:focus{
		border-color: #5cb85c;
	}
    .form-control, .btn{        
        border-radius: 3px;
    }
	.signup-form{
		width: 400px;
		margin: 0 auto;
		padding: 30px 0;
	}
	.signup-form h2{
		color: #636363;
        margin: 0 0 15px;
		position: relative;
		text-align: center;
    }
	.signup-form h2:before, .signup-form h2:after{
		content: "";
		height: 2px;
		width: 30%;
		background: #d4d4d4;
		position: absolute;
		top: 50%;
		z-index: 2;
	}	
	.signup-form h2:before{
		left: 0;
	}
	.signup-form h2:after{
		right: 0;
	}
    .signup-form .hint-text{
		color: #999;
		margin-bottom: 30px;
		text-align: center;
	}
    .signup-form form{
		color: #999;
		border-radius: 3px;
    	margin-bottom: 15px;
        background: #f5f1d7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
	.signup-form .form-group{
		margin-bottom: 20px;
	}
	.signup-form input[type="checkbox"]{
		margin-top: 3px;
	}
	.signup-form .btn{        
        font-size: 16px;
        font-weight: bold;		
		min-width: 140px;
        outline: none !important;
    }
	.signup-form .row div:first-child{
		padding-right: 10px;
	}
	.signup-form .row div:last-child{
		padding-left: 10px;
	}    	
    .signup-form a{
		color: #fff;
		text-decoration: underline;
	}
    .signup-form a:hover{
		text-decoration: none;
	}
	.signup-form form a{
		color: #5cb85c;
		text-decoration: none;
	}	
	.signup-form form a:hover{
		text-decoration: underline;
	}  
</style>

</head>
<body background="<c:url value= "/resources/images/Simple.jpg"/>" class="bg-light" >

<% 
WalletUser user=(WalletUser)session.getAttribute("user");
WalletUser user1=(WalletUser)request.getAttribute("user"); 
if(user1==null){
	user1=user;
}
session.setAttribute("user",user);
System.out.println(user.getPhoneNo());
Cookie cookies[] = request.getCookies() ;
boolean flag=false;
   for(Cookie c : cookies){
	   if(c.getName().equals("status")){
		  if(c.getValue().equals("loggedin")){
			  flag=true;
			  break;
		  }
	   }
   }
   if(!flag){
	   response.sendRedirect("login");
   }
  
%>
<div>
 <h1 style="font-size: 30px;font-family: fantasy;
 padding-left: 350px;color:white">Welcome <span style="font-family: sans-serif;">${user.userName}</span></h1>
</div>
<form class="bg-dark" style="margin-top:25px;" >
  <div class="form-row bg-info">
    <div class="col-md-4">
      <input type="text" class="form-control" placeholder="First name" disabled="disabled" 
      value="    account Number :   ${user.account.accountNo}">>
    </div>
    <div class="col-md-4">
      <input type="text" class="form-control" placeholder="Last name" disabled="disabled"
      value="    account balance :  ${user.account.balance}">
    </div>
    <div class="col-md-4">
      <input type="text" class="form-control" placeholder="Last name" disabled="disabled"
      value="    phone Number :   ${user.phoneNo}">
    </div>
  </div>
</form>
<center>
<div style="margin-top: 120px;margin-left: 520px">

<h5 style="margin-top: 120px;margin-right: 400px">Add Amount to your Wallet</h5>
<a href="addAmount"> <div class="input-group mb-3 align-items-center" align="center">
  <div class="input-group-append" align="center">
  
  <button type="button" class="btn btn-success btn-med" >Add Amount</button></a>
  </div>
  </div>
</div>
<center>
</div>
</div>
<div style="margin-top: 300px">
<div style="margin-left: 150px">
<div class="signup-form">
<h5>Transfer Money</h5>
		<p >Tranfer Money to a same wallet or different wallet account </p>
    <form action="transferAmount" method="post">
		
           <div class="checkbox form-group d-flex align-items-left ">

			<input type="radio" name="accountType" value="same" checked> Same wallet <br>

			</div>

			<div class="checkbox form-group d-flex align-items-right">

            <input type="radio" name="accountType" value="diff"> Different Wallet<br>

			</div>
        <div class="form-group">
        	<input type="text" class="form-control" name="phoneNo" placeholder="Phone Number" required="required"/>
        </div>
		<div class="form-group">
            <input type="number" class="form-control" name="amount" placeholder="Amount" required="required"/>
<div class="form-group">
</div>
<div style="margin-top: 60px">
            <button type="submit" value="Transfer" class="btn btn-success btn-md btn-block"/>Transfer</button>
     </div>
        </div>
        </form>
</div>
</div>

    </div>

</center>
 <div>
        <span style="color:blue;font-size: 30px"> ${error} </span>
        </div>
<div class="signup-form" style="margin-top:50px">
    <form action="getTransactionsPage" method="post">
     <div class="checkbox form-group d-flex align-items-left ">

			<input type="radio" name="getExcel" value="true" > get Excel sheet <br>
			</div>
			     <div class="checkbox form-group d-flex align-items-left ">

			<input type="radio" name="getExcel" value="false" > print transactions<br>
			</div>
        <div class="form-group">
        	<input type="date" data-date-format="YYYY/MM/DD" class="form-control" name="fromDate" placeholder="yyyy/MM/dd" required="required"/>
        </div>
		<div class="form-group">
            <input type="date" data-date-format="yyyy/MM/dd" class="form-control" name="toDate" placeholder="yyyy/MM/dd" required="required"/>
<div class="form-group">
</div>
<div style="margin-top: 60px">
            <button type="submit" value="Transfer" class="btn btn-success btn-md btn-block"/>Get Transactions</button>
     </div>
        </div>
        </form>



</div>

<div style="margin-top: 10px">
<a href="signOut"><button type="button" class="btn btn-outline-dark btn-med" >SignOut</button></a>
</div> 
</center>
</body>
</html>