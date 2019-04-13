package top.starrysea.dal.dao;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.Work;

public interface IWorkDao {
	
	DaoResult getAllWorkDao(Condition condition, Work work);

	DaoResult getWorkCountDao(Condition condition, Work work);

	DaoResult getWorkDao(Work work);

	DaoResult saveWorkDao(Work work);

	DaoResult deleteWorkDao(Work work);
	
	DaoResult addWorkClick(Work work);

}
