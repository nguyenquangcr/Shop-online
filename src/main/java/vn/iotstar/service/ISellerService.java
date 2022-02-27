package vn.iotstar.service;

import java.util.List;

import vn.iotstar.model.Seller;

public interface ISellerService {
	void insert(Seller seller);

	void update(Seller seller);

	void delete(int id);

	Seller findOne(int id);

	List<Seller> findAll();
}
