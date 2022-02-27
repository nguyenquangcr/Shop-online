package vn.iotstar.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.model.CartItems;
import vn.iotstar.model.Product;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.impl.ProductServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/member/cart", "/member/cart/add", "/member/cart/remove", "/member/cart/updatequantity" })
public class CartController extends HttpServlet {
	IProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI().toString();
		if (url.contains("cart/add")) {
			addCart(req, resp);
		}else if(url.contains("cart/updatequantity")) {
			updateQuantity(req,resp);
		} else if (url.contains("cart/remove")) {
			removeItem(req, resp);
		} else {
			listCart(req, resp);
		}
	}

	private void updateQuantity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// nhan tham so tu views
		String pId = req.getParameter("pId");
		String quantity = req.getParameter("quantity");
		//truy van Product bang pId
		Product product = productService.findOne(Integer.parseInt(pId));
		
		CartItems cartItem = new CartItems();
		cartItem.setQuantity(Integer.parseInt(quantity));
		cartItem.setUnitPrice(product.getPrice());
		cartItem.setProduct(product);
		
		//tao session
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("cart");
		if(obj == null) {
			Map<Integer, CartItems> map = new HashMap<Integer, CartItems>();
			map.put(cartItem.getProduct().getProductid(), cartItem);
			session.setAttribute("cart", map);
		}else {
			Map<Integer, CartItems> map = exatracted(obj);
			CartItems existCartItem = map.get(Integer.valueOf(pId));
			if(existCartItem == null) {
				map.put(product.getProductid(), cartItem);
			}else {
				existCartItem.setQuantity(Integer.parseInt(quantity));
			}
			session.setAttribute("cart", map);
		}
		resp.sendRedirect(req.getContextPath() + "/memeber/cart");
	}

	protected void listCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/web/cart-list.jsp");
		dispatcher.forward(req, resp);
	};

	protected void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// nhan tham so tu views
		String pId = req.getParameter("pId");
		String quantity = req.getParameter("quantity");
		// truy van Product bang pId
		Product product = productService.findOne(Integer.parseInt(pId));

		CartItems cartItem = new CartItems();
		cartItem.setQuantity(Integer.parseInt(quantity));
		cartItem.setUnitPrice(product.getPrice());
		cartItem.setProduct(product);

		// Tao session
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("cart");
		if (obj == null) {
			Map<Integer, CartItems> map = new HashMap<Integer, CartItems>();
			map.put(cartItem.getProduct().getProductid(), cartItem);
			session.setAttribute("cart", map);
		} else {
			Map<Integer, CartItems> map = exatracted(obj);
			CartItems exitstCartItem = map.get(Integer.valueOf(pId));
			if (exitstCartItem == null) {
				map.put(cartItem.getProduct().getProductid(), cartItem);
			} else {
				exitstCartItem.setQuantity(exitstCartItem.getQuantity() + Integer.parseInt(quantity));
			}
			session.setAttribute("cart", map);
		}
		resp.sendRedirect(req.getContextPath() + "/member/cart");
	};

	private Map<Integer, CartItems> exatracted(Object obj) {
		return (Map<Integer, CartItems>) obj;
	}

	protected void removeItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// nhan tham so tu views
		String pId = req.getParameter("pId");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("cart");
		if (obj != null) {
			@SuppressWarnings("unchecked")
			Map<Integer, CartItems> map = (Map<Integer, CartItems>) obj;
			map.remove(Integer.parseInt(pId));
			session.setAttribute("cart", map);
		}
		resp.sendRedirect(req.getContextPath() + "/member/cart");
	};
}
