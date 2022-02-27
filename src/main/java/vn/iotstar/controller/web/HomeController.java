package vn.iotstar.controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.model.Category;
import vn.iotstar.model.Product;
import vn.iotstar.model.Seller;
import vn.iotstar.model.Slides;
import vn.iotstar.model.Users;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.ISellerService;
import vn.iotstar.service.ISlidesService;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.CategorySeviceImpl;
import vn.iotstar.service.impl.ProductServiceImpl;
import vn.iotstar.service.impl.SellerServiceImpl;
import vn.iotstar.service.impl.SlidesSeviceImpl;
import vn.iotstar.service.impl.UserSeviceImpl;
import vn.iotstar.util.Constant;
import vn.iotstar.util.Email;

@WebServlet(urlPatterns = { "/home", "/login", "/register", "/forgotpass", "/waiting", "/VerifyCode", "/logout" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 5889168824989045500L;

	ICategoryService cateService = new CategorySeviceImpl();
	IUserService userService = new UserSeviceImpl();
	IProductService productService = new ProductServiceImpl();
	ICategoryService categoryService = new CategorySeviceImpl();
	ISellerService sellerService = new SellerServiceImpl();
	ISlidesService slidesService = new SlidesSeviceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("register")) {
			getRegister(req, resp);
		} else if (url.contains("login")) {
			getLogin(req, resp);
		} else if (url.contains("forgotpass")) {
			req.getRequestDispatcher("views/web/forgotpassword.jsp").forward(req, resp);
		} else if (url.contains("waiting")) {
			getWaiting(req, resp);
		} else if (url.contains("VerifyCode")) {
			req.getRequestDispatcher("views/web/verify.jsp").forward(req, resp);
		} else if (url.contains("logout")) {
			getLogout(req, resp);
		} else {
			homePage(req, resp);
		}
	}

	private void getLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		session.removeAttribute("account"); // remove session

		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					break;
				}
			}
		}
		resp.sendRedirect("./login");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("register")) {
			postRegister(req, resp);
		} else if (url.contains("login")) {
			postLogin(req, resp);
		} else if (url.contains("forgotpass")) {
			postForgotPassword(req, resp);
		} else if (url.contains("VerifyCode")) {
			postVerifyCode(req, resp);
		}
	}// home voi method Get

	protected void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> productListNew = productService.findAllNew();
		List<Category> categoryList = categoryService.findAll();
		List<Seller> sellerList = sellerService.findAll();
		List<Product> productListTopAmount = productService.findAllTopAmount();
		List<Slides> slidesList = slidesService.findAll(); // goi ham findAll trong servicetra ve danh sacch
		// doi tuong List<Slides>
		req.setAttribute("slidesList", slidesList); // day danh sach len Views
		req.setAttribute("productListTopAmount", productListTopAmount);
		req.setAttribute("sellerList", sellerList);
		req.setAttribute("productListNew", productListNew);
		req.setAttribute("categoryList", categoryList);
		req.getRequestDispatcher("views/web/home.jsp").forward(req, resp);

		// xử lý slide số 2
		for (Slides slide : slidesList) {
			if (slide.getSlidestyle() == 2) {
				req.setAttribute("slide_id", slide.getSlideid());
				break;
			}
		}

	}

	protected void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/web/register.jsp").forward(req, resp);
	}

	protected void postLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean isRememberMe = false;
		String remember = req.getParameter("remember");

		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		String alertMsg = "";
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tai khoan hoac mat khau khong dung";
			req.setAttribute("error", alertMsg);
			req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
			return;
		}

		Users user = userService.login(username, password);
		if (user != null) {
			if (user.getStatus() == 1) {
				// tao session
				HttpSession session = req.getSession(true);
				session.setAttribute("account", user);
				if (isRememberMe) {
					saveRememberMe(resp, username);
				}

				resp.sendRedirect(req.getContextPath() + "/waiting");
			} else {
				alertMsg = "Tai khoan da bi khoa, vui long lien he Admin";
				req.setAttribute("error", alertMsg);
				req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
			}
		} else {
			alertMsg = "Tai khoan hoac mat khau khong dung";
			req.setAttribute("error", alertMsg);
			req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
		}
	}

	private void saveRememberMe(HttpServletResponse resp, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		resp.addCookie(cookie);
	}

	protected void getWaiting(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// kiem tra session
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			Users u = (Users) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			if (u.getRoles().getRoleid() == 2) {
				resp.sendRedirect(req.getContextPath() + "/admin/home");
			} else if (u.getRoleid() == 2019) {
				resp.sendRedirect(req.getContextPath() + "/manager/home");
			} else if (u.getRoleid() == 2020) {
				resp.sendRedirect(req.getContextPath() + "/seller/home");
			} else {
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	protected void postForgotPassword(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String email = req.getParameter("email");
		Users user = userService.findOne(username);

		if (user.getEmail().equals(email) && user.getUsername().equals(username)) {
			Email sm = new Email();
			boolean test = sm.sendEmail(user);
			if (test) {
				req.setAttribute("message", "Vui long kiem tra email de nhan mat khau moi !");
			} else {
				req.setAttribute("error", "Loi gui mail!");
			}
		} else {
			req.setAttribute("error", "Username hoac Email khong ton tai trong he thong!");
			req.getRequestDispatcher("views/web/forgotpassword.jsp").forward(req, resp);
			return;
		}
		req.getRequestDispatcher("views/web/forgotpassword.jsp").forward(req, resp);
	}

	// login voi method post
	protected void getLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// check session
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}
		// check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/waiting");
					return;
				}
			}
		}
		req.getRequestDispatcher("views/web/login.jsp").forward(req, resp);
	}

	protected void postRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// lay tham so tu form
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");

		String alerMsg = "";
		if (userService.checkExisEmail(email)) {
			alerMsg = "Email đã tồn tài";
			req.setAttribute("error", alerMsg);
			req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
		} else if (userService.checkExisEmail(username)) {
			alerMsg = "Tài khoản đã tồn tại!";
			req.setAttribute("error", alerMsg);
			req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
		} else {
			Email sm = new Email();
			String code = sm.getRandom();
			Users user = new Users(username, email, fullname, code);
			boolean test = sm.sendEmail(user);
			if (test) {
				HttpSession session = req.getSession();
				session.setAttribute("account", user);
				Boolean isSuccess = userService.register(email, password, username, fullname, code);
				if (isSuccess) {
					resp.sendRedirect(req.getContextPath() + "/VerifyCode");
				} else {
					alerMsg = "Lỗi hệ thống!";
					req.setAttribute("error", alerMsg);
					req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
				}
			} else {
				PrintWriter out = resp.getWriter();
				out.println("Lỗi khi gửi mail!!!!!");
			}
		}
	}

	protected void postVerifyCode(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {

			HttpSession session = req.getSession();
			Users user = (Users) session.getAttribute("account");
			String code = req.getParameter("authcode");
			if (code.equals(user.getCode())) {
				user.setEmail(user.getEmail());
				user.setStatus(1);
				userService.updatestatus(user);
				out.println("<div class=\"container\"><br/>\r\n" + "     <br/>\r\n"
						+ " <br/>Kich hoat thanh cong!<br/>\r\n" + "<br/>\r\n" + "<br/></div>");
			} else {
				out.println("<div class=\"container\"><br/>\r\n" + "     <br/>\r\n"
						+ " <br/>Sai ma kich hoat, vui long kiem tra lai<br/>\r\n" + "<br/>\r\n" + "<br/></div>");
			}
		}
	}
}
