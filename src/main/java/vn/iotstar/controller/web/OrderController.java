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

import vn.iotstar.model.Cart;
import vn.iotstar.model.CartItems;
import vn.iotstar.model.Product;
import vn.iotstar.model.Users;
import vn.iotstar.service.ICartItemService;
import vn.iotstar.service.ICartService;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.CartItemServiceImpl;
import vn.iotstar.service.impl.CartServiceImp;
import vn.iotstar.service.impl.ProductServiceImpl;
import vn.iotstar.service.impl.UserSeviceImpl;
import vn.iotstar.util.RandomUUID;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/member/order" })

public class OrderController extends HttpServlet {
	IUserService userService = new UserSeviceImpl();
	ICartService cartService = new CartServiceImp();
	ICartItemService cartItemService = new CartItemServiceImpl();
	
	long time = System.currentTimeMillis();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		//kiem tra session
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("account");
		Users buyer = (Users) obj;
		
		Cart cart = new Cart();
		cart.setBuyer(buyer);
		cart.setBuyDate(new java.sql.Date(time));
		cart.setCartid(RandomUUID.getRandomID());
		cart.setStatus(0);
		
		cartService.insert(cart);
		
		//thiet lap session cho cart
		Object objCart = session.getAttribute("cart");
		if(objCart != null) {
			//ep ve dung kieu cua no khi them vao o phan them vao gio hang controller
			@SuppressWarnings("unchecked")
			Map<Integer, CartItems> map = (Map<Integer,CartItems>) objCart;
			
			for (CartItems cartitem: map.values()) {
				cartitem.setCart(cart);
				cartitem.setCartitemid(RandomUUID.getRandomID());
				cartItemService.insert(cartitem);
			}
		}
		session.removeAttribute("cart");
		resp.sendRedirect(req.getContextPath() + "/home");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
