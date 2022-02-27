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
import vn.iotstar.model.Slides;
import vn.iotstar.service.ISlidesService;
import vn.iotstar.service.impl.SlidesSeviceImpl;
import vn.iotstar.util.Constant;
import vn.iotstar.util.UploadUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/slides", "/admin/slides/create", "/admin/slides/update", "/admin/slides/edit",
		"/admin/slides/delete", "/admin/slides/reset" })

public class SlidesController extends HttpServlet {
	ISlidesService slidesService = new SlidesSeviceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();

		Slides slides = null;

		if (url.contains("delete")) {
			delete(req, resp);
			slides = new Slides();
			req.setAttribute("slides", slides); // đẩy dữ liệu lên views
		} else if (url.contains("edit")) {
			edit(req, resp);
		} else if (url.contains("reset")) {
			slides = new Slides();
			req.setAttribute("slides", slides); // đẩy dữ liệu lên views
		}
		req.setAttribute("tag", "cate");
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-slides.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();
		Slides slides = null;
		if (url.contains("create")) {
			create(req, resp);
		} else if (url.contains("update")) {
			update(req, resp);
		} else if (url.contains("delete")) {
			delete(req, resp);
		} else if (url.contains("reset")) {
			slides = new Slides();
			req.setAttribute("slides", slides);
		}
		findAll(req, resp); // hiện danh sách user trong model
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-slides.jsp");
		dispatcher.forward(req, resp);
	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Slides> slidesList = slidesService.findAll(); // goi ham findAll trong servicetra ve danh sacch
																// doi tuong List<Slides>
			req.setAttribute("slidesList", slidesList); // day danh sach len Views
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("slideid"); // lay tham so tu views co Name = "id"
			// XOA ANH CU DI
			Slides oldSlides = slidesService.findOne(Integer.parseInt(id));
			if (oldSlides.getSlideimages() != null) {
				String fileName = oldSlides.getSlideimages();
				UploadUtils.deleteFile(fileName, "\\slides\\");
			}

			slidesService.delete(Integer.parseInt(id)); // goi ham delete trong service de xoa user thong qua id(kieu
														// so) chuyen tu string sang int
			req.setAttribute("message", "Đã xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("slideid"); // lay tham so tu views co name bang id
			Slides slides = slidesService.findOne(Integer.parseInt(id)); // goi ham finOne trong service de lay 1
																			// user thong
			// qua id(kieu so) chuyen tu string sang int
			req.setAttribute("slides", slides); // day doi tuong user len view

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
			Slides slides = new Slides();
			BeanUtils.populate(slides, req.getParameterMap());
			// xu ly anh
			if (req.getPart("images").getSize() != 0) {
				// xu ly anh
				String fileName = "" + System.currentTimeMillis();
				slides.setSlideimages(UploadUtils.processUpload("images", req, Constant.DIR + "\\slides\\", fileName));
			}
			// goi phuong thuc update torng Service
			slidesService.insert(slides);

			req.setAttribute("slides", slides);
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
			Slides slides = new Slides();
			BeanUtils.populate(slides, req.getParameterMap());
			// xử lý hình ảnh
			// khởi tạo DAO
			Slides oldSlides = slidesService.findOne(slides.getSlideid());
			if (req.getPart("images").getSize() == 0) {
				slides.setSlideimages(oldSlides.getSlideimages());
			} else {
				if (oldSlides.getSlideimages() != null) {
					// XOA ANH CU DI
					String fileName = oldSlides.getSlideimages();
					UploadUtils.deleteFile(fileName, "\\users\\");
					// xử lý hình ảnh
					fileName = "" + System.currentTimeMillis();
					slides.setSlideimages(
							UploadUtils.processUpload("images", req, Constant.DIR + "\\slides\\", fileName));
				}
			}

			// gọi phương thức update trong Service
			slidesService.update(slides);

			req.setAttribute("slides", slides);
			req.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}

	}

}
