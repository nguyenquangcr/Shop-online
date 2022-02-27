package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.Dao.DBConnection;
import vn.iotstar.Dao.ICategoryDao;
import vn.iotstar.Dao.IUserDao;
import vn.iotstar.model.Category;
import vn.iotstar.model.UserRoles;
import vn.iotstar.model.Users;
import vn.iotstar.service.IUserRoleService;
import vn.iotstar.service.impl.UserRoleServiceImpl;

public class CategoryDaoImpl implements ICategoryDao {

	public Connection conn = null;
	public PreparedStatement ps = null; // nem cau query vao thuc ti du lieu
	public ResultSet rs = null;

	@Override
	public List<Category> findAll() {
		List<Category> categoryList = new ArrayList<Category>();
		String sql = "select * From Category";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				categoryList.add(category);
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@Override
	public Category findOne(int id) {
		String sql = "Select * From Category where categoryid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));

				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Category category) {
		String sql = "Insert INTO Category(categoryname,images,status) Values (?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Category category) {
		String sql = "UPDATE Category SET categoryname=?, images=?, status=? Where categoryid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getCategoryid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Category Where categoryid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
