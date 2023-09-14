package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setaccount")
public class SetAccount extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long acno = Long.parseLong(req.getParameter("acno"));

		req.getSession().setAttribute("acno", acno);

		req.getRequestDispatcher("AccountHome.jsp").include(req, resp);
	}
}
