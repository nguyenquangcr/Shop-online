package vn.iotstar.service.impl;

import java.util.List;
import vn.iotstar.Dao.ISlidesDao;
import vn.iotstar.Dao.Impl.SlidesDaoImpl;
import vn.iotstar.model.Slides;
import vn.iotstar.service.ISlidesService;

public class SlidesSeviceImpl implements ISlidesService {
	ISlidesDao slicesDao = new SlidesDaoImpl();

	@Override
	public void insert(Slides slides) {
		slicesDao.insert(slides);
	}

	@Override
	public void update(Slides slides) {
		Slides oldSlides = slicesDao.findOne(slides.getSlideid());

		oldSlides.setSlidename(slides.getSlidename());
		oldSlides.setSlidelink(slides.getSlidelink());
		oldSlides.setSlidedescription(slides.getSlidedescription());
		oldSlides.setSlideimages(slides.getSlideimages());
		oldSlides.setStatus(slides.getStatus());
		oldSlides.setSlidestyle(slides.getSlidestyle());
		slicesDao.update(oldSlides);
	}

	@Override
	public void delete(int id) {
		slicesDao.delete(id);
	}

	@Override
	public Slides findOne(int id) {
		return slicesDao.findOne(id);
	}

	public List<Slides> findAll() {
		return slicesDao.findAll();
	}

}
