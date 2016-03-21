package com.zyh.beautycits.service.hotel;

import java.util.List;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.hotel.HotelType;

public interface HotelTypeService extends BaseService{
	/**
	 * 
	 * getVehicle(查询所有宾馆类别信息)
	 */
	public ResultMsg getHotelTypes(Integer currentPage, String name, Integer userid);
	
	/**
	 * 
	 * addTicketType(新增宾馆类别)
	 * @date 2016年3月19日 下午2:09:43
	 *
	 */
	public ResultMsg addHotelType(HotelType hotelType);
	
	/**
	 * 
	 * deleteTicketType(删除宾馆类别)
	 * @date 2016年3月19日 下午2:10:06
	 */
	public ResultMsg deleteHotelType(Integer id);
	
	/**
	 * 
	 * getAllTicketType(获取全部宾馆类型信息)
	 * @date 2016年3月19日 下午3:42:26
	 */
	public List<HotelType> getAllHotelType(Integer userid);
}
