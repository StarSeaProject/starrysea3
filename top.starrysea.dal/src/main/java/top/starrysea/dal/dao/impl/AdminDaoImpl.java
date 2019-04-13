package top.starrysea.dal.dao.impl;

import static top.starrysea.common.Common.isNotNull;
import static top.starrysea.common.Common.md5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.dao.IAdminDao;
import top.starrysea.dal.entity.Admin;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;

@Repository("adminDao")
public class AdminDaoImpl implements IAdminDao {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	// 管理员登�?
	public DaoResult loginDao(Admin admin) {
		kumaSqlDao.selectMode();
		ListSqlResult<String> list = kumaSqlDao.select("1").from(Admin.class)
				.where("admin_useraccount", WhereType.EQUALS, admin.getAdminUseraccount()).endForList(String.class);
		if (list.getResult().isEmpty()) {
			return new DaoResult(false, "管理员账号不存在");
		}
		ListSqlResult<Admin> theResult = kumaSqlDao.select("admin_id").from(Admin.class)
				.where("admin_useraccount", WhereType.EQUALS, admin.getAdminUseraccount())
				.where("admin_password", WhereType.EQUALS, md5(admin.getAdminPassword()))
				.endForList((rs, row) -> new Admin.Builder().adminId(rs.getInt("admin_id")).build());
		if (isNotNull(theResult.getResult())) {
			Admin result = theResult.getResult().get(0);
			return new DaoResult(true, result);
		} else {
			return new DaoResult(false, "密码错误");
		}
	}

}
