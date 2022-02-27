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


@WebServlet("/addrole")
public class AddRoleControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //truy xuat vao Interface userRoleService de lay cac ham implement Service userRole 
	IUserRoleService userroleService = new UserRoleServiceImpl();
     
    public AddRoleControler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/addrole.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("request: " + request.getParameter("rolename"));
		
		//xu ly tieng viet khi them vao
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		UserRoles userrole = new UserRoles();
		String rolename = request.getParameter("rolename");	//lay du lieu tu viewJSP voi thuoc tinh name
		
		userrole.setRolename(rolename);		// nap du lieu vao truong role name trong model
		userroleService.insert(userrole);  //them du lieu vao database bang ham insert
		
		response.sendRedirect(request.getContextPath() + "/userrole");		//tra ve servlet controller
	}

}
