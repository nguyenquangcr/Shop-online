package vn.iotstar.Dao;

import java.util.List;

import vn.iotstar.model.Slides;

public interface ISlidesDao {

	void insert(Slides slides);
	
	void update (Slides slides);
	
	void delete(int id);
	
	Slides findOne(int id);
	
	List<Slides> findAll();
}
