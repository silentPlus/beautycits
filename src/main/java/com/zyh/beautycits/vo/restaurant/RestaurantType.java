package com.zyh.beautycits.vo.restaurant;

public class RestaurantType {
	private Integer id;
	private Integer userid;
	private String name;
	private Integer star;
	private String createtiem;
	private String updatetime;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getCreatetiem() {
		return createtiem;
	}
	public void setCreatetiem(String createtiem) {
		this.createtiem = createtiem;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	@Override
	public String toString() {
		return "RestaurantType [id=" + id + ", userid=" + userid + ", name=" + name + ", star=" + star + ", createtiem="
				+ createtiem + ", updatetime=" + updatetime + "]";
	}
}
