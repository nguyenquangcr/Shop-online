package vn.iotstar.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.model.UserRoles;
import vn.iotstar.service.IUserRoleService;
import vn.iotstar.service.impl.UserRoleServiceImpl;

@WebServlet("/deleterole")
public class DeleteRoleControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// truy xuat vao Interface userRoleService de lay cac ham implement Service
	// userRole
	IUserRoleService userroleService = new UserRoleServiceImpl();

	public DeleteRoleControler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id"); // lay du lieu tham so tu url tren view JSP
		int roleid = Integer.parseInt(id); // chuyen kieu du lieu chuoi ve so nguyen

//		UserRoles userrole = userroleService.findOne(roleid); //lay 1 doi tuong UserRole tu RoleId bang ham fineOne
		userroleService.delete(roleid);// goi ham delete de xoa theo doi tuong theo tham so truyen vao rolId
		// view se nhan du lieu la view nao
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/userrole-list.jsp");
//		dispatcher.forward(request, response);
//		response.sendRedirect(request.getContextPath() + "/userrole");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/userrole");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
