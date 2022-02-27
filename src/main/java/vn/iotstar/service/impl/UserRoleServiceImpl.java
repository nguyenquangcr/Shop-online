package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.Dao.IUserRoleDao;
import vn.iotstar.Dao.Impl.UserRoleDaoImpl;
import vn.iotstar.model.UserRoles;
import vn.iotstar.service.IUserRoleService;

public class UserRoleServiceImpl implements IUserRoleService {
	//truy xuat vao Interface Dao de lay cac ham implement userRoleDao 
	IUserRoleDao userRoleDao = new UserRoleDaoImpl();
	
	
	@Override
	public List<UserRoles> findAll() {
		return userRoleDao.findAll();
	}

	@Override
	public UserRoles findOne(int id) {
		return userRoleDao.findOne(id);
	}

	@Override
	public void insert(UserRoles role) {
		userRoleDao.insert(role);
		
	}

	@Override
	public void update(UserRoles role) {
		UserRoles oldRole = userRoleDao.findOne(role.getRoleid());
		oldRole.setRolename(role.getRolename());
		userRoleDao.update(oldRole);
	}

	@Override
	public void delete(int id) {
		userRoleDao.delete(id);
	}

}
