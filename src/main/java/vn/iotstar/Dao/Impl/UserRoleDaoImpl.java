package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.Dao.DBConnection;
import vn.iotstar.Dao.IUserRoleDao;
import vn.iotstar.model.UserRoles;

public class UserRoleDaoImpl implements IUserRoleDao {
	public Connection conn = null;
	public PreparedStatement ps = null; // nem cau query vao thuc ti du lieu
	public ResultSet rs = null;

	@Override
	public List<UserRoles> findAll() {
		List<UserRoles> roleList = new ArrayList<UserRoles>();
		String sql = "select * From UserRoles";

		try {
			conn = new DBConnection().getConnection(); // dòng kết nối cơ sở dữ liệu
			ps = conn.prepareStatement(sql); // ném câu lệnh sql bằng phát biểu preparestatement
			rs = ps.executeQuery(); // thực thi query lấy về kết quả
			while (rs.next()) {
				UserRoles userRole = new UserRoles();
				userRole.setRoleid(rs.getInt("roleId"));
				userRole.setRolename(rs.getString("roleName"));
				roleList.add(userRole);
			}
			;

		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.pri()
		return roleList;
	}

	@Override
	public UserRoles findOne(int id) {
		String sql = "select * From UserRoles where roleId=?";

		try {
			conn = new DBConnection().getConnection(); // dòng kết nối cơ sở dữ liệu
			ps = conn.prepareStatement(sql); // ném câu lệnh sql bằng phát biểu preparestatement
			ps.setInt(1, id); // đưa tham số vào dấu ?
			rs = ps.executeQuery(); // thực thi query lấy về kết quả
			while (rs.next()) {
				UserRoles userRole = new UserRoles();
				userRole.setRoleid(rs.getInt("roleId"));
				userRole.setRolename(rs.getString("roleName"));

				return userRole;
			}
			;

		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.pri()
		return null;
	}

	@Override
	public void insert(UserRoles role) {
		String sql = "Insert INTO UserRoles(roleName) VALUES (?)";
		try {
			conn = new DBConnection().getConnection(); // dòng kết nối cơ sở dữ liệu
			ps = conn.prepareStatement(sql); // ném câu lệnh sql bằng phát biểu preparestatement
			ps.setString(1, role.getRolename()); // đưa tham số vào dấu ?
			ps.executeUpdate(); // thực hiện update câu query đó
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(UserRoles role) {
		String sql = "UPDATE UserRoles SET roleName=? Where roleId=?";
		try {
			conn = new DBConnection().getConnection(); // dòng kết nối cơ sở dữ liệu
			ps = conn.prepareStatement(sql); // ném câu lệnh sql bằng phát biểu preparestatement
			ps.setString(1, role.getRolename()); // đưa tham số vào dấu ?
			ps.setInt(2, role.getRoleid()); // đưa tham số vào dấu ?
			ps.executeUpdate(); // thực hiện update câu query đó
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM UserRoles Where roleId=?";
		try {
			conn = new DBConnection().getConnection(); // dòng kết nối cơ sở dữ liệu
			ps = conn.prepareStatement(sql); // ném câu lệnh sql bằng phát biểu preparestatement
			ps.setInt(1, id); // đưa tham số vào dấu ?
			ps.executeUpdate(); // thực hiện update câu query đó
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
