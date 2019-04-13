package top.starrysea.work.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.work.dao.IWorkImageDao;
import top.starrysea.work.object.dto.WorkImage;

public class WorkTypeDaoFallback implements IWorkImageDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public WorkTypeDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}
	
	@Override
	public List<WorkImage> getAllWorkImageDao(WorkImage workImage) {
		return null;
	}

	@Override
	public Boolean saveWorkImageDao(List<WorkImage> workImages) {
		return false;
	}

}
