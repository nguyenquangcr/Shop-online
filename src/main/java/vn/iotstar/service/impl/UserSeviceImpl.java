package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.Dao.IUserDao;
import vn.iotstar.Dao.Impl.UserDaoImpl;
import vn.iotstar.model.Users;
import vn.iotstar.service.IUserService;

public class UserSeviceImpl implements IUserService {
	IUserDao userDao = new UserDaoImpl();

	@Override
	public List<Users> findAll() {
		return userDao.findAll();
	}

	public Users findOne(String username) {
		return userDao.findOne(username);
	}

	@Override
	public void insert(Users user) {
		userDao.insert(user);
	}

	@Override
	public void update(Users user) {
		Users oldUser = userDao.findOne(user.getUserid());
		oldUser.setEmail(user.getEmail());
		oldUser.setFullname(user.getFullname());
		oldUser.setUsername(user.getUsername());
		oldUser.setPassword(user.getPassword());
		oldUser.setPhone(user.getPhone());
		oldUser.setRoles(user.getRoles());
		oldUser.setStatus(user.getStatus());
		oldUser.setImages(user.getImages());
		userDao.update(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String code) {
		if (userDao.checkExisEmail(email)) {
			return false;
		}
		if (userDao.checkExisUsername(username)) {
			return false;
		}
		userDao.insertregister(new Users(username, email, fullname, password, 0, 3, code));
		return true;
	}

	@Override
	public void updatestatus(Users user) {
		userDao.updatestatus(user);
	}

	@Override
	public Users login(String username, String password) {
		Users user = this.findOne(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean checkExisEmail(String email) {
		return userDao.checkExisEmail(email);
	}

	@Override
	public boolean checkExisUsername(String username) {
		return userDao.checkExisUsername(username);
	}

	@Override
	public Users findOne(int id) {
		return userDao.findOne(id);
	}

}
