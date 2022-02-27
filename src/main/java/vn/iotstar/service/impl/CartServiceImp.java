package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.Dao.ICartDao;
import vn.iotstar.Dao.Impl.CartDaoImp;
import vn.iotstar.model.Cart;
import vn.iotstar.service.ICartService;

public class CartServiceImp implements ICartService {
	ICartDao cartDao = new CartDaoImp();

	@Override
	public void insert(Cart cart) {
		cartDao.insert(cart);
	}

	@Override
	public void update(Cart cart) {
		Cart oldCart = cartDao.findOne(cart.getCartid());
		oldCart.setBuyDate(cart.getBuyDate());
		oldCart.setBuyer(cart.getBuyer());
		cartDao.update(oldCart);
	}

	@Override
	public void delete(String id) {
		cartDao.delete(id);
	}

	@Override
	public void updateStatus(String id, int st) {
		cartDao.updateStatus(id, st);
	}

	@Override
	public Cart findOne(String id) {
		return cartDao.findOne(id);
	}

	@Override
	public List<Cart> findAll() {
		return cartDao.findAll();
	}

	@Override
	public List<Cart> findAllByUser(int id) {
		return cartDao.findAllByUser(id);
	}

	@Override
	public int countByUser(int id) {
		return cartDao.countByUser(id);
	}

	@Override
	public int countByStatus(int id, int status) {
		return cartDao.countByStatus(id, status);
	}

}
