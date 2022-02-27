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

import vn.iotstar.model.UserRoles;
import vn.iotstar.model.Users;
import vn.iotstar.service.IUserRoleService;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.UserRoleServiceImpl;
import vn.iotstar.service.impl.UserSeviceImpl;
import vn.iotstar.util.Constant;
import vn.iotstar.util.UploadUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/user", "/admin/user/create", "/admin/user/update", "/admin/user/edit",
		"/admin/user/delete", "/admin/user/reset" })
public class UserController extends HttpServlet {
	IUserRoleService userRoleService = new UserRoleServiceImpl();
	IUserService userService = new UserSeviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();

		Users user = null;

		if (url.contains("delete")) {
			delete(req, resp);
			user = new Users();
			req.setAttribute("user", user); // đẩy dữ liệu lên views
		} else if (url.contains("edit")) {
			edit(req, resp);
		} else if (url.contains("reset")) {
			user = new Users();
			req.setAttribute("user", user); // đẩy dữ liệu lên views
		}
		req.setAttribute("tag", "use");
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-user.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();
		Users user = null;
		if (url.contains("create")) {
			create(req, resp);
		} else if (url.contains("update")) {
			update(req, resp);
		} else if (url.contains("reset")) {
			user = new Users();
			req.setAttribute("user", user);
		}
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-user.jsp");
		dispatcher.forward(req, resp);
	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Users> userList = userService.findAll(); // goi ham findAll trong servicetra ve danh sacch doi tuong
															// List<User>
			req.setAttribute("userList", userList); // day danh sach len Views
			List<UserRoles> roles = userRoleService.findAll(); // lay tat ca role trong bang roles
			req.setAttribute("roles", roles); // day danh sach role len view
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("userid"); // lay tham so tu views co Name = "id"
			// XOA ANH CU DI
			Users oldUser = userService.findOne(Integer.parseInt(id));
			if (oldUser.getImages() != null) {
				String fileName = oldUser.getImages();
				UploadUtils.deleteFile(fileName, "\\users\\");
			}

			userService.delete(Integer.parseInt(id)); // goi ham delete trong service de xoa user thong qua id(kieu so)
														// chuyen tu string sang int
			req.setAttribute("message", "Đã xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("userid"); // lay tham so tu views co name bang id
			Users user = userService.findOne(Integer.parseInt(id)); // goi ham finOne trong service de lay 1 user thong
																	// qua id(kieu so) chuyen tu string sang int
			req.setAttribute("user", user); // day doi tuong user len view
			List<UserRoles> roles = userRoleService.findAll();
			req.setAttribute("roles", roles);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Users user = new Users();

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory); // lay fike tren dia bo vao
																							// upload
		try {
			List<FileItem> items = servletFileUpload.parseRequest(req); // doc tung dong trong file item ra
			for (FileItem item : items) { // dung vong lap for doi tuong
				if (item.getFieldName().equals("email")) {
					if (userService.checkExisEmail("email")) {
						req.setAttribute("error", "Email đã tồn tại");
						return;
					} else
						user.setEmail(item.getString());
				} else if (item.getFieldName().equals("username")) {
					if (userService.checkExisUsername(item.getString())) {
						req.setAttribute("error", "Username đã tồn tại");
						return;
					} else
						user.setUsername(item.getString());
				} else if (item.getFieldName().equals("fullname")) {
					user.setFullname(item.getString("UTF-8"));
				} else if (item.getFieldName().equals("password")) {
					user.setPassword(item.getString());
				} else if (item.getFieldName().equals("roleid")) {
					user.setRoles(userRoleService.findOne(Integer.parseInt(item.getString())));
				} else if (item.getFieldName().equals("status")) {
					user.setStatus(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("phone")) {
					user.setPhone(item.getString());
				} else if (item.getFieldName().equals("images")) {
					if (item.getSize() > 0) {
						String ofFileName = item.getName();
						int index = ofFileName.lastIndexOf("."); // tim dau cham trong file
						String ext = ofFileName.substring(index + 1);
						String fileName = System.currentTimeMillis() + "." + ext; // ham lay gio he thong
						File file = new File(Constant.DIR + "/users/" + fileName);
						item.write(file);
						user.setImages(fileName);
					} else {
						user.setImages("avatar.png");
					}
				}
			}
			userService.insert(user);
			req.setAttribute("user", user);
			req.setAttribute("message", "Đã thêm thành công");
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
			Users user = new Users();
			BeanUtils.populate(user, req.getParameterMap());
//			System.out.println("user: " + user);
			// xử lý hình ảnh
			// khởi tạo DAO

			Users oldUser = userService.findOne(user.getUserid());
			if (req.getPart("images").getSize() == 0) {
				user.setImages(oldUser.getImages());
			} else {
				if (oldUser.getImages() != null) {
					// XOA ANH CU DI
					String fileName = oldUser.getImages();
					UploadUtils.deleteFile(fileName, "\\users\\");
					// xử lý hình ảnh
					fileName = "" + System.currentTimeMillis();
					user.setImages(UploadUtils.processUpload("images", req, Constant.DIR + "\\users\\", fileName));
				}
			}
			// lấy dữ liệu roles từ bản UserRoles
			user.setRoles(userRoleService.findOne(user.getRoleid()));
			// gọi phương thức update trong Service
			userService.update(user);

			req.setAttribute("user", user);
			req.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}

	}

}
