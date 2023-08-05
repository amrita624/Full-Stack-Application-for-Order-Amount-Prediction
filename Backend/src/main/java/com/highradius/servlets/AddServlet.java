package com.highradius.servlets;

import com.highradius.model.Invoice;
import com.highradius.implementation.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Invoice invoice = null;
		InvoiceDao dao = null;
		
		Integer sl_no;
		Integer cust_order_id;
		Integer sales_org;
		String dist_chan;
		Integer cust_num;
		Integer comp_code;
		String order_curr;
		Float amount_in_usd;
		String order_creation_date;
		
		sl_no = Integer.parseInt(request.getParameter("sl_no"));
		cust_order_id = Integer.parseInt(request.getParameter("cust_order_id"));
		sales_org = Integer.parseInt(request.getParameter("sales_org"));
		dist_chan = request.getParameter("dist_chan");
		cust_num = Integer.parseInt(request.getParameter("cust_num"));
		comp_code = Integer.parseInt(request.getParameter("comp_code"));
		order_curr = request.getParameter("order_curr");
		amount_in_usd = Float.parseFloat(request.getParameter("amount_in_usd"));
		order_creation_date = request.getParameter("order_creation_date");
		
		if(sl_no != null && cust_order_id != null  && sales_org != null  && dist_chan != null && cust_num != null && comp_code != null && order_curr != null && amount_in_usd != null && order_creation_date != null) {
			
			invoice = new Invoice(sl_no, cust_order_id, sales_org, dist_chan, cust_num, comp_code, order_curr, amount_in_usd, order_creation_date);
			dao = new InvoiceDaoImpl();
			boolean inserted = dao.addInvoice(invoice);
			
			if(inserted) {
				response.getWriter().append("Invoice Inserted");
			}
			
			else {
				response.getWriter().append("Invoice was not Inserted");
			}
		}
		
		else {
			
			response.getWriter().append("Insufficient Data!");
		}
		
	}
}