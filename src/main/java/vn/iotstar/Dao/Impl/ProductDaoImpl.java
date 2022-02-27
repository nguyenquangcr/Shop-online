package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.Dao.DBConnection;
import vn.iotstar.Dao.IProductDao;
import vn.iotstar.model.Category;
import vn.iotstar.model.Product;
import vn.iotstar.model.Seller;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.ISellerService;
import vn.iotstar.service.impl.CategorySeviceImpl;
import vn.iotstar.service.impl.SellerServiceImpl;

public class ProductDaoImpl implements IProductDao {
	public Connection conn = null;
	public PreparedStatement ps = null; // nem cau query vao thuc ti du lieu
	public ResultSet rs = null;
	ISellerService sellerService = new SellerServiceImpl();
	ICategoryService categoryService = new CategorySeviceImpl();

	@Override
	public void insert(Product product) {
		String sql = "Insert INTO Product (productName,productCode,categoryId,description,price,stock,images,status,createDate,sellerId) Values (?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getProductname());
			ps.setLong(2, product.getProductcode());
			ps.setInt(3, product.getCategory().getCategoryid());
			ps.setString(4, product.getDescription());
			ps.setDouble(5, product.getPrice());
			ps.setInt(6, product.getStock());
			ps.setString(7, product.getImages());
			ps.setInt(8, product.getStatus());
			ps.setDate(9, product.getCreateDate());
			ps.setInt(10, product.getSeller().getSellerid());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Product product) {
		String sql = "Update Product SET productName=?,productCode=?,categoryId=?,description=?,price=?,stock=?,images=?,status=?,createDate=?,sellerId=? where productId=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getProductname());
			ps.setLong(2, product.getProductcode());
			ps.setInt(3, product.getCategory().getCategoryid());
			ps.setString(4, product.getDescription());
			ps.setDouble(5, product.getPrice());
			ps.setInt(6, product.getStock());
			ps.setString(7, product.getImages());
			ps.setInt(8, product.getStatus());
			ps.setDate(9, product.getCreateDate());
			ps.setInt(10, product.getSeller().getSellerid());
			ps.setInt(11, product.getProductid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from Product where productId=?";
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
	public Product findOne(int id) {
		String sql = "Select Product.productId, Product.productName, Product.productCode, Product.description, Product.amount, Product.price, Product.images, Product.createDate,\r\n"
				+ "Product.stock, Product.wishlist, Product.status, Category.categoryId, Category.categoryName, Seller.sellerId, Seller.images AS sellerAvatar, Seller.sellername\r\n"
				+ "from Product \r\n" + "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n"
				+ "INNER JOIN Seller ON Product.sellerId = Seller.sellerId\r\n" + "where productId =?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
//			System.out.println("rs: " + rs);
			while (rs.next()) {
				Category category = categoryService.findOne(rs.getInt("categoryId"));
				Seller seller = sellerService.findOne(rs.getInt("sellerId"));
				Product product = new Product();
				product.setProductid(rs.getInt("productId"));
				product.setProductcode(rs.getLong("productCode"));
				product.setProductname(rs.getString("productName"));
				product.setAmount(rs.getInt("amount"));
				product.setDescription(rs.getString("description"));
				product.setImages(rs.getString("images"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setWishlist(rs.getInt("wishlist"));
				product.setStatus(rs.getInt("status"));
				product.setCreateDate(rs.getDate("createDate"));
				product.setCategory(category);
				product.setSeller(seller);
				return product;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> findAll() {
		List<Product> productList = new ArrayList<Product>();
		String sql = "Select Product.productId, Product.productName, Product.productCode, Product.description, Product.amount, Product.price, Product.images, Product.createDate,\r\n"
				+ "Product.stock, Product.wishlist, Product.status, Category.categoryId, Category.categoryName, Seller.sellerId, Seller.images AS sellerAvatar, Seller.sellername\r\n"
				+ "from Product \r\n" + "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n"
				+ "INNER JOIN Seller ON Product.sellerId = Seller.sellerId";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = categoryService.findOne(rs.getInt("categoryId"));
				Seller seller = sellerService.findOne(rs.getInt("sellerId"));
				Product product = new Product();
				product.setProductid(rs.getInt("productId"));
				product.setProductcode(rs.getLong("productCode"));
				product.setProductname(rs.getString("productName"));
				product.setAmount(rs.getInt("amount"));
				product.setDescription(rs.getString("description"));
				product.setImages(rs.getString("images"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setWishlist(rs.getInt("wishlist"));
				product.setStatus(rs.getInt("status"));
				product.setCreateDate(rs.getDate("createDate"));
				product.setCategory(category);
				product.setSeller(seller);

				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

//	@Override
	public List<Product> findAllNew() {
		List<Product> productList = new ArrayList<Product>();
		String sql = "Select Product.productId, Product.productName, Product.productCode, Product.description, Product.amount, Product.price, Product.images, Product.createDate,\r\n"
				+ "Product.stock, Product.wishlist, Product.status, Category.categoryId, Category.categoryName, Seller.sellerId, Seller.images AS sellerAvatar, Seller.sellername\r\n"
				+ "from Product\r\n" + "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n"
				+ "INNER JOIN Seller ON Product.sellerId = Seller.sellerId\r\n" + "ORDER BY productId DESC";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = categoryService.findOne(rs.getInt("categoryId"));
				Seller seller = sellerService.findOne(rs.getInt("sellerId"));
				Product product = new Product();
				product.setProductid(rs.getInt("productId"));
				product.setProductcode(rs.getLong("productCode"));
				product.setProductname(rs.getString("productName"));
				product.setAmount(rs.getInt("amount"));
				product.setDescription(rs.getString("description"));
				product.setImages(rs.getString("images"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setWishlist(rs.getInt("wishlist"));
				product.setStatus(rs.getInt("status"));
				product.setCreateDate(rs.getDate("createDate"));
				product.setCategory(category);
				product.setSeller(seller);

				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<Product> findAllTopAmount() {
		List<Product> productList = new ArrayList<Product>();
		String sql = "Select Product.productId, Product.productName, Product.productCode, Product.description, Product.amount, Product.price, Product.images, Product.createDate,\r\n"
				+ "Product.stock, Product.wishlist, Product.status, Category.categoryId, Category.categoryName, Seller.sellerId, Seller.images AS sellerAvatar, Seller.sellername\r\n"
				+ "from Product\r\n" + "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n"
				+ "INNER JOIN Seller ON Product.sellerId = Seller.sellerId\r\n" + "ORDER BY amount DESC";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = categoryService.findOne(rs.getInt("categoryId"));
				Seller seller = sellerService.findOne(rs.getInt("sellerId"));
				Product product = new Product();
				product.setProductid(rs.getInt("productId"));
				product.setProductcode(rs.getLong("productCode"));
				product.setProductname(rs.getString("productName"));
				product.setAmount(rs.getInt("amount"));
				product.setDescription(rs.getString("description"));
				product.setImages(rs.getString("images"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setWishlist(rs.getInt("wishlist"));
				product.setStatus(rs.getInt("status"));
				product.setCreateDate(rs.getDate("createDate"));
				product.setCategory(category);
				product.setSeller(seller);

				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<Product> findAllByCID(int id, int index) {
		List<Product> productList = new ArrayList<Product>();
		String sql = "Select Product.productId, Product.productName, Product.productCode, Product.description, Product.amount, Product.price, Product.images, Product.createDate,\r\n"
				+ "Product.stock, Product.wishlist, Product.status, Category.categoryId, Category.categoryName, Seller.sellerId, Seller.images AS sellerAvatar, Seller.sellername\r\n"
				+ "from Product\r\n" + "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n"
				+ "INNER JOIN Seller ON Product.sellerId = Seller.sellerId\r\n"
				+ " WHERE Category.categoryId=? ORDER BY productId DESC OFFSET ? rows fetch next 3 rows only";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, index);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = categoryService.findOne(rs.getInt("categoryId"));
				Seller seller = sellerService.findOne(rs.getInt("sellerId"));
				Product product = new Product();
				product.setProductid(rs.getInt("productId"));
				product.setProductcode(rs.getLong("productCode"));
				product.setProductname(rs.getString("productName"));
				product.setAmount(rs.getInt("amount"));
				product.setDescription(rs.getString("description"));
				product.setImages(rs.getString("images"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setWishlist(rs.getInt("wishlist"));
				product.setStatus(rs.getInt("status"));
				product.setCreateDate(rs.getDate("createDate"));
				product.setCategory(category);
				product.setSeller(seller);

				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<Product> findAllBySeller(int id, int index) {
		List<Product> productList = new ArrayList<Product>();
		String sql = "Select Product.productId, Product.productName, Product.productCode, Product.description, Product.amount, Product.price, Product.images, Product.createDate,\r\n"
				+ "Product.stock, Product.wishlist, Product.status, Category.categoryId, Category.categoryName, Seller.sellerId, Seller.images AS sellerAvatar, Seller.sellername\r\n"
				+ "from Product\r\n" + "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n"
				+ "INNER JOIN Seller ON Product.sellerId = Seller.sellerId\r\n"
				+ " WHERE Seller.sellerId=? ORDER BY productId DESC OFFSET ? rows fetch next 3 rows only";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, index);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = categoryService.findOne(rs.getInt("categoryId"));
				Seller seller = sellerService.findOne(rs.getInt("sellerId"));
				Product product = new Product();
				product.setProductid(rs.getInt("productId"));
				product.setProductcode(rs.getLong("productCode"));
				product.setProductname(rs.getString("productName"));
				product.setAmount(rs.getInt("amount"));
				product.setDescription(rs.getString("description"));
				product.setImages(rs.getString("images"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setWishlist(rs.getInt("wishlist"));
				product.setStatus(rs.getInt("status"));
				product.setCreateDate(rs.getDate("createDate"));
				product.setCategory(category);
				product.setSeller(seller);

				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<Product> findAllPage(int indexp) {
		List<Product> productList = new ArrayList<Product>();
		String sql = "Select Product.productId, Product.productName, Product.productCode, Product.description, Product.amount, Product.price, Product.images, Product.createDate,\r\n"
				+ "Product.stock, Product.wishlist, Product.status, Category.categoryId, Category.categoryName, Seller.sellerId, Seller.images AS sellerAvatar, Seller.sellername\r\n"
				+ "from Product \r\n" + "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n"
				+ "INNER JOIN Seller ON Product.sellerId = Seller.sellerId\r\n"
				+ "ORDER BY productId DESC OFFSET ? rows fetch next 3 rows only";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, indexp);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = categoryService.findOne(rs.getInt("categoryId"));
				Seller seller = sellerService.findOne(rs.getInt("sellerId"));
				Product product = new Product();
				product.setProductid(rs.getInt("productId"));
				product.setProductcode(rs.getLong("productCode"));
				product.setProductname(rs.getString("productName"));
				product.setAmount(rs.getInt("amount"));
				product.setDescription(rs.getString("description"));
				product.setImages(rs.getString("images"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setWishlist(rs.getInt("wishlist"));
				product.setStatus(rs.getInt("status"));
				product.setCreateDate(rs.getDate("createDate"));
				product.setCategory(category);
				product.setSeller(seller);

				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public int countAll() {
		String sql = "select count(*) from product";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int countCID(int id) {
		String sql = "select count(*) from product where categoryId=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int countSell(int id) {
		String sql = "select count(*) from product where sellerId=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
}
