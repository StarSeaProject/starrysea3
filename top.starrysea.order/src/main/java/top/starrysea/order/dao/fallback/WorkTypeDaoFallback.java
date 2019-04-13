package top.starrysea.order.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.order.dao.IWorkTypeDao;
import top.starrysea.order.object.dto.Orders;
import top.starrysea.order.object.dto.WorkType;

public class WorkTypeDaoFallback implements IWorkTypeDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public WorkTypeDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}
	
	@Override
	public Integer getWorkTypeStockDao(WorkType workType) {
		return null;
	}

	@Override
	public WorkType getWorkTypeNameDao(WorkType workType) {
		return null;
	}

	@Override
	public Boolean reduceWorkTypeStockDao(WorkType workType) {
		return false;
	}

	@Override
	public Boolean updateWorkTypeStockDao(Orders order) {
		return false;
	}

	@Override
	public List<WorkType> getAllWorkTypeForShoppingCarDao(List<WorkType> workTypes) {
		return null;
	}

}
