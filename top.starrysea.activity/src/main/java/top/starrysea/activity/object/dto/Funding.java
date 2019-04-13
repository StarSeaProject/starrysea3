package top.starrysea.activity.object.dto;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;

public class Funding implements Entity {

	private Integer fundingId;
	private Activity activity;
	private String fundingName;
	private Double fundingMoney;
	private String fundingMessage;
	
	public Funding() {}
	private Funding(Builder builder) {
		this.fundingId=builder.fundingId;
		this.activity=builder.activity;
		this.fundingName=builder.fundingName;
		this.fundingMoney=builder.fundingMoney;
		this.fundingMessage=builder.fundingMessage;
	}
	
	public static class Builder implements IBuilder<Funding>{

		private Integer fundingId;
		private Activity activity;
		private String fundingName;
		private Double fundingMoney;
		private String fundingMessage;
		
		public Builder fundingId(Integer fundingId) {
			this.fundingId=fundingId;
			return this;
		}
		
		public Builder activity(Activity activity) {
			this.activity=activity;
			return this;
		}
		
		public Builder fundingName(String fundingName) {
			this.fundingName=fundingName;
			return this;
		}
		
		public Builder fundingMoney(Double fundingMoney) {
			this.fundingMoney=fundingMoney;
			return this;
		}
		
		public Builder fundingMessage(String fundingMessage) {
			this.fundingMessage=fundingMessage;
			return this;
		}
		
		@Override
		public Funding build() {
			return new Funding(this);
		}
	}

	public Integer getFundingId() {
		return fundingId;
	}

	public void setFundingId(Integer fundingId) {
		this.fundingId = fundingId;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getFundingName() {
		return fundingName;
	}

	public void setFundingName(String fundingName) {
		this.fundingName = fundingName;
	}

	public Double getFundingMoney() {
		return fundingMoney;
	}

	public void setFundingMoney(Double fundingMoney) {
		this.fundingMoney = fundingMoney;
	}

	public String getFundingMessage() {
		return fundingMessage;
	}

	public void setFundingMessage(String fundingMessage) {
		this.fundingMessage = fundingMessage;
	}
	
}
