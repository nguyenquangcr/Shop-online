package vn.iotstar.service.impl;

import java.sql.Date;
import java.util.List;

import vn.iotstar.Dao.IProductDao;
import vn.iotstar.Dao.Impl.ProductDaoImpl;
import vn.iotstar.model.Product;
import vn.iotstar.service.IProductService;

public class ProductServiceImpl implements IProductService {
	IProductDao productDao = new ProductDaoImpl();

	@Override
	public void insert(Product product) {
		product.setCreateDate(new Date(System.currentTimeMillis()));
		productDao.insert(product);
	}

	@Override
	public void update(Product product) {
		Product oldProduct = productDao.findOne(product.getProductid());

		oldProduct.setProductcode(product.getProductcode());
		oldProduct.setProductname(product.getProductname());
		oldProduct.setDescription(product.getDescription());
		oldProduct.setImages(product.getImages());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setStock(product.getStock());
		oldProduct.setStatus(product.getStatus());
		oldProduct.setCategory(product.getCategory());
		oldProduct.setSeller(product.getSeller());

		productDao.update(product);
	}

	@Override
	public void delete(int id) {
		productDao.delete(id);
	}

	@Override
	public Product findOne(int id) {
		return productDao.findOne(id);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public List<Product> findAllNew() {
		return productDao.findAllNew();
	}

	@Override
	public List<Product> findAllTopAmount() {
		return productDao.findAllTopAmount();
	}

	@Override
	public List<Product> findAllByCID(int id, int index) {
		return productDao.findAllByCID(id, index);
	}

	@Override
	public List<Product> findAllBySeller(int id, int index) {
		return productDao.findAllBySeller(id, index);
	}

	@Override
	public List<Product> findAllPage(int indexp) {
		return productDao.findAllPage(indexp);
	}

	@Override
	public int countAll() {
		return productDao.countAll();
	}

	@Override
	public int countCID(int id) {
		return productDao.countCID(id);
	}

	@Override
	public int countSell(int id) {
		return productDao.countSell(id);
	}

}
