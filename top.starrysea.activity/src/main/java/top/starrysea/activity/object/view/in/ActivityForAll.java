package top.starrysea.activity.object.view.in;

import top.starrysea.common.Condition;
import top.starrysea.activity.object.dto.Activity;

public class ActivityForAll {

	private String activityName;
	private Integer page;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	public Condition getCondition() {
		return new Condition(page != null ? page : 1);
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Activity toDTO() {
		return new Activity.Builder().activityName(activityName).build();
	}
}
