package com.zyh.beautycits.service.hotel.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.hotel.HotelTypeService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.hotel.HotelType;

@Service("hotelTypeService")
public class HotelTypeServiceImpl extends BaseServiceImpl implements HotelTypeService{
	@Autowired
	private JdbcBaseDao<HotelType> hotelTypeDao;
	
	@Override
	public ResultMsg getHotelTypes(Integer currentPage, String name, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select * from hoteltype ht where ht.userid = ? ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and ht.name like '%").append(name).append("%' ");
		}
		
		PageInfo<HotelType> pageHotelType = new PageInfo<>();
		pageHotelType.setPageSize(ConfigConstants.PAGESIZE);
		pageHotelType.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageHotelType = hotelTypeDao.getPageModel(pageHotelType, sql, countsql, HotelType.class, userid);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageHotelType);
		return resultMsg;
	}

	@Override
	public ResultMsg addHotelType(HotelType hotelType) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT INTO hoteltype(userid, name, star, createtime) values(?,?,?,now())");
		sql.trimToSize();
		int num = hotelTypeDao.commonUpdate(sql.toString(), hotelType.getUserid(), hotelType.getName(), hotelType.getStar());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteHotelType(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from hoteltype where id = ?";
		int num = hotelTypeDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public List<HotelType> getAllHotelType(Integer userid) {
		String sql = "select * from hoteltype where userid = ?";
		List<HotelType> list = hotelTypeDao.getList(sql, HotelType.class, userid);
		return list;
	}

}
