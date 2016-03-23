package com.zyh.beautycits.vo.line;

public class Line {
	private Integer id;
	private Integer linetypeid;
	private String linetype;
	private String name;
	private Integer day;
	private Integer ispublish;
	private String iispublish;
	private Integer num;   // 标记有多少旅行社规划了该线路
	private String publish;   // 当num>0时，可发布
	private String remark;
	private String createtime;
	private String updatetime;
	public String getIispublish() {
		return iispublish;
	}
	public void setIispublish(String iispublish) {
		this.iispublish = iispublish;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
		if (num > 0) {
			this.publish = "可发布";
		} else {
			this.publish = "不可发布";
		}
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getLinetype() {
		return linetype;
	}
	public void setLinetype(String linetype) {
		this.linetype = linetype;
	}
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
		if (ispublish == 0) {
			this.iispublish = "未发布";
		} else {
			this.iispublish = "已发布";
		}
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
		return "Line [id=" + id + ", linetypeid=" + linetypeid + ", linetype=" + linetype + ", name=" + name + ", day="
				+ day + ", ispublish=" + ispublish + ", iispublish=" + iispublish + ", num=" + num + ", publish="
				+ publish + ", remark=" + remark + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
