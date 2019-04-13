package top.starrysea.dal.entity;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;

public class Online implements Entity {

	private String onlineId;
	private String onlineEmail;

	public Online() {}
	private Online(Builder builder) {
		this.onlineId = builder.onlineId;
		this.onlineEmail = builder.onlineEmail;
	}

	public static class Builder implements IBuilder<Online> {

		private String onlineId;
		private String onlineEmail;

		public Builder onlineId(String onlineId) {
			this.onlineId = onlineId;
			return this;
		}

		public Builder onlineEmail(String onlineEmail) {
			this.onlineEmail = onlineEmail;
			return this;
		}

		@Override
		public Online build() {
			return new Online(this);
		}

	}

	public String getOnlineId() {
		return onlineId;
	}

	public void setOnlineId(String onlineId) {
		this.onlineId = onlineId;
	}

	public String getOnlineEmail() {
		return onlineEmail;
	}

	public void setOnlineEmail(String onlineEmail) {
		this.onlineEmail = onlineEmail;
	}

}
