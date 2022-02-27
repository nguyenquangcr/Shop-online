package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.Dao.DBConnection;
import vn.iotstar.Dao.ICartItemDao;
import vn.iotstar.model.Cart;
import vn.iotstar.model.CartItems;
import vn.iotstar.model.Product;
import vn.iotstar.model.Seller;
import vn.iotstar.model.Users;
import vn.iotstar.service.ICartService;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.ISellerService;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.CartServiceImp;
import vn.iotstar.service.impl.ProductServiceImpl;
import vn.iotstar.service.impl.SellerServiceImpl;
import vn.iotstar.service.impl.UserSeviceImpl;

public class CartItemDaoImp implements ICartItemDao{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	ICartService cartService = new CartServiceImp();
	IProductService productService = new ProductServiceImpl();
	IUserService userService = new UserSeviceImpl();
	ISellerService sellerService = new SellerServiceImpl();
	
	@Override
	public void insert(CartItems cartItem) {
		String sql = "INSERT INTO CartItem(cartitemid, cartid, productid, quantity, unitPrice) VALUES (?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cartItem.getCartitemid());
			ps.setString(2, cartItem.getCart().getCartid());
			ps.setInt(3, cartItem.getProduct().getProductid());
			ps.setInt(4, cartItem.getQuantity());
			ps.setDouble(5, cartItem.getUnitPrice());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CartItems cartItem) {
		String sql = "UPDATE CartItem SET cartid=? productid=? quantity=? unitPrice=? WHERE cartitemid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(5, cartItem.getCartitemid());
			ps.setString(1, cartItem.getCart().getCartid());
			ps.setInt(2, cartItem.getProduct().getProductid());
			ps.setInt(3, cartItem.getQuantity());
			ps.setDouble(4, cartItem.getUnitPrice());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM CartItem WHERE cartitemid=?";
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
	public CartItems findOne(String id) {
		String sql = "SELECT CartItem.cartitemid, CartItem.quantity, CartItem.unitPrice, Cart.userid, cart.buyDate, cart.status, cart.cartid AS cart_id, product.productname, product.price\r\n"
				+ "FROM CartItem\r\n"
				+ "INNER JOIN Cart on CartItem.cartid = cart.cartid\r\n"
				+ "INNER JOIN Product on CartItem.productid = Product.productid\r\n"
				+ "WHERE CartItem.cartitemid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("userid"));
				Cart cart = new Cart();
				Product product = new Product();
				CartItems cartItem = new CartItems();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setCartid(rs.getString("cart_id"));
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getDouble("unitPrice"));
				return cartItem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CartItems> findAll() {
		List<CartItems> cartItemList = new ArrayList<CartItems>();
		String sql = "SELECT CartItem.cartitemid, CartItem.quantity, CartItem.unitPrice, Cart.userid, cart.buyDate, cart.status, cart.cartid AS cart_id, product.productname, product.price\r\n"
				+ "FROM CartItem\r\n"
				+ "INNER JOIN Cart on CartItem.cartid = cart.cartid\r\n"
				+ "INNER JOIN Product on CartItem.productid = Product.productid";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("userid"));
				Cart cart = new Cart();
				Product product = new Product();
				CartItems cartItem = new CartItems();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setCartid(rs.getString("cart_id"));
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getDouble("unitPrice"));
				cartItemList.add(cartItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

	@Override
	public List<CartItems> findAllByUserId(int id) {
		List<CartItems> cartItemList = new ArrayList<CartItems>();
		String sql = "SELECT CartItem.cartitemid, CartItem.quantity, CartItem.unitPrice, Cart.userid, cart.buyDate, cart.status, cart.cartid AS cart_id, product.productname, product.price\r\n"
				+ "FROM CartItem\r\n"
				+ "INNER JOIN Cart on CartItem.cartid = cart.cartid\r\n"
				+ "INNER JOIN Product on CartItem.productid = Product.productid\r\n"
				+ "WHERE cart.userid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("userid"));
				Cart cart = new Cart();
				Product product = new Product();
				CartItems cartItem = new CartItems();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setCartid(rs.getString("cart_id"));
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				product.setImages(rs.getString("images"));
				cartItem.setCartitemid(rs.getString("cartitemid"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getDouble("unitPrice"));
				cartItemList.add(cartItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

	@Override
	public List<CartItems> findAllByUser(int id) {
		List<CartItems> cartItemList = new ArrayList<CartItems>();
		String sql = "SELECT CartItem.cartitemid, CartItem.quantity, CartItem.unitPrice, Cart.userid, cart.buyDate, cart.status, cart.cartid AS cart_id, product.productname, product.price, product.images, Seller.sellername, Seller.sellerid AS s_id,Seller.images AS avatar\r\n"
				+ "FROM CartItem\r\n"
				+ "INNER JOIN Cart on CartItem.cartid = cart.cartid\r\n"
				+ "INNER JOIN Product on CartItem.productid = Product.productid\r\n"
				+ "INNER JOIN Seller on product.sellerid = Seller.sellerid\r\n"
				+ "WHERE cart.userid=? order by cart.status ASC";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("userid"));
				Seller seller = sellerService.findOne(rs.getInt("s_id"));
				Cart cart = new Cart();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setCartid(rs.getString("cart_id"));
				
				Product product = new Product();
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				product.setImages(rs.getString("images"));
				product.setSeller(seller);
				
				CartItems cartItem = new CartItems();
				cartItem.setCartitemid(rs.getString("cartitemid"));
				cartItem.setCartid(rs.getString("cart_id"));
				cartItem.setProductid(rs.getInt("productid"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getDouble("unitPrice"));
				
				cartItemList.add(cartItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

	@Override
	public List<CartItems> findAllByUserPaging(int id, int index) {
		List<CartItems> cartItemList = new ArrayList<CartItems>();
		String sql = "SELECT CartItem.cartitemid, CartItem.quantity, CartItem.unitPrice,CartItem.cartid,CartItem.productid,Cart.userid, cart.buyDate, cart.status, cart.cartid AS cart_id, product.productname, product.price, product.images, Seller.sellername, Seller.sellerid AS s_id,Seller.images AS avatar\r\n"
				+ "FROM CartItem\r\n"
				+ "INNER JOIN Cart on CartItem.cartid = cart.cartid\r\n"
				+ "INNER JOIN Product on CartItem.productid = Product.productid\r\n"
				+ "INNER JOIN Seller on product.sellerid = Seller.sellerid\r\n"
				+ "WHERE cart.userid=? order by cart.status ASC OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, (index-1)*3);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("userid"));
				Seller seller = sellerService.findOne(rs.getInt("s_id"));
				
				Cart cart = new Cart();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setCartid(rs.getString("cart_id"));
				
				Product product = new Product();
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				product.setImages(rs.getString("images"));
				product.setSellerid(rs.getInt("s_id"));
				product.setSeller(seller);
				
				CartItems cartItem = new CartItems();
				cartItem.setCartitemid(rs.getString("cartitemid"));
				cartItem.setCartid(rs.getString("cart_id"));
				cartItem.setProductid(rs.getInt("productid"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getDouble("unitPrice"));
				
				cartItemList.add(cartItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

	@Override
	public int countByUser(int id) {
		String sql = "SELECT count(*) RFOM CartItem INNER JOIN Cart ON cart.cartid = CartItem.cartid WHERE Cart.userid=?";
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
	public List<CartItems> findAllBySeller(int id) {
		List<CartItems> cartItemList = new ArrayList<CartItems>();
		String sql = "SELECT CartItem.cartitemid, CartItem.quantity, CartItem.unitPrice,CartItem.cartid,CartItem.productid, Cart.userid, cart.buyDate, cart.status, cart.cartid AS cart_id, product.productname, product.price, product.images, Seller.sellername, Seller.sellerid AS s_id,Seller.images AS avatar\r\n"
				+ "FROM CartItem\r\n"
				+ "INNER JOIN Cart on CartItem.cartid = cart.cartid\r\n"
				+ "INNER JOIN Product on CartItem.productid = Product.productid\r\n"
				+ "INNER JOIN Seller on product.sellerid = Seller.sellerid\r\n"
				+ "WHERE Seller.sellerid=? order by cart.status ASC";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Users user = userService.findOne(rs.getInt("userid"));
				Seller seller = sellerService.findOne(rs.getInt("s_id"));
				Cart cart = new Cart();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setStatus(rs.getInt("status"));
				cart.setCartid(rs.getString("cart_id"));
				
				Product product = new Product();
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				product.setImages(rs.getString("images"));
				product.setSellerid(rs.getInt("s_id"));
				product.setSeller(seller);
				
				CartItems cartItem = new CartItems();
				cartItem.setCartitemid(rs.getString("cartitemid"));
				cartItem.setCartid(rs.getString("cartid"));
				cartItem.setProductid(rs.getInt("productid"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getDouble("unitPrice"));
				
				cartItemList.add(cartItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

}
