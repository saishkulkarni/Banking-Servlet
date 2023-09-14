package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;

@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		if (email.equals("admin") && password.equals("admin")) {
			resp.getWriter().print("<h1>Login Success</h1>");

			BankDao bankDao = new BankDao();

			req.setAttribute("list", bankDao.fetchAll());
			req.getRequestDispatcher("AdminHome.jsp").include(req, resp);
		} else {
			resp.getWriter().print("<h1>Invalid Credentials</h1>");
			req.getRequestDispatcher("AdminLogin.html").include(req, resp);
		}
	}
}
