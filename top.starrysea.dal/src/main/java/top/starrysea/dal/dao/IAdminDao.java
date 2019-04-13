package top.starrysea.dal.dao;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.Admin;

public interface IAdminDao {
	DaoResult loginDao(Admin admin);
}
