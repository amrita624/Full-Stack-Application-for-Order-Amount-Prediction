package com.highradius.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

@WebServlet("/DataLoadingServlet")
public class DataLoadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InvoiceDao dao = new InvoiceDaoImpl();
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		invoiceList = dao.getInvoiceList();
//		String errorString = "Sorry! No customers found";
//		if(customerList.size() > 0) {
//			response.getWriter().append("" + customerList);
//		} else {
//			response.getWriter().append(errorString);
//		}
		String errorString = "Sorry! No customers found";
		Gson gson = new Gson(); 
		String jsonResponse = gson.toJson(errorString);
		if(invoiceList.size() > 0) {
			jsonResponse = gson.toJson(invoiceList);
		}
		response.setHeader("Content-Type", "application/json; charset=utf-8");
		response.getWriter().append(jsonResponse);
	}
}
