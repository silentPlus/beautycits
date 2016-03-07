package com.zyh.beautycits.vo.line;

public class Schedule {
	private Integer id;
	private Integer linedetailid;
	private Integer day;
	private Integer hotelid;
	private Integer ticketid;
	private Integer restaurantid;
	private String remark;
	private String createtime;
	private String updatetime;
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
	public Integer getTicketid() {
		return ticketid;
	}
	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}
	public Integer getRestaurantid() {
		return restaurantid;
	}
	public void setRestaurantid(Integer restaurantid) {
		this.restaurantid = restaurantid;
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
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", linedetailid=" + linedetailid + ", day=" + day + ", hotelid=" + hotelid
				+ ", ticketid=" + ticketid + ", restaurantid=" + restaurantid + ", remark=" + remark + ", createtime="
				+ createtime + ", updatetime=" + updatetime + "]";
	}
}
