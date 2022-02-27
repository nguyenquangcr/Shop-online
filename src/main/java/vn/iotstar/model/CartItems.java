package vn.iotstar.model;

import java.util.Date;

public class CartItems {

	private String cartitemid;
	private int quantity;
	private double unitPrice;
	private String cartid;
	private int productid;
	private Product product;
	private Cart cart;
	public CartItems() {
		super();
	}
	public CartItems(String cartitemid, int quantity, double unitPrice, String cartid, int productid, Product product,
			Cart cart) {
		super();
		this.cartitemid = cartitemid;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.cartid = cartid;
		this.productid = productid;
		this.product = product;
		this.cart = cart;
	}
	public String getCartitemid() {
		return cartitemid;
	}
	public void setCartitemid(String cartitemid) {
		this.cartitemid = cartitemid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCartid() {
		return cartid;
	}
	public void setCartid(String cartid) {
		this.cartid = cartid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
