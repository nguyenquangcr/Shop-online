package vn.iotstar.model;

import java.io.Serializable;

public class Slides implements Serializable {
	private int slideid;
	private String slidename;
	private String slidelink;
	private String slideimages;
	private String slidedescription;
	private int status;
	private int slidestyle;
	
	public Slides(int slideid, String slidename, String slidelink, String slideimages, String slidedescription,
			int status, int slidestyle) {
		super();
		this.slideid = slideid;
		this.slidename = slidename;
		this.slidelink = slidelink;
		this.slideimages = slideimages;
		this.slidedescription = slidedescription;
		this.status = status;
		this.slidestyle = slidestyle;
	}

	public Slides() {
		super();
	}

	public int getSlideid() {
		return slideid;
	}

	public void setSlideid(int slideid) {
		this.slideid = slideid;
	}

	public String getSlidename() {
		return slidename;
	}

	public void setSlidename(String slidename) {
		this.slidename = slidename;
	}

	public String getSlidelink() {
		return slidelink;
	}

	public void setSlidelink(String slidelink) {
		this.slidelink = slidelink;
	}

	public String getSlideimages() {
		return slideimages;
	}

	public void setSlideimages(String slideimages) {
		this.slideimages = slideimages;
	}

	public String getSlidedescription() {
		return slidedescription;
	}

	public void setSlidedescription(String slidedescription) {
		this.slidedescription = slidedescription;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSlidestyle() {
		return slidestyle;
	}

	public void setSlidestyle(int slidestyle) {
		this.slidestyle = slidestyle;
	}
	
	
}
