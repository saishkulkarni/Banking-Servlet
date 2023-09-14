package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dto.BankAccount;
import dto.BankTransaction;
import dto.Customer;

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = (Customer) req.getSession().getAttribute("customer");

		if(customer==null)
		{
			resp.getWriter().print("<h1>Session Expired Login Again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else {
		
		double amt = Double.parseDouble(req.getParameter("amt"));

		long acno = (long) req.getSession().getAttribute("acno");
		BankDao bankDao = new BankDao();

		BankAccount account = bankDao.find(acno);

		if (amt > account.getAmount()) {
			resp.getWriter().print("<h1>InSufficient Balance </h1>");
			req.getRequestDispatcher("AccountHome.jsp").include(req, resp);
		} else {
			if (amt > account.getAclimit()) {
				resp.getWriter().print("<h1>Out of Limit enter amount within " + account.getAclimit() + "  </h1>");
				req.getRequestDispatcher("AccountHome.jsp").include(req, resp);
			} else {
				account.setAmount(account.getAmount() - amt);

				BankTransaction bankTransaction=new BankTransaction();
				bankTransaction.setDeposit(0);
				bankTransaction.setWithdraw(amt);
				bankTransaction.setBalance(account.getAmount());
				bankTransaction.setDateTime(LocalDateTime.now());
				
				List<BankTransaction> list=account.getTransactions();
				list.add(bankTransaction);
				
				account.setTransactions(list);

				bankDao.update(account);

				resp.getWriter().print("<h1>Amount withdrawed Successfully</h1>");
				req.getRequestDispatcher("AccountHome.jsp").include(req, resp);
			}
		}}
	}
}
