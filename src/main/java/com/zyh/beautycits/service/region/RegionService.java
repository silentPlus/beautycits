package com.zyh.beautycits.service.region;

import java.util.List;
import java.util.Map;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.region.Area;
import com.zyh.beautycits.vo.region.City;
import com.zyh.beautycits.vo.region.Province;

public interface RegionService extends BaseService{
	
	public List<Province> getProvinces();
	
	public Map<String, List<City>> getcitys();
	
	public Map<String, List<Area>> getareas();
}
