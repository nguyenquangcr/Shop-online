package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.Dao.DBConnection;
import vn.iotstar.Dao.IUserDao;
import vn.iotstar.model.UserRoles;
import vn.iotstar.model.Users;
import vn.iotstar.service.IUserRoleService;
import vn.iotstar.service.impl.UserRoleServiceImpl;

public class UserDaoImpl implements IUserDao {
	public Connection conn = null;
	public PreparedStatement ps = null; // nem cau query vao thuc ti du lieu
	public ResultSet rs = null;
	IUserRoleService userRoleService = new UserRoleServiceImpl(); // lay du lieu tu userRole

	@Override
	public List<Users> findAll() {
		List<Users> userListArr = new ArrayList<Users>();
		String sql = "select Users.userId,Users.email,Users.fullname,Users.images,Users.username,Users.password,Users.phone,Users.roleId,\r\n"
				+ "Users.status, UserRoles.roleName, UserRoles.roleId from Users\r\n"
				+ "INNER JOIN UserRoles ON Users.roleId = UserRoles.roleId";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserRoles userRoles = userRoleService.findOne(rs.getInt("roleId"));
				Users user = new Users();
				user.setUserid(rs.getInt("userid"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
//				user.setCode(rs.getInt("code"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));
				user.setRoles(userRoles);
				user.setStatus(rs.getInt("status"));

				userListArr.add(user);
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userListArr;
	}

	@Override
	public Users findOne(int id) {
		String sql = "select Users.userId,Users.email,Users.fullname,Users.images,Users.username,Users.password,Users.phone,Users.roleId,\r\n"
				+ "Users.status, UserRoles.roleName, UserRoles.roleId from Users\r\n"
				+ "INNER JOIN UserRoles ON Users.roleId = UserRoles.roleId\r\n" + "Where Users.userId = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserRoles userRoles = userRoleService.findOne(rs.getInt("roleId"));
				Users user = new Users();
				user.setUserid(rs.getInt("userId"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
//				user.setCode(rs.getInt("code"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleId"));
				user.setRoles(userRoles);
				user.setStatus(rs.getInt("status"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Users findOne(String username) {
		String sql = "select Users.userId,Users.email,Users.fullname,Users.images,Users.username,Users.password,Users.phone,Users.roleId,\r\n"
				+ "Users.status, UserRoles.roleName from Users\r\n"
				+ "INNER JOIN UserRoles ON Users.roleId = UserRoles.roleId\r\n" + "Where Users.username = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserRoles userRoles = userRoleService.findOne(rs.getInt("roleId"));
				Users user = new Users();
				user.setUserid(rs.getInt("userId"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
//				user.setCode(rs.getInt("code"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleId"));
				user.setRoles(userRoles);
				user.setStatus(rs.getInt("status"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Users user) {
		String sql = "Insert INTO Users (email,username,fullname,password,images,phone,status,roleId) Values (?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getImages());
			ps.setString(6, user.getPhone());
			ps.setInt(7, user.getStatus());
			ps.setInt(8, user.getRoles().getRoleid());
//			ps.setInt(9, user.getSellers().getSellerid());
//			ps.setInt(9, user.getCode());		

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Users user) {
		String sql = "Update Users SET email=?,username=?,fullname=?,password=?,images=?,phone=?,status=?\r\n"
				+ "where userId = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getImages());
			ps.setString(6, user.getPhone());
			ps.setInt(7, user.getStatus());
//			ps.setInt(8, user.getRoles().getRoleid());
//			ps.setInt(8, user.getSellers().getSellerid());
//			ps.setString(8, user.getCode());
			ps.setInt(8, user.getUserid());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from Users where userId=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkExisEmail(String email) {
		boolean duplicate = false;
		String sql = "Select * From Users where email =?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExisUsername(String username) {
		boolean duplicate = false;
		String sql = "Select * From Users where username =?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public void updatestatus(Users user) {
		String sql = "UPDATE [Users] SET status=?, code=? WHERE email = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getStatus());
			ps.setString(2, user.getCode());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertregister(Users user) {
		String sql = "Insert INTO Users (email,username,fullname,password,status,roleId,code) Values (?,?,?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getStatus());
			ps.setInt(6, user.getRoleid());
			ps.setString(7, user.getCode());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
