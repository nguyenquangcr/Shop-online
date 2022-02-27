package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.model.Category;
import vn.iotstar.model.Product;
import vn.iotstar.model.Seller;

import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.ISellerService;

import vn.iotstar.service.impl.CategorySeviceImpl;
import vn.iotstar.service.impl.ProductServiceImpl;
import vn.iotstar.service.impl.SellerServiceImpl;

import vn.iotstar.util.Constant;
import vn.iotstar.util.UploadUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/product", "/admin/product/create", "/admin/product/update", "/admin/product/edit",
		"/admin/product/delete", "/admin/product/reset" })
public class ProductController extends HttpServlet {
	ICategoryService categoryService = new CategorySeviceImpl();
	ISellerService sellerService = new SellerServiceImpl();
	IProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();

		Product product = null;

		if (url.contains("delete")) {
			delete(req, resp);
			product = new Product();
			req.setAttribute("product", product); // đẩy dữ liệu lên views
		} else if (url.contains("edit")) {
			edit(req, resp);
		} else if (url.contains("reset")) {
			product = new Product();
			req.setAttribute("product", product); // đẩy dữ liệu lên views
		}
		req.setAttribute("tag", "pro");
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-product.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();
		Product product = null;
		if (url.contains("create")) {
			create(req, resp);
		} else if (url.contains("update")) {
			update(req, resp);
		} else if (url.contains("reset")) {
			product = new Product();
			req.setAttribute("product", product);
		}
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-product.jsp");
		dispatcher.forward(req, resp);
	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Category> categoryList = categoryService.findAll(); // goi ham findAll trong service tra ve danh sacch
																		// doi tuong List<Category>
			req.setAttribute("categoryList", categoryList); // day danh sach len Views

			List<Seller> sellerList = sellerService.findAll(); // lay tat ca role trong bang seller
			req.setAttribute("sellerList", sellerList); // day danh sach sellerList len view

			List<Product> productList = productService.findAll(); // lay tat ca role trong bang product
//			System.out.println("productList: "+ productList);
			req.setAttribute("productList", productList); // day danh sach productList len view
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("productid"); // lay tham so tu views co Name = "id"
			// XOA ANH CU DI
			Product oldProduct = productService.findOne(Integer.parseInt(id));
			if (oldProduct.getImages() != null) {
				String fileName = oldProduct.getImages();
				UploadUtils.deleteFile(fileName, "\\products\\");
			}

			productService.delete(Integer.parseInt(id)); // goi ham delete trong service de xoa product thong qua
															// id(kieu so)
															// chuyen tu string sang int
			req.setAttribute("message", "Đã xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("productid"); // lay tham so tu views co name bang productid
			Product product = productService.findOne(Integer.parseInt(id)); // goi ham finOne trong service de lay 1
																			// user thong
			// qua id(kieu so) chuyen tu string sang int
			req.setAttribute("product", product); // day doi tuong user len view

			List<Category> categoryList = categoryService.findAll(); // goi ham findAll trong service tra ve danh sacch
																		// doi tuong List<Category>
			req.setAttribute("categoryList", categoryList); // day danh sach len Views

			List<Seller> sellerList = sellerService.findAll(); // lay tat ca role trong bang seller
			req.setAttribute("sellerList", sellerList); // day danh sach seller len view

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			// lay du lieu tu jsp bang BeanUtils
			Product product = new Product();
			BeanUtils.populate(product, req.getParameterMap());
			// xu ly anh
			if (req.getPart("images").getSize() != 0) {
				// xu ly anh
				String fileName = "" + System.currentTimeMillis();
				product.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\products\\", fileName));
			}
			// lấy dữ liệu category từ bảng Category
			product.setCategory(categoryService.findOne(product.getCategoryid()));
			// lấy dữ liệu seller từ bảng Seller
			product.setSeller(sellerService.findOne(product.getSellerid()));

			// goi phuong thuc insert trong Service
			productService.insert(product);

			req.setAttribute("product", product);
			req.setAttribute("message", "Cập nhật thành công");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			// lấy dữ liệu từ views jsp bằng BeanUtils
			Product product = new Product();
			BeanUtils.populate(product, req.getParameterMap());
			// xử lý hình ảnh
			// khởi tạo DAO

			Product oldProduct = productService.findOne((product.getProductid()));
			if (req.getPart("images").getSize() == 0) {
				product.setImages(oldProduct.getImages());
			} else {
				if (oldProduct.getImages() != null) {
					// XOA ANH CU DI
					String fileName = oldProduct.getImages();
					UploadUtils.deleteFile(fileName, "\\products\\");
					// xử lý hình ảnh
					fileName = "" + System.currentTimeMillis();
					product.setImages(
							UploadUtils.processUpload("images", req, Constant.DIR + "\\products\\", fileName));
				}
			}
			// lấy dữ liệu category từ bảng Category
			product.setCategory(categoryService.findOne(product.getCategoryid()));
			// lấy dữ liệu seller từ bảng Seller
			product.setSeller(sellerService.findOne(product.getSellerid()));
			// gọi phương thức update trong Service
			productService.update(product);

			req.setAttribute("product", product);
			req.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}

	}

}
