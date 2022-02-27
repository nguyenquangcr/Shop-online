package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.Dao.DBConnection;
import vn.iotstar.Dao.ISellerDao;
import vn.iotstar.model.Seller;

public class SellerDaoImpl implements ISellerDao {
	public Connection conn = null;
	public PreparedStatement ps = null; // nem cau query vao thuc ti du lieu
	public ResultSet rs = null;

	@Override
	public List<Seller> findAll() {
		List<Seller> sellerList = new ArrayList<Seller>();
		String sql = "select * From Seller";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Seller seller = new Seller();
				seller.setSellerid(rs.getInt("sellerid"));
				seller.setSellername(rs.getString("sellername"));
				seller.setImages(rs.getString("images"));
				seller.setStatus(rs.getInt("status"));
				sellerList.add(seller);
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sellerList;
	}

	@Override
	public Seller findOne(int id) {
		String sql = "Select * From Seller where sellerid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Seller seller = new Seller();
				seller.setSellerid(rs.getInt("Sellerid"));
				seller.setSellername(rs.getString("Sellername"));
				seller.setImages(rs.getString("images"));
				seller.setStatus(rs.getInt("status"));

				return seller;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Seller seller) {
		String sql = "Insert INTO Seller(sellername,images,status) Values (?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, seller.getSellername());
			ps.setString(2, seller.getImages());
			ps.setInt(3, seller.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Seller seller) {
		String sql = "UPDATE Seller SET sellername=?, images=?, status=? Where sellerid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, seller.getSellername());
			ps.setString(2, seller.getImages());
			ps.setInt(3, seller.getStatus());
			ps.setInt(4, seller.getSellerid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Seller Where sellerid=?";
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
