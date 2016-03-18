package com.zyh.beautycits.service.region.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.region.RegionService;
import com.zyh.beautycits.vo.region.Area;
import com.zyh.beautycits.vo.region.City;
import com.zyh.beautycits.vo.region.Province;

@Service("regionService")
public class RegionServiceImpl extends BaseServiceImpl implements RegionService{

	@Autowired
	private JdbcBaseDao<Province> provinceDao;
	@Autowired
	private JdbcBaseDao<City> cityDao;
	@Autowired
	private JdbcBaseDao<Area> areaDao;
	
	@Override
	public List<Province> getProvinces() {
		String sql = "select * from province";
		List<Province> list = provinceDao.getList(sql, Province.class);
		return list;
	}

	@Override
	public JSONObject getcitys() {
		String sql = "select * from city";
		JSONObject map = new JSONObject();
		List<City> listCity = cityDao.getList(sql, City.class);
		for (City city : listCity) {
			String provinceCode = city.getProvincecode();
			if (map.containsKey(provinceCode)) {
				List<City> citys = (List<City>) map.get(provinceCode);
				citys.add(city);
				map.put(provinceCode, citys);
			} else {
				List<City> citys = new ArrayList<>();
				citys.add(city);
				map.put(provinceCode, citys);
			}
		}
		return map;
	}

	@Override
	public JSONObject getareas() {
		String sql = "select * from area";
		List<Area> listArea = areaDao.getList(sql, Area.class);
		JSONObject map = new JSONObject();
		for (Area area : listArea) {
			String citycode = area.getCitycode();
			if (map.containsKey(citycode)) {
				List<Area> areas = (List<Area>) map.get(citycode);
				areas.add(area);
				map.put(citycode, areas);
			} else {
				List<Area> areas = new ArrayList<>();
				areas.add(area);
				map.put(citycode, areas);
			}
		}
		return map;
	}

}
