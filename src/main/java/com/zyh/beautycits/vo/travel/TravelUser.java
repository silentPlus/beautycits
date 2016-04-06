package com.zyh.beautycits.vo.travel;

public class TravelUser {
	private Integer id;
	private Integer linedetailid;
	private Integer travelquoteid;
	private String name;
	private Integer age;
	private Integer ispublish;
	private String createtime;
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
	public Integer getTravelquoteid() {
		return travelquoteid;
	}
	public void setTravelquoteid(Integer travelquoteid) {
		this.travelquoteid = travelquoteid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getIspublish() {
		return ispublish;
	}
	public void setIspublish(Integer ispublish) {
		this.ispublish = ispublish;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "TravelUser [id=" + id + ", linedetailid=" + linedetailid + ", travelquoteid=" + travelquoteid
				+ ", name=" + name + ", age=" + age + ", ispublish=" + ispublish + ", createtime=" + createtime + "]";
	}
	
}
