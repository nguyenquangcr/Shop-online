package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.Dao.ISellerDao;
import vn.iotstar.Dao.Impl.SellerDaoImpl;
import vn.iotstar.model.Seller;
import vn.iotstar.service.ISellerService;

public class SellerServiceImpl implements ISellerService{
	ISellerDao sellerDao = new SellerDaoImpl();

	@Override
	public void insert(Seller seller) {
		sellerDao.insert(seller);
	}

	@Override
	public void update(Seller seller) {
		Seller oldSeller = sellerDao.findOne(seller.getSellerid());
		oldSeller.setSellername(seller.getSellername());
		oldSeller.setStatus(seller.getStatus());
		oldSeller.setImages(seller.getImages());
		sellerDao.update(oldSeller);
	}

	@Override
	public void delete(int id) {
		sellerDao.delete(id);
	}

	@Override
	public Seller findOne(int id) {
		return sellerDao.findOne(id);
	}

	public List<Seller> findAll() {
		return sellerDao.findAll();
	}
}
