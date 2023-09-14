package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dto.BankAccount;

@WebServlet("/changestatus")
public class ChangeAccountStatus extends HttpServlet
{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	long acno=Long.parseLong(req.getParameter("acno"));
	
	BankDao bankDao=new BankDao();
	
	BankAccount account=bankDao.find(acno);
	
	if(account.isStatus())
	{
		account.setStatus(false);
	}
	else {
		account.setStatus(true);
	}
	
	bankDao.update(account);
	
	resp.getWriter().print("<h1>Update Success</h1>");	
	req.setAttribute("list", bankDao.fetchAll());
	req.getRequestDispatcher("AdminHome.jsp").include(req, resp);
	
}
}
