package com.zyh.beautycits.vo.quote;

public class InnerQuote {
	private Integer id;
	private Integer lineid;
	private Integer linedetailid;
	private String primecost;
	private String offercost;
	private String grossprofit;
	private String remark;
	private String createtime;
	private String updatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLineid() {
		return lineid;
	}
	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}
	public Integer getLinedetailid() {
		return linedetailid;
	}
	public void setLinedetailid(Integer linedetailid) {
		this.linedetailid = linedetailid;
	}
	public String getPrimecost() {
		return primecost;
	}
	public void setPrimecost(String primecost) {
		this.primecost = primecost;
	}
	public String getOffercost() {
		return offercost;
	}
	public void setOffercost(String offercost) {
		this.offercost = offercost;
	}
	public String getGrossprofit() {
		return grossprofit;
	}
	public void setGrossprofit(String grossprofit) {
		this.grossprofit = grossprofit;
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
		return "InnerQuote [id=" + id + ", lineid=" + lineid + ", linedetailid=" + linedetailid + ", primecost="
				+ primecost + ", offercost=" + offercost + ", grossprofit=" + grossprofit + ", remark=" + remark
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
