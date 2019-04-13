package top.starrysea.activity.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.activity.dao.IActivityDao;
import top.starrysea.activity.object.dto.Activity;

public class ActivityDaoFallback implements IActivityDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ActivityDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}
	
	@Override
	public Activity getNewestActivityDao() {
		return null;
	}

	@Override
	public List<Activity> getAllActivityDao(Activity activity, int page) {
		return null;
	}

	@Override
	public Integer getActivityCountDao(Activity activity, int page) {
		return null;
	}

	@Override
	public Activity getActivityDao(Activity activity) {
		return null;
	}

	@Override
	public Integer saveActivityDao(Activity activity) {
		return null;
	}

	@Override
	public Boolean updateActivityDao(Activity activity) {
		return false;
	}

	@Override
	public Boolean updateAddActivityMoneyDao(List<Activity> activitys) {
		return false;
	}

	@Override
	public Boolean updateReduceActivityMoneyDao(Activity activity) {
		return false;
	}

	@Override
	public Boolean deleteActivityDao(Activity activity) {
		return false;
	}

}
