package com.zyh.beautycits.vo.line;

public class ScheduleTicket {
	private Integer id;
	private Integer scheduleid;
	private Integer ticketid;
	private String ticket;
	private String ticketcost;
	private String createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}
	public Integer getTicketid() {
		return ticketid;
	}
	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getTicketcost() {
		return ticketcost;
	}
	public void setTicketcost(String ticketcost) {
		this.ticketcost = ticketcost;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "ScheduleTicket [id=" + id + ", scheduleid=" + scheduleid + ", ticketid=" + ticketid + ", ticket="
				+ ticket + ", ticketcost=" + ticketcost + ", createtime=" + createtime + "]";
	}

}
