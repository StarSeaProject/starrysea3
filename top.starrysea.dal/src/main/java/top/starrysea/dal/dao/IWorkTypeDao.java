package top.starrysea.dal.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.Orders;
import top.starrysea.dal.entity.WorkType;

public interface IWorkTypeDao {

	DaoResult getAllWorkTypeDao(WorkType workType);
	
	DaoResult getWorkTypeStockDao(WorkType workType);
	
	DaoResult getWorkTypeNameDao(WorkType workType);
	
	DaoResult saveWorkTypeDao(List<WorkType> workTypes);
	
	DaoResult deleteWorkTypeDao(WorkType workType);
	
	DaoResult updateWorkTypeStockDao(WorkType workType);
	
	DaoResult reduceWorkTypeStockDao(WorkType workType);
	
	DaoResult updateWorkTypeStockDao(Orders order);
	
	DaoResult getAllWorkTypeForShoppingCarDao(List<WorkType> workTypes);
}
