package com.zyh.beautycits.vo.hotel;

public class Hotel {
	private Integer id;
	private Integer userid;
	private Integer provinceid;
	private Integer cityid;
	private Integer areaid;
	private Integer hoteltypeid;
	private Integer format;
	private String cost;
	private String remark;
	private String createtime;
	private String updatetime;
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
		return "Hotel [id=" + id + ", userid=" + userid + ", provinceid=" + provinceid + ", cityid=" + cityid
				+ ", areaid=" + areaid + ", hoteltypeid=" + hoteltypeid + ", format=" + format + ", cost=" + cost
				+ ", remark=" + remark + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
