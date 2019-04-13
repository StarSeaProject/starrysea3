package top.starrysea.dal.dao;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.Online;

public interface IOnlineDao {

	DaoResult getAllOnlineDao();

	DaoResult saveOnlineDao(Online online);
}
