package top.starrysea.dal.dao;

import java.util.List;

import top.starrysea.common.Condition;
import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.Activity;

public interface IActivityDao {
	
	DaoResult getNewestActivityDao();
	
	DaoResult getAllActivityDao(Condition condition, Activity activity);

	DaoResult getActivityCountDao(Condition condition, Activity activity);

	DaoResult getActivityDao(Activity activity);

	DaoResult saveActivityDao(Activity activity);

	DaoResult updateActivityDao(Activity activity);
	
	DaoResult updateAddActivityMoneyDao(List<Activity> activitys);
	
	DaoResult updateReduceActivityMoneyDao(Activity activity);

	DaoResult deleteActivityDao(Activity activity);
}
