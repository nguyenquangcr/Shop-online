package vn.iotstar.model;

import java.util.Date;

public class Cart {
	private String cartid;
	private Users buyer;
	private Date buyDate;
	private int status;
	
	public Cart() {
		super();
	}

	public Cart(String cartid, Users buyer, Date buyDate, int status) {
		super();
		this.cartid = cartid;
		this.buyer = buyer;
		this.buyDate = buyDate;
		this.status = status;
	}

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public Users getBuyer() {
		return buyer;
	}

	public void setBuyer(Users buyer) {
		this.buyer = buyer;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
