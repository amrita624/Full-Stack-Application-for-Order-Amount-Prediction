package com.highradius.implementation;

import java.util.List;

import com.highradius.model.Invoice;

public interface InvoiceDao {

	boolean addInvoice(Invoice invoice);
	List<Invoice> getInvoiceList();
	boolean editInvoice(Integer sl_no,Invoice invoice);
	boolean deleteInvoice(Integer sl_no);
}