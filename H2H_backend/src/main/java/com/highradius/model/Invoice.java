package com.highradius.model;

public class Invoice {
	private int sl_no;
	private int cust_order_id;
	private int sales_org;
	private String dist_chan;
	private int cust_num;
	private int comp_code;
	private String order_curr;
	private float amount_in_usd;
	private String order_creation_date;

	public Invoice() {
		super();
	}

	public Invoice(int sl_no, int cust_order_id, int sales_org, String dist_chan, int cust_num, int comp_code, String order_curr,
			float amount_in_usd, String order_creation_date) {
		super();
		this.sl_no = sl_no;
		this.cust_order_id = cust_order_id;
		this.sales_org = sales_org;
		this.dist_chan = dist_chan;
		this.cust_num = cust_num;
		this.comp_code = comp_code;
		this.order_curr = order_curr;
		this.amount_in_usd = amount_in_usd;
		this.order_creation_date = order_creation_date;
	}
	
	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public int getCust_order_id() {
		return cust_order_id;
	}
	public void setCust_order_id(int cust_order_id) {
		this.cust_order_id = cust_order_id;
	}
	
	public int getSales_org() {
		return sales_org;
	}
	public void setSales_org(int sales_org) {
		this.sales_org = sales_org;
	}
	public String getDist_chan() {
		return dist_chan;
	}
	
	public void setDist_chan(String dist_chan) {
		this.dist_chan = dist_chan;
	}
	public int getCust_num() {
		return cust_num;
	}
	
	public void setCust_num(int cust_num) {
		this.cust_num = cust_num;
	}
	public int getComp_code() {
		return comp_code;
	}
	
	public void setComp_code(int comp_code) {
		this.comp_code = comp_code;
	}
	public String getOrder_curr() {
		return order_curr;
	}
	public void setOrder_curr(String order_curr) {
		this.order_curr = order_curr;
	}
	
	public float getAmount_in_usd() {
		return amount_in_usd;
	}
	public void setAmount_in_usd(float amount_in_usd) {
		this.amount_in_usd = amount_in_usd;
	}
	
	public String getOrder_creation_date() {
		return order_creation_date;
	}
	public void setOrder_creation_date(String order_creation_date) {
		this.order_creation_date = order_creation_date;
	}

	@Override
	public String toString() {
		return "Invoice [sl_no=" + sl_no + ", cust_order_id=" + cust_order_id + ", sales_org=" + sales_org
				+ ", dist_chan=" + dist_chan + ", cust_num=" + cust_num + ", comp_code=" + comp_code + ", order_curr="
				+ order_curr + ", amount_in_usd=" + amount_in_usd + ", order_creation_date=" + order_creation_date
				+ "]";
	}
}