package vn.iotstar.Dao;

import java.util.List;

import vn.iotstar.model.Category;

public interface ICategoryDao {

	void insert(Category category);
	
	void update (Category category);
	
	void delete(int id);
	
	Category findOne(int id);
	
	List<Category> findAll();
}
