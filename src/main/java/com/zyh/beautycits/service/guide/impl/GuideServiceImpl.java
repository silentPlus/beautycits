package com.zyh.beautycits.service.guide.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.guide.GuideService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.guide.Guide;

@Service("guideService")
public class GuideServiceImpl extends BaseServiceImpl implements GuideService{

	@Autowired
	private JdbcBaseDao<Guide> guideDao;
	
	@Override
	public ResultMsg getGuide(Integer currentPage, String guidename, Integer isused, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select * from guide g where g.userid = ? ");
		if (StringUtils.isNotBlank(guidename)) {
			sql.append(" and g.guidename like '%").append(guidename).append("%' ");
		}
		if (isused != null) {
			sql.append(" and g.isused = ").append(isused);
		}
		
		PageInfo<Guide> pageGuide = new PageInfo<>();
		pageGuide.setPageSize(ConfigConstants.PAGESIZE);
		pageGuide.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageGuide = guideDao.getPageModel(pageGuide, sql, countsql, Guide.class, userid);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageGuide);
		return resultMsg;
	}

	@Override
	public ResultMsg saveGuide(Guide guide) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT INTO guide(userid, guidename, telephone, cost, remark, createtime) values(?,?,?,?,?,now())");
		sql.trimToSize();
		int num = guideDao.commonUpdate(sql.toString(), guide.getUserid(), guide.getGuidename(), guide.getTelephone(), guide.getCost(), guide.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteGuide(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from guide where id = ?";
		int num = guideDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public List<Guide> getAllGuide(Integer userid) {
		String sql = "select * from guide g where g.isused = 1 and g.userid = ? ORDER BY g.createtime desc ";
		List<Guide> list = guideDao.getList(sql, Guide.class, userid);
		return list;
	}

	@Override
	public ResultMsg updateGuide(Integer id, Integer isused) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update guide g set g.isused = ?, g.updatetime = now() where g.id = ?";
		int num = guideDao.commonUpdate(sql, isused, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
