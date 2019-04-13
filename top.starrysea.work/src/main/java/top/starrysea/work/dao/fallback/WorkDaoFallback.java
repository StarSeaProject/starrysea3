package top.starrysea.work.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.work.dao.IWorkDao;
import top.starrysea.work.object.dto.Work;

public class WorkDaoFallback implements IWorkDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public WorkDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}
	
	@Override
	public List<Work> getAllWorkDao(Work work, int page) {
		return null;
	}

	@Override
	public Integer getWorkCountDao(Work work, int page) {
		return null;
	}

	@Override
	public Work getWorkDao(Work work) {
		return null;
	}

	@Override
	public Integer saveWorkDao(Work work) {
		return null;
	}

	@Override
	public Boolean deleteWorkDao(Work work) {
		return false;
	}

	@Override
	public Boolean addWorkClick(Work work) {
		return false;
	}

}
