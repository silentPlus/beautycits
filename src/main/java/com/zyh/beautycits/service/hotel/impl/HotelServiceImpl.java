package com.zyh.beautycits.service.hotel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.hotel.HotelService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.hotel.Hotel;

@Service("hotelService")
public class HotelServiceImpl extends BaseServiceImpl implements HotelService{
	
	@Autowired
	private JdbcBaseDao<Hotel> hotelDao;
	
	@Override
	public ResultMsg getHotels(Integer currentPage, Integer hoteltypeid, Integer format, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select h.*, ht.name as hoteltype from hotel h left join hoteltype ht on ht.id = h.hoteltypeid where h.userid = ? ");
		if (hoteltypeid != null) {
			sql.append(" and h.hoteltypeid = ").append(hoteltypeid);
		}
		if (format != null) {
			sql.append(" and h.format = ").append(format);
		}
		
		PageInfo<Hotel> pageHotel = new PageInfo<>();
		pageHotel.setPageSize(ConfigConstants.PAGESIZE);
		pageHotel.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageHotel = hotelDao.getPageModel(pageHotel, sql, countsql, Hotel.class, userid);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageHotel);
		return resultMsg;
	}

	@Override
	public ResultMsg addHotel(Hotel hotel) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("insert into hotel(userid, provinceid, cityid, areaid, area, hoteltypeid, format, cost, remark, createtime) ");
		sql.append("values(?,?,?,?,?,?,?,?,?,now())");
		sql.trimToSize();
		int num = hotelDao.commonUpdate(sql.toString(), hotel.getUserid(), hotel.getProvinceid(), hotel.getCityid(), hotel.getAreaid(), hotel.getArea(), hotel.getHoteltypeid(), 
				hotel.getFormat(), hotel.getCost(), hotel.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteHotel(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from hotel where id = ?";
		int num = hotelDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public List<Hotel> getAllHotel(Integer userid) {
		String sql = "select * from hotel h where h.userid = ? ORDER BY h.createtime desc ";
		List<Hotel> list = hotelDao.getList(sql, Hotel.class);
		return list;
	}

}
