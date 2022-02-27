package vn.iotstar.Dao;

import java.util.List;

import vn.iotstar.model.CartItems;

public interface ICartItemDao {
	void insert(CartItems cartItem);
	
	void update(CartItems cartItem);
	
	void delete(String id);
	
	CartItems findOne(String id);
	
	List<CartItems> findAll();
	
	List<CartItems> findAllByUserId(int id);
	
	List<CartItems> findAllByUser(int id);
	
	List<CartItems> findAllByUserPaging(int id, int index);
	
	int countByUser(int id);
	
	List<CartItems> findAllBySeller(int id);
}
