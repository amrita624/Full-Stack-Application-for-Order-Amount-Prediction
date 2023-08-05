package com.highradius.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			InvoiceDao dao = new InvoiceDaoImpl();
			
			Integer sl_no = Integer.parseInt(request.getParameter("sl_no"));
			Integer comp_code = Integer.parseInt(request.getParameter("comp_code"));
			String order_curr = request.getParameter("order_curr");
			String dist_chan = request.getParameter("dist_chan");
			
			Invoice invoice = new Invoice();
	        invoice.setSl_no(sl_no);
	        invoice.setComp_code(comp_code);
	        invoice.setOrder_curr(order_curr);
	        invoice.setDist_chan(dist_chan);
			
			boolean edited = dao.editInvoice(sl_no, invoice);
			
			if(edited) {
				response.getWriter().append("Invoice Edited");
			}
			else {
				response.getWriter().append("Invoice was not Edited");
			}
	}
}