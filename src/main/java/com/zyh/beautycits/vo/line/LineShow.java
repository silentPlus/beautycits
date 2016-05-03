package com.zyh.beautycits.vo.line;

public class LineShow {
	private Integer id;
	private String linename;
	private String linetype;
	private Integer govehicleid;
	private Integer backvehicleid;
	private String govehicle;
	private String backvehicle;
	private String publishtime;
	private Integer day;
	private Integer number; // 设置最大人数
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLinename() {
		return linename;
	}
	public void setLinename(String linename) {
		this.linename = linename;
	}
	public String getLinetype() {
		return linetype;
	}
	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}
	public String getGovehicle() {
		return govehicle;
	}
	public void setGovehicle(String govehicle) {
		this.govehicle = govehicle;
	}
	public String getBackvehicle() {
		return backvehicle;
	}
	public void setBackvehicle(String backvehicle) {
		this.backvehicle = backvehicle;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public Integer getGovehicleid() {
		return govehicleid;
	}
	public void setGovehicleid(Integer govehicleid) {
		this.govehicleid = govehicleid;
		if (govehicleid != null) {
			switch (govehicleid) {
			case 1 : this.govehicle = "汽车";break;
			case 2 : this.govehicle = "火车";break;
			case 3 : this.govehicle = "飞机";break;
			case 4 : this.govehicle = "轮船";break;
			default : this.govehicle = "无";break;
			}
		} else {
			this.govehicle = "无";
		}
	}
	public Integer getBackvehicleid() {
		return backvehicleid;
	}
	public void setBackvehicleid(Integer backvehicleid) {
		this.backvehicleid = backvehicleid;
		if (backvehicleid != null) {
			switch (backvehicleid) {
			case 1 : this.backvehicle = "汽车";break;
			case 2 : this.backvehicle = "火车";break;
			case 3 : this.backvehicle = "飞机";break;
			case 4 : this.backvehicle = "轮船";break;
			default : this.backvehicle = "无";break;
			}
		} else {
			this.backvehicle = "无";
		}
	}
	@Override
	public String toString() {
		return "LineShow [id=" + id + ", linename=" + linename + ", linetype=" + linetype + ", govehicleid="
				+ govehicleid + ", backvehicleid=" + backvehicleid + ", govehicle=" + govehicle + ", backvehicle="
				+ backvehicle + ", publishtime=" + publishtime + ", day=" + day + "]";
	}
}
