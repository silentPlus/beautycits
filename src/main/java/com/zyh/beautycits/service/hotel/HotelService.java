package com.zyh.beautycits.service.hotel;


import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.hotel.Hotel;

public interface HotelService extends BaseService{
	
	/**
	 * 
	 * getVehicle(查询所有宾馆信息)
	 */
	public ResultMsg getHotels(Integer currentPage, Integer hoteltypeid, Integer format, Integer userid);
	
	/**
	 * 
	 * addTicketType(新增宾馆信息)
	 * @date 2016年3月19日 下午2:09:43
	 *
	 */
	public ResultMsg addHotel(Hotel hotel);
	
	/**
	 * 
	 * deleteTicketType(删除宾馆信息)
	 * @date 2016年3月19日 下午2:10:06
	 */
	public ResultMsg deleteHotel(Integer id);
	
}
