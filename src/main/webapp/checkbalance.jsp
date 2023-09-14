<%@page import="dto.Customer"%>
<%@page import="dto.BankAccount"%>
<%@page import="dao.BankDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Balance</title>
</head>
<body>

<%Customer customer1 =(Customer)session.getAttribute("customer"); 
if(customer1==null)
{
	response.getWriter().print("<h1>Session Expired Login Again</h1>");
	request.getRequestDispatcher("Login.html").include(request, response);
}
else{
%>
<%
long acno = (long) session.getAttribute("acno");
BankDao bankDao = new BankDao();
BankAccount account = bankDao.find(acno);
Customer customer=account.getCustomer();
%>

<h1>Hello <%if(customer.getGender().equals("male")) {%>Mr. <%}else{%>Ms. <%} %> <%=customer.getName() %></h1>
<h1>Your <%=account.getType() %> account balance is <%=account.getAmount() %></h1>
<br><br>
<a href="AccountHome.jsp"><button>Back</button></a>
<%} %>
</body>
</html>