package vn.iotstar.Dao;

import java.util.List;

import vn.iotstar.model.Users;

public interface IUserDao {

	//khai báo các hàm để xử lý
	List<Users> findAll();		//hàm lấy toàn bộ User
	
	Users findOne(int id);		//hàm lấy 01 đối tượng User
	
	Users findOne(String username);		//hàm lấy 01 đối tượng User theo username

	void insert(Users user);	//hàm thêm một đối tượng user
	
	void insertregister(Users user); //hàm này dùng cho register
	
	void update(Users user);	//hàm cập nhật một đối tượng user
	
	void updatestatus(Users user);	//hàm này dùng để active tài khoản
	
	void delete (int id);		//hàm xóa đối tượng user
	
	boolean checkExisEmail(String email);
	
	boolean checkExisUsername(String username);
}
