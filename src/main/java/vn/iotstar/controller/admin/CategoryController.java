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
import javax.swing.text.AbstractDocument.Content;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import vn.iotstar.model.Category;
import vn.iotstar.model.UserRoles;
import vn.iotstar.model.Users;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IUserRoleService;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.CategorySeviceImpl;
import vn.iotstar.service.impl.UserRoleServiceImpl;
import vn.iotstar.service.impl.UserSeviceImpl;
import vn.iotstar.util.Constant;
import vn.iotstar.util.UploadUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/category", "/admin/category/create", "/admin/category/update",
		"/admin/category/edit", "/admin/category/delete", "/admin/category/reset" })

public class CategoryController extends HttpServlet {
	ICategoryService categoryService = new CategorySeviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();

		Category category = null;

		if (url.contains("delete")) {
			delete(req, resp);
			category = new Category();
			req.setAttribute("category", category); // đẩy dữ liệu lên views
		} else if (url.contains("edit")) {
			edit(req, resp);
		} else if (url.contains("reset")) {
			category = new Category();
			req.setAttribute("category", category); // đẩy dữ liệu lên views
		}
		req.setAttribute("tag", "cate");
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();
		Category category = null;
		if (url.contains("create")) {
			create(req, resp);
		} else if (url.contains("update")) {
			update(req, resp);
		} else if (url.contains("delete")) {
			delete(req, resp);
		} else if (url.contains("reset")) {
			category = new Category();
			req.setAttribute("category", category);
		}
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-category.jsp");
		dispatcher.forward(req, resp);
	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Category> categoryList = categoryService.findAll(); // goi ham findAll trong servicetra ve danh sacch
																		// doi tuong List<Category>
			req.setAttribute("categoryList", categoryList); // day danh sach len Views
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("categoryid"); // lay tham so tu views co Name = "id"
			// XOA ANH CU DI
			Category oldCategory = categoryService.findOne(Integer.parseInt(id));
			if (oldCategory.getImages() != null) {
				String fileName = oldCategory.getImages();
				UploadUtils.deleteFile(fileName, "\\category\\");
			}

			categoryService.delete(Integer.parseInt(id)); // goi ham delete trong service de xoa user thong qua id(kieu
															// so) chuyen tu string sang int
			req.setAttribute("message", "Đã xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("categoryid"); // lay tham so tu views co name bang id
			Category category = categoryService.findOne(Integer.parseInt(id)); // goi ham finOne trong service de lay 1
																				// user thong
			// qua id(kieu so) chuyen tu string sang int
			req.setAttribute("category", category); // day doi tuong user len view

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
			Category category = new Category();
			BeanUtils.populate(category, req.getParameterMap());
			// xu ly anh
			if (req.getPart("images").getSize() != 0) {
				// xu ly anh
				String fileName = "" + System.currentTimeMillis();
				category.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\category\\", fileName));
			}
			// goi phuong thuc update torng Service
			categoryService.insert(category);

			req.setAttribute("category", category);
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
			Category category = new Category();
			BeanUtils.populate(category, req.getParameterMap());
			// xử lý hình ảnh
			// khởi tạo DAO
			Category oldCategory = categoryService.findOne(category.getCategoryid());
			if (req.getPart("images").getSize() == 0) {
				category.setImages(oldCategory.getImages());
			} else {
				if (oldCategory.getImages() != null) {
					// XOA ANH CU DI
					String fileName = oldCategory.getImages();
					UploadUtils.deleteFile(fileName, "\\users\\");
					// xử lý hình ảnh
					fileName = "" + System.currentTimeMillis();
					category.setImages(
							UploadUtils.processUpload("images", req, Constant.DIR + "\\category\\", fileName));
				}
			}

			// gọi phương thức update trong Service
			categoryService.update(category);

			req.setAttribute("user", category);
			req.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}

	}

}
