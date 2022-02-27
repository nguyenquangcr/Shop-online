package vn.iotstar.model;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Product implements Serializable {
	private int productid;
	private String productname;
	private Long productcode;			//bigint
	private int categoryid;
	private String description;
	private Double price;		//float
	private int amount;
	private int stock;
	private String images;
	private int wishlist;
	private int status;
	private Date createDate;
	private int sellerid;
	
	private Category category;
	private Seller seller;
	
	public Product(int productid, String productname, Long productcode, int categoryid, String description,
			Double price, int amount, int stock, String images, int wishlist, int status, Date createDate, int sellerid,
			Category category, Seller seller) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.productcode = productcode;
		this.categoryid = categoryid;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.stock = stock;
		this.images = images;
		this.wishlist = wishlist;
		this.status = status;
		this.createDate = createDate;
		this.sellerid = sellerid;
		this.category = category;
		this.seller = seller;
	}
	public Product() {
		super();
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Long getProductcode() {
		return productcode;
	}
	public void setProductcode(Long productcode) {
		this.productcode = productcode;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public int getWishlist() {
		return wishlist;
	}
	public void setWishlist(int wishlist) {
		this.wishlist = wishlist;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	

}
