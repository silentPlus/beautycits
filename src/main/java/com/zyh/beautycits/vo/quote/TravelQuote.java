package com.zyh.beautycits.vo.quote;

public class TravelQuote {
	private Integer id;
	private Integer userid;
	private Integer lineid;
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
	public Integer getLineid() {
		return lineid;
	}
	public void setLineid(Integer lineid) {
		this.lineid = lineid;
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
		return "TravelQuote [id=" + id + ", userid=" + userid + ", lineid=" + lineid + ", cost=" + cost + ", remark="
				+ remark + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
