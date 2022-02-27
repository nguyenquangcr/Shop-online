package vn.iotstar.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserRoles implements Serializable {
	private int roleid;
	private String rolename;
	
	public UserRoles() {
		super();
	}

	public UserRoles(int roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
	
}
