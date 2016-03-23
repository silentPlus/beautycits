package com.zyh.beautycits.service.line.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.LineTypeService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.LineType;

@Service("lineTypeService")
public class LineTypeServiceImpl extends BaseServiceImpl implements LineTypeService {

	@Autowired
	private JdbcBaseDao<LineType> lineTypeDao;
	
	@Override
	public ResultMsg getLineTypes(Integer currentPage) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select * from linetype ");
		
		PageInfo<LineType> pageLineType = new PageInfo<>();
		pageLineType.setPageSize(ConfigConstants.PAGESIZE);
		pageLineType.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageLineType = lineTypeDao.getPageModel(pageLineType, sql, countsql, LineType.class);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageLineType);
		return resultMsg;
	}

	@Override
	public ResultMsg addLineType(LineType lineType) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT into linetype(name, remark, createtime) values(?, ?, now())");
		sql.trimToSize();
		int num = lineTypeDao.commonUpdate(sql.toString(), lineType.getName(), lineType.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteLineType(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from linetype where id = ?";
		int num = lineTypeDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public List<LineType> getAllLineType() {
		String sql = "select * from linetype";
		List<LineType> list = lineTypeDao.getList(sql, LineType.class);
		return list;
	}

}
