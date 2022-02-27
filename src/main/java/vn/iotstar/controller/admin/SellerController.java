package vn.iotstar.controller.admin;
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

import vn.iotstar.model.Seller;
import vn.iotstar.service.ISellerService;
import vn.iotstar.service.impl.SellerServiceImpl;
import vn.iotstar.util.Constant;
import vn.iotstar.util.UploadUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/seller", "/admin/seller/create", "/admin/seller/update",
		"/admin/seller/edit", "/admin/seller/delete", "/admin/seller/reset" })

public class SellerController extends HttpServlet {
	ISellerService sellerService = new SellerServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();

		Seller seller = null;

		if (url.contains("delete")) {
			delete(req, resp);
			seller = new Seller();
			req.setAttribute("seller", seller); // đẩy dữ liệu lên views
		} else if (url.contains("edit")) {
			edit(req, resp);
		} else if (url.contains("reset")) {
			seller = new Seller();
			req.setAttribute("seller", seller); // đẩy dữ liệu lên views
		}
		req.setAttribute("tag", "sell");
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-seller.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();
		Seller seller = null;
		if (url.contains("create")) {
			create(req, resp);
		} else if (url.contains("update")) {
			update(req, resp);
		} else if (url.contains("delete")) {
			delete(req, resp);
		} else if (url.contains("reset")) {
			seller = new Seller();
			req.setAttribute("seller", seller);
		}
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-seller.jsp");
		dispatcher.forward(req, resp);
	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Seller> sellerList = sellerService.findAll(); // goi ham findAll trong servicetra ve danh sacch
																		// doi tuong List<seller>
			req.setAttribute("sellerList", sellerList); // day danh sach len Views
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("sellerid"); // lay tham so tu views co Name = "id"
			// XOA ANH CU DI
			Seller oldSeller = sellerService.findOne(Integer.parseInt(id));
			if (oldSeller.getImages() != null) {
				String fileName = oldSeller.getImages();
				UploadUtils.deleteFile(fileName, "\\category\\");
			}

			sellerService.delete(Integer.parseInt(id)); // goi ham delete trong service de xoa user thong qua id(kieu
															// so) chuyen tu string sang int
			req.setAttribute("message", "Đã xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("sellerid"); // lay tham so tu views co name bang id
			Seller seller = sellerService.findOne(Integer.parseInt(id)); // goi ham finOne trong service de lay 1
																				// user thong
			// qua id(kieu so) chuyen tu string sang int
			req.setAttribute("seller", seller); // day doi tuong user len view

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
			Seller seller = new Seller();
			BeanUtils.populate(seller, req.getParameterMap());
			// xu ly anh
			if (req.getPart("images").getSize() != 0) {
				// xu ly anh
				String fileName = "" + System.currentTimeMillis();
				seller.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\seller\\", fileName));
			}
			// goi phuong thuc update torng Service
			sellerService.insert(seller);

			req.setAttribute("seller", seller);
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
			Seller seller = new Seller();
			BeanUtils.populate(seller, req.getParameterMap());
			// xử lý hình ảnh
			// khởi tạo DAO
			Seller oldSeller = sellerService.findOne(seller.getSellerid());
			if (req.getPart("images").getSize() == 0) {
				seller.setImages(oldSeller.getImages());
			} else {
				if (oldSeller.getImages() != null) {
					// XOA ANH CU DI
					String fileName = oldSeller.getImages();
					UploadUtils.deleteFile(fileName, "\\users\\");
					// xử lý hình ảnh
					fileName = "" + System.currentTimeMillis();
					seller.setImages(
							UploadUtils.processUpload("images", req, Constant.DIR + "\\seller\\", fileName));
				}
			}

			// gọi phương thức update trong Service
			sellerService.update(seller);

			req.setAttribute("user", seller);
			req.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}

	}

}
