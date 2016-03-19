package com.zyh.beautycits.vo.ticket;

public class Ticket {
	private Integer id;
	private Integer provinceid;
	private Integer cityid;
	private Integer areaid;
	private String area;
	private String name;
	private Integer star;
	private Integer tickettypeid;
	private String tickettype;
	private String cost;
	private String description;
	private String remark;
	private String createtime;
	private String updatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public Integer getTickettypeid() {
		return tickettypeid;
	}
	public void setTickettypeid(Integer tickettypeid) {
		this.tickettypeid = tickettypeid;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getTickettype() {
		return tickettype;
	}
	public void setTickettype(String tickettype) {
		this.tickettype = tickettype;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", provinceid=" + provinceid + ", cityid=" + cityid + ", areaid=" + areaid
				+ ", area=" + area + ", name=" + name + ", star=" + star + ", tickettypeid=" + tickettypeid
				+ ", tickettype=" + tickettype + ", cost=" + cost + ", description=" + description + ", remark="
				+ remark + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
	
}
