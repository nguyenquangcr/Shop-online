package vn.iotstar.service;

import java.util.List;

import vn.iotstar.model.Category;

public interface ICategoryService {
	void insert(Category category);
	
	void update (Category category);
	
	void delete(int id);
	
	Category findOne(int id);
	
	List<Category> findAll();
}
