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
<% Cookie cookies[] = request.getCookies() ;
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
<table>
<tr>
<th>account Id</th>
<th>account balance</th>
<th>account status</th>
</tr>
<a:forEach var="pro" items="${accounts}">
<tr>
<td>${pro.accountNo}</td>
<td>${pro.balance}</td>
<td>${pro.accountStatus}</td>
</tr>
</a:forEach>
</table>
</body>
</html>