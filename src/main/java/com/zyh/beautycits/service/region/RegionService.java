package com.zyh.beautycits.service.region;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.region.Province;

public interface RegionService extends BaseService{
	
	public List<Province> getProvinces();
	
	public JSONObject getcitys();
	
	public JSONObject getareas();
}
