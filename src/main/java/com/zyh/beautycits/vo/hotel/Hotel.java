package com.zyh.beautycits.vo.hotel;

public class Hotel {
	private Integer id;
	private Integer userid;
	private Integer provinceid;
	private Integer cityid;
	private Integer areaid;
	private String area;
	private Integer hoteltypeid;
	private String hoteltype;
	private Integer format;   // 房间类型，0：标准间、1：大床房、2：单人间、3：三人间、4：商务套房、5：豪华套房、6：总统套房、7：海景房
	private String cost;
	private String remark;
	private String createtime;
	private String updatetime;
	private String formatname;
	public String getFormatname() {
		return formatname;
	}
	public void setFormatname(String formatname) {
		this.formatname = formatname;
	}
	public String getHoteltype() {
		return hoteltype;
	}
	public void setHoteltype(String hoteltype) {
		this.hoteltype = hoteltype;
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
	public Integer getHoteltypeid() {
		return hoteltypeid;
	}
	public void setHoteltypeid(Integer hoteltypeid) {
		this.hoteltypeid = hoteltypeid;
	}
	public Integer getFormat() {
		return format;
	}
	public void setFormat(Integer format) {
		this.format = format;
		switch(format){
		case 0 : this.formatname = "标准间";break;
		case 1 : this.formatname = "大床房";break;
		case 2 : this.formatname = "单人间";break;
		case 3 : this.formatname = "三人间";break;
		case 4 : this.formatname = "商务套房";break;
		case 5 : this.formatname = "豪华套房";break;
		case 6 : this.formatname = "总统套房";break;
		case 7 : this.formatname = "海景房";break;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", userid=" + userid + ", provinceid=" + provinceid + ", cityid=" + cityid
				+ ", areaid=" + areaid + ", area=" + area + ", hoteltypeid=" + hoteltypeid + ", hoteltype=" + hoteltype
				+ ", format=" + format + ", cost=" + cost + ", remark=" + remark + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", formatname=" + formatname + "]";
	}
}
