package top.starrysea.dal.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.dao.IOnlineDao;
import top.starrysea.dal.entity.Online;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;

@Repository("onlineDao")
public class OnlineDaoImpl implements IOnlineDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public DaoResult saveOnlineDao(Online online) {
		kumaSqlDao.insertMode();
		try {
			kumaSqlDao.insert("online_id", online.getOnlineId()).insert("online_email", online.getOnlineEmail())
					.table(Online.class).end();
		} catch (DuplicateKeyException e) {
			logger.error(e.getMessage(), e);
			return new DaoResult(false);
		}
		return new DaoResult(true);
	}

	@Override
	public DaoResult getAllOnlineDao() {
		kumaSqlDao.selectMode();
		ListSqlResult<Online> theResult = kumaSqlDao.select("online_email").from(Online.class)
				.endForList((rs, row) -> new Online.Builder().onlineEmail(rs.getString("online_email")).build());
		return new DaoResult(true, theResult.getResult());
	}

}
