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

@WebServlet("/userrole")
public class UserRoleControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// truy xuat vao Interface userRoleService de lay cac ham implement Service
	// userRole
	IUserRoleService userroleService = new UserRoleServiceImpl();

	public UserRoleControler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<UserRoles> roleList = userroleService.findAll(); // lay toan bo du lieu bang userRole dua vao list
		request.setAttribute("roleList", roleList); // day du lieu tu list len view JSP
		request.setAttribute("tag", "rol");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/userrole-list.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
