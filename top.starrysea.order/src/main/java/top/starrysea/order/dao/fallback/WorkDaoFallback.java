package top.starrysea.order.dao.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.order.dao.IWorkDao;
import top.starrysea.order.object.dto.Work;

public class WorkDaoFallback implements IWorkDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public WorkDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}
	
	@Override
	public Work getWorkDao(Work work) {
		return null;
	}

}
