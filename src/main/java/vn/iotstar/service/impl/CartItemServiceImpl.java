package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.Dao.ICartItemDao;
import vn.iotstar.Dao.Impl.CartItemDaoImp;
import vn.iotstar.model.CartItems;
import vn.iotstar.service.ICartItemService;

public class CartItemServiceImpl implements ICartItemService{
	ICartItemDao cartItemDao = new CartItemDaoImp();
	
	@Override
	public void insert(CartItems cartItem) {
		cartItemDao.insert(cartItem);
	}

	@Override
	public void update(CartItems cartItem) {
		CartItems oldCartItem = cartItemDao.findOne(cartItem.getCartitemid());
		oldCartItem.setCart(cartItem.getCart());
		oldCartItem.setProduct(cartItem.getProduct());
		oldCartItem.setQuantity(cartItem.getQuantity());
		oldCartItem.setUnitPrice(cartItem.getUnitPrice());
		
		cartItemDao.update(oldCartItem);
	}

	@Override
	public void delete(String id) {
		cartItemDao.delete(id);
		
	}

	@Override
	public CartItems findOne(String id) {
		return cartItemDao.findOne(id);
	}

	@Override
	public List<CartItems> findAll() {
		return cartItemDao.findAll();
	}

	@Override
	public List<CartItems> findAllByUserId(int id) {
		return cartItemDao.findAllByUserId(id);
	}

	@Override
	public List<CartItems> findAllByUser(int id) {
		return cartItemDao.findAllByUser(id);
	}

	@Override
	public List<CartItems> findAllByUserPaging(int id, int index) {
		return cartItemDao.findAllByUserPaging(id, index);
	}

	@Override
	public int countByUser(int id) {
		return cartItemDao.countByUser(id);
	}

	@Override
	public List<CartItems> findAllBySeller(int id) {
		return cartItemDao.findAllBySeller(id);
	}

}
