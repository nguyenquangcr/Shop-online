package vn.iotstar.service;

import java.util.List;

import vn.iotstar.model.Product;

public interface IProductService {
	
	void insert(Product product);
	
	void update (Product product);
	
	void delete(int id);
	
	Product findOne(int id);
	
	List<Product> findAll();
	
	List<Product> findAllNew();
	
	List<Product> findAllTopAmount();
	
	List<Product> findAllByCID(int id,int index);
	
	List<Product> findAllBySeller(int id,int index);
	
	List<Product> findAllPage(int indexp);
	
	int countAll();
	
	int countCID(int id);
	
	int countSell(int id);
}
