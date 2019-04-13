package top.starrysea.work.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.work.object.dto.Work;

public class WorkForOne {
	@NotNull(message = "作品ID不能为空")
	private Integer workId;

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public Work toDTO() {
		return new Work.Builder().workId(workId).build();
	}
}
