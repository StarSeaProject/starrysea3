package top.starrysea.activity.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.activity.object.dto.Activity;

public class ActivityForOne {

	@NotNull(message = "活动id不能为空")
	private Integer activityId;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Activity toDTO() {
		return new Activity.Builder().activityId(activityId).build();
	}
}
