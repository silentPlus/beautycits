package com.zyh.beautycits.vo.line;

public class Schedule {
	private Integer id;
	private Integer linedetailid;
	private Integer day;
	private Integer hotelid;
	private Integer busid;
	private Integer morestaurantid;
	private Integer lurestaurantid;
	private Integer direstaurantid;
	private String hotelcost;
	private String buscost;
	private String mocost;
	private String lucost;
	private String dicost;
	private String remark;
	private String createtime;
	private String updatetime;
	// 显示用
	private String hotel;
	private String bus;
	private String morestaurant;
	private String lurestaurant;
	private String direstaurant;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLinedetailid() {
		return linedetailid;
	}
	public void setLinedetailid(Integer linedetailid) {
		this.linedetailid = linedetailid;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getHotelid() {
		return hotelid;
	}
	public void setHotelid(Integer hotelid) {
		this.hotelid = hotelid;
	}
	public Integer getBusid() {
		return busid;
	}
	public void setBusid(Integer busid) {
		this.busid = busid;
	}
	public Integer getMorestaurantid() {
		return morestaurantid;
	}
	public void setMorestaurantid(Integer morestaurantid) {
		this.morestaurantid = morestaurantid;
	}
	public Integer getLurestaurantid() {
		return lurestaurantid;
	}
	public void setLurestaurantid(Integer lurestaurantid) {
		this.lurestaurantid = lurestaurantid;
	}
	public Integer getDirestaurantid() {
		return direstaurantid;
	}
	public void setDirestaurantid(Integer direstaurantid) {
		this.direstaurantid = direstaurantid;
	}
	public String getHotelcost() {
		return hotelcost;
	}
	public void setHotelcost(String hotelcost) {
		this.hotelcost = hotelcost;
	}
	public String getBuscost() {
		return buscost;
	}
	public void setBuscost(String buscost) {
		this.buscost = buscost;
	}
	public String getMocost() {
		return mocost;
	}
	public void setMocost(String mocost) {
		this.mocost = mocost;
	}
	public String getLucost() {
		return lucost;
	}
	public void setLucost(String lucost) {
		this.lucost = lucost;
	}
	public String getDicost() {
		return dicost;
	}
	public void setDicost(String dicost) {
		this.dicost = dicost;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public String getBus() {
		return bus;
	}
	public void setBus(String bus) {
		this.bus = bus;
	}
	public String getMorestaurant() {
		return morestaurant;
	}
	public void setMorestaurant(String morestaurant) {
		this.morestaurant = morestaurant;
	}
	public String getLurestaurant() {
		return lurestaurant;
	}
	public void setLurestaurant(String lurestaurant) {
		this.lurestaurant = lurestaurant;
	}
	public String getDirestaurant() {
		return direstaurant;
	}
	public void setDirestaurant(String direstaurant) {
		this.direstaurant = direstaurant;
	}
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", linedetailid=" + linedetailid + ", day=" + day + ", hotelid=" + hotelid
				+ ", busid=" + busid + ", morestaurantid=" + morestaurantid + ", lurestaurantid=" + lurestaurantid
				+ ", direstaurantid=" + direstaurantid + ", hotelcost=" + hotelcost + ", buscost=" + buscost
				+ ", mocost=" + mocost + ", lucost=" + lucost + ", dicost=" + dicost + ", remark=" + remark
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + ", hotel=" + hotel + ", bus=" + bus
				+ ", morestaurant=" + morestaurant + ", lurestaurant=" + lurestaurant + ", direstaurant=" + direstaurant
				+ "]";
	}
}
