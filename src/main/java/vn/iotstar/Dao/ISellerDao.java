package vn.iotstar.Dao;

import java.util.List;

import vn.iotstar.model.Seller;

public interface ISellerDao {

	void insert(Seller seller);

	void update(Seller seller);

	void delete(int id);

	Seller findOne(int id);

	List<Seller> findAll();
}
