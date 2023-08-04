package com.highradius.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			InvoiceDao dao = new InvoiceDaoImpl();
			Integer sl_no;
			sl_no = Integer.parseInt(request.getParameter("sl_no"));
			
			boolean deleted = dao.deleteInvoice(sl_no);
			
			if(deleted) {
				response.getWriter().append("Invoice Deleted");
			}
			else {
				response.getWriter().append("Invoice was not Deleted");
			}
	}
}