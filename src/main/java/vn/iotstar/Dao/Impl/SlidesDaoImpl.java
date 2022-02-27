package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.Dao.DBConnection;
import vn.iotstar.Dao.ISlidesDao;
import vn.iotstar.model.Slides;

public class SlidesDaoImpl implements ISlidesDao {

	public Connection conn = null;
	public PreparedStatement ps = null; // nem cau query vao thuc ti du lieu
	public ResultSet rs = null;

	@Override
	public List<Slides> findAll() {
		List<Slides> slidesList = new ArrayList<Slides>();
		String sql = "select * From Slides";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Slides slides = new Slides();
				slides.setSlideid(rs.getInt("slideid"));
				slides.setSlidename(rs.getString("slidename"));
				slides.setSlidelink(rs.getString("slidelink"));
				slides.setSlidedescription(rs.getString("slidedescription"));
				slides.setSlideimages(rs.getString("slideimages"));
				slides.setStatus(rs.getInt("status"));
				slides.setSlidestyle(rs.getInt("slidestyle"));
				slidesList.add(slides);
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return slidesList;
	}

	@Override
	public Slides findOne(int id) {
		String sql = "Select * From Slides where slideid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Slides slides = new Slides();
				slides.setSlideid(rs.getInt("slideid"));
				slides.setSlidename(rs.getString("slidename"));
				slides.setSlidelink(rs.getString("slidelink"));
				slides.setSlidedescription(rs.getString("slidedescription"));
				slides.setSlideimages(rs.getString("slideimages"));
				slides.setStatus(rs.getInt("status"));
				slides.setSlidestyle(rs.getInt("slidestyle"));

				return slides;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Slides slides) {
		String sql = "Insert INTO Slides(slidename,slideimages,slidelink,slidedescription,status,slidestyle) Values (?,?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, slides.getSlidename());
			ps.setString(2, slides.getSlideimages());
			ps.setString(3, slides.getSlidelink());
			ps.setString(4, slides.getSlidedescription());
			ps.setInt(5, slides.getStatus());
			ps.setInt(6, slides.getSlidestyle());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Slides slides) {
		String sql = "UPDATE Slides SET slidename=?, slideimages=?, slidelink=?, slidedescription=?, status=?, slidestyle=? Where slideid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, slides.getSlidename());
			ps.setString(2, slides.getSlideimages());
			ps.setString(3, slides.getSlidelink());
			ps.setString(4, slides.getSlidedescription());
			ps.setInt(5, slides.getStatus());
			ps.setInt(6, slides.getSlidestyle());
			ps.setInt(7, slides.getSlideid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Slides Where slideid=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
