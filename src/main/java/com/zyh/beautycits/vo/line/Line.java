package com.zyh.beautycits.vo.line;

public class Line {
	private Integer id;
	private Integer linetypeid;
	private String name;
	private Integer day;
	private Integer ispublish;
	private String remark;
	private String createtime;
	private String updatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLinetypeid() {
		return linetypeid;
	}
	public void setLinetypeid(Integer linetypeid) {
		this.linetypeid = linetypeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getIspublish() {
		return ispublish;
	}
	public void setIspublish(Integer ispublish) {
		this.ispublish = ispublish;
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
		return "Line [id=" + id + ", linetypeid=" + linetypeid + ", name=" + name + ", day=" + day + ", ispublish="
				+ ispublish + ", remark=" + remark + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
