package top.starrysea.activity.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.activity.dao.IActivityImageDao;
import top.starrysea.activity.object.dto.ActivityImage;

public class ActivityImageDaoFallback implements IActivityImageDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ActivityImageDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}

	@Override
	public Boolean saveActivityImageDao(List<ActivityImage> activityImages) {
		return false;
	}

}
