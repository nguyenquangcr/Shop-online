package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.Dao.DBConnection;
import vn.iotstar.Dao.ICartDao;
import vn.iotstar.model.Cart;
import vn.iotstar.model.Users;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.UserSeviceImpl;

public class CartDaoImp implements ICartDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	IUserService userService = new UserSeviceImpl();

	@Override
	public void insert(Cart cart) {
		String sql = "INSERT INTO Cart(cartid,userid,buyDate,status) VALUES (?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cart.getCartid());
			ps.setInt(2, cart.getBuyer().getUserid());
			ps.setDate(3, new Date(cart.getBuyDate().getTime()));
			ps.setInt(4, cart.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Cart cart) {
		String sql = "UPDATE Cart SET userid=?,buyDate=?,status=? WHERE cartid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(4, cart.getCartid());
			ps.setInt(1, cart.getBuyer().getUserid());
			ps.setDate(2, new Date(cart.getBuyDate().getTime()));
			ps.setInt(3, cart.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM Cart WHERE cartid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStatus(String id, int st) {
		String sql = "UPDATE Cart SET status=? WHERE cartid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, st);
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Cart findOne(String id) {
		String sql = "SELECT Cart.cartId, Cart.buyDate, Cart.status, Users.sellerId, Users.email,Users.username, Users.userId AS user_id\r\n"
				+ "FROM Cart\r\n" + "INNER JOIN Users on Cart.userId = Users.userId\r\n" + "WHERE Cart.cartId=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("user_id"));
				Cart cart = new Cart();
				cart.setCartid(rs.getString("cartid"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setBuyer(user);
				return cart;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cart> findAll() {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT Cart.cartId, Cart.buyDate, Cart.status, Users.sellerId, Users.email,Users.username, Users.userId AS user_id\r\n"
				+ "FROM Cart\r\n" + "INNER JOIN Users on Cart.userId = Users.userId";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("user_id"));
				Cart cart = new Cart();
				cart.setCartid(rs.getString("cartid"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setBuyer(user);
				cartList.add(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public List<Cart> findAllByUser(int id) {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT Cart.cartId, Cart.buyDate, Cart.status, Users.sellerId, Users.email,Users.username, Users.userId AS user_id\r\n"
				+ "FROM Cart\r\n" + "INNER JOIN Users on Cart.userId = Users.userId\r\n"
				+ "WHERE Users.userId=? ORDER BY buyDate, status";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("user_id"));
				Cart cart = new Cart();
				cart.setCartid(rs.getString("cartid"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setBuyer(user);
				cartList.add(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public int countByUser(int id) {
		String sql = "SELECT count(*) RFOM Cart WHERE Cart.userid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs= ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countByStatus(int id, int status) {
		String sql = "SELECT count(*) RFOM Cart WHERE Cart.userid=? and Cart.status=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, status);
			rs= ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
