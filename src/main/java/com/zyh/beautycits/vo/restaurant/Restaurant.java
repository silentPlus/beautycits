package com.zyh.beautycits.vo.restaurant;

public class Restaurant {
	private Integer id;
	private Integer userid;
	private Integer provinceid;
	private Integer cityid;
	private Integer areaid;
	private String area;
	private Integer restauranttypeid;
	private String restaurant;
	private String formatname;
	private Integer format; // 规格、0：小桌（1~4）、1：中桌（5~8）、2：大桌（>8）
	private String cost;
	private String remark;
	private String createtime;
	private String updatetime;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getRestauranttypeid() {
		return restauranttypeid;
	}
	public void setRestauranttypeid(Integer restauranttypeid) {
		this.restauranttypeid = restauranttypeid;
	}
	public String getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	public String getFormatname() {
		return formatname;
	}
	public void setFormatname(String formatname) {
		this.formatname = formatname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public Integer getFormat() {
		return format;
	}
	public void setFormat(Integer format) {
		this.format = format;
		switch(format){
		case 0 : this.formatname = "小桌（1~4人）";break;
		case 1 : this.formatname = "中桌（5~8人）";break;
		case 2 : this.formatname = "大桌（>8人）";break;
		default : this.formatname = "";break;
		}
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
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
		return "Restaurant [id=" + id + ", userid=" + userid + ", provinceid=" + provinceid + ", cityid=" + cityid
				+ ", areaid=" + areaid + ", area=" + area + ", restauranttypeid=" + restauranttypeid + ", restaurant="
				+ restaurant + ", formatname=" + formatname + ", format=" + format + ", cost=" + cost + ", remark="
				+ remark + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
