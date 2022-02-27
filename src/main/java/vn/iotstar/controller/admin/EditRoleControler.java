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


@WebServlet("/editrole")
public class EditRoleControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //truy xuat vao Interface userRoleService de lay cac ham implement Service userRole 
	IUserRoleService userroleService = new UserRoleServiceImpl();
     
    public EditRoleControler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");			//lay du lieu tham so tu url tren view JSP
		int roleid = Integer.parseInt(id);		//chuyen kieu du lieu chuoi ve so nguyen
		
		UserRoles userrole = userroleService.findOne(roleid); //lay 1 doi tuong UserRole tu RoleId bang ham fineOne
		
		request.setAttribute("userrole", userrole);
		//view se nhan du lieu la view nao
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/editrole.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request: " + request.getParameter("rolename"));
		
		//xu ly tieng viet khi them vao
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		UserRoles userrole = new UserRoles();
		String id = request.getParameter("roleid");	//lay du lieu tu viewJSP voi thuoc tinh name
		int roleid = Integer.parseInt(id);
		
		String rolename = request.getParameter("rolename");	//lay du lieu tu viewJSP voi thuoc tinh name
		
		
		userrole.setRoleid(roleid);   // nap du lieu vao truong roleid trong model
		userrole.setRolename(rolename);		// nap du lieu vao truong role name trong model
		userroleService.update(userrole);  //them du lieu vao database bang ham insert
		
		response.sendRedirect(request.getContextPath() + "/userrole");		//tra ve servlet controller
	}

}
