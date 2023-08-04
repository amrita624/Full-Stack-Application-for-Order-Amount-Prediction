package com.highradius.implementation;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

public class InvoiceDaoImpl implements InvoiceDao {

	Connection connection = null; 
	PreparedStatement ps = null;
	
	public boolean addInvoice(Invoice invoice) {
		String sql = "INSERT INTO h2h_oap (sl_no, cust_order_id, sales_org, dist_chan, cust_num, comp_code, order_curr, amount_in_usd, order_creation_date) VALUES (?,?,?,?,?,?,?,?,?)";
		
		try {
			
			connection = DatabaseConnection.getConnection();
			if(connection == null) {
				return false;
			}
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, invoice.getSl_no());
			ps.setInt(2, invoice.getCust_order_id());
			ps.setInt(3, invoice.getSales_org());
			ps.setString(4, invoice.getDist_chan());
			ps.setInt(5, invoice.getCust_num());
			ps.setInt(6, invoice.getComp_code());
			ps.setString(7, invoice.getOrder_curr());
			ps.setFloat(8, invoice.getAmount_in_usd());
			ps.setString(9, invoice.getOrder_creation_date());
			
			int insert = ps.executeUpdate();
			return insert > 0 ? true : false;
			
		} catch(Exception e){
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}
	
	public List<Invoice> getInvoiceList() {
		List<Invoice> invoice = new ArrayList<Invoice>();
		String sql = "SELECT * from h2h_oap WHERE sl_no<20";
		try {
			connection = DatabaseConnection.getConnection();
			if(connection == null)
				return null;
			
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				invoice.add(new Invoice(rs.getInt("sl_no"), rs.getInt("cust_order_id"), rs.getInt("sales_org"), 
						rs.getString("dist_chan"), rs.getInt("cust_num"), rs.getInt("comp_code"), rs.getString("order_curr"),
						rs.getFloat("amount_in_usd"), rs.getString("order_creation_date")));
			}
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return invoice;
		}
	}
	
	
	public boolean deleteInvoice(Integer sl_no) {
		String sql = "DELETE FROM h2h_oap WHERE sl_no=?";
		
		try {
			connection = DatabaseConnection.getConnection();
			if(connection == null) {
				return false;
			}
			ps = connection.prepareStatement(sql);
			ps.setInt(1, sl_no);
			int delete = ps.executeUpdate();
			return delete > 0 ? true : false;
			
		} catch(Exception e){
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
	}

	public boolean editInvoice(Integer sl_no, Invoice invoice) {
		String sql = "UPDATE h2h_oap SET order_curr = ?, comp_code = ?, dist_chan = ? WHERE sl_no = ?";
		
		try {
			
			connection = DatabaseConnection.getConnection();
			if(connection == null) {
				return false;
			}
			
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, invoice.getOrder_curr());
			ps.setInt(4, invoice.getSl_no());
			ps.setString(3, invoice.getDist_chan());
			ps.setInt(2, invoice.getComp_code());
			
			int insert = ps.executeUpdate();
			return insert > 0 ? true : false;
			
		} catch(Exception e){
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
	}
}