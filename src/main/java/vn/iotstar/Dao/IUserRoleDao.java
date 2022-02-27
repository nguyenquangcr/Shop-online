package vn.iotstar.Dao;

import java.util.List;

import vn.iotstar.model.UserRoles;

public interface IUserRoleDao {
	List<UserRoles> findAll();		//hàm lấy toàn bộ User
	
	UserRoles findOne(int id);		//hàm lấy 01 đối tượng role

	void insert(UserRoles role);	//hàm thêm một đối tượng role
	
	void update(UserRoles role);	//hàm cập nhật một đối tượng role
	
	void delete (int id);		//hàm xóa đối tượng role
}
