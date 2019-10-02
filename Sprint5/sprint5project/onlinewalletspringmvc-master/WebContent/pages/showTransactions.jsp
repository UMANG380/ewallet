<%@page import="com.cg.onlinewallet.dto.WalletUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
WalletUser user=(WalletUser)request.getAttribute("user");
session.setAttribute("user",user);
System.out.println(user.getPhoneNo());
Cookie cookies[] = request.getCookies() ;
boolean flag=false;
   for(Cookie c : cookies){
	   if(c.getName().equals("status")){
		   System.out.println(c.getValue());
		  if(c.getValue().equals("loggedin")){
			  flag=true;
			  break;
		  }
	   }
   }
   if(!flag){
	   System.out.println("hii");
	   response.sendRedirect("login");
   }


%>
<table cellspacing="10">
<tr>
<th>transaction Id</th>
<th>transaction Date</th>
<th>transaction amount</th>
<th>transaction Description</th>
<th>transaction balance</th>
</tr>
<a:forEach var="pro" items="${transactions}">
<tr>
<td>${pro.transactionId}</td>
<td>${pro.dateOfTransaction}</td>
<td>${pro.amount}</td>
<td>${pro.description}</td>
<td>${pro.balance}</td>
</tr>
</a:forEach>
</table>
<a href="getTransactionsExcel">Get Excel Sheet</a>
</body>
</html>