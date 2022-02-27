package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.Dao.ICategoryDao;
import vn.iotstar.Dao.Impl.CategoryDaoImpl;
import vn.iotstar.model.Category;
import vn.iotstar.service.ICategoryService;

public class CategorySeviceImpl implements ICategoryService {
	ICategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void update(Category category) {
		Category oldCategory = categoryDao.findOne(category.getCategoryid());
		oldCategory.setCategoryname(category.getCategoryname());
		oldCategory.setStatus(category.getStatus());
		oldCategory.setImages(category.getImages());
		categoryDao.update(oldCategory);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category findOne(int id) {
		return categoryDao.findOne(id);
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

}
