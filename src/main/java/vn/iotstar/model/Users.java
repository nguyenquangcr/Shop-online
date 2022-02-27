package vn.iotstar.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Users implements Serializable {
	private int userid;
	private String username;
	private String email;
	private String fullname;
	private String password;
	private String images;
	private String phone;
	private int status;
	private String code;
	private int roleid;
	private UserRoles roles;

//	private Users user;

	private int sellerid;
	private Seller sellers;

	public Users() {
		super();
	}

	public Users(int userid, String username, String email, String fullname, String password, String images,
			String phone, int status, String code, int roleId, UserRoles roles, Users user) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.images = images;
		this.phone = phone;
		this.status = status;
		this.code = code;
		this.roleid = roleid;
		this.roles = roles;
//		this.user = user;
		this.sellerid = sellerid;
		this.sellers = sellers;
	}

	// constructor to insert
	public Users(String username, String email, String fullname, String password, int status, int roleId) {
		super();
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.status = status;
		this.roleid = roleid;
	}
	
	

	public Users(String username, String email, String fullname, String password, int status, int roleid, String code) {
		super();
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.status = status;
		this.code = code;
		this.roleid = roleid;
	}
	
	

	public Users(String username, String email, String fullname, String code) {
		super();
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.code = code;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleId) {
		this.roleid = roleid;
	}

	public UserRoles getRoles() {
		return roles;
	}

	public void setRoles(UserRoles roles) {
		this.roles = roles;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public Seller getSellers() {
		return sellers;
	}

	public void setSellers(Seller sellers) {
		this.sellers = sellers;
	}
}
