package vn.iotstar.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Seller implements Serializable {
	private int sellerid;
	private String sellername;
	private String images;
	private int status;
	
	public Seller() {
		super();
	}

	public Seller(int sellerid, String sellername, String images, int status) {
		super();
		this.sellerid = sellerid;
		this.sellername = sellername;
		this.images = images;
		this.status = status;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
