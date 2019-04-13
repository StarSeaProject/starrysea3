package top.starrysea.order.object.view.out;

import java.util.Date;

import top.starrysea.common.Common;
import top.starrysea.order.object.dto.Orders;

public class OrderForOne {
	
	private String orderName;
	private String orderAddress;
	private String orderStatus;
	private String orderExpressnum;
	private String orderTime;
	private String orderEMail;
	private String orderPhone;
	private String orderRemark;
	private String orderNum;

	public OrderForOne(Orders order) {
		this.orderName = order.getOrderName();
		this.orderAddress = order.getOrderArea().getCity().getProvince().getProvinceName()
				+ order.getOrderArea().getCity().getCityName() + order.getOrderArea().getAreaName()
				+ order.getOrderAddress();
		String status = "";
		if (order.getOrderStatus() == (short) 1) {
			status = "未发�?";
		} else if (order.getOrderStatus() == (short) 2) {
			status = "已发�?";
		}
		this.orderStatus = status;
		this.orderExpressnum = order.getOrderExpressnum();
		if (this.orderExpressnum == null) {
			this.orderExpressnum = "暂无";
		}
		this.orderTime = Common.time2String(new Date(order.getOrderTime()));
		this.orderEMail = order.getOrderEMail();
		this.orderPhone = order.getOrderPhone();
		this.orderRemark = order.getOrderRemark();
		this.orderNum = order.getOrderNum();
	}

	public String getOrderName() {
		return orderName;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public String getOrderExpressnum() {
		return orderExpressnum;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public String getOrderEMail() {
		return orderEMail;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public String getOrderNum() {
		return orderNum;
	}

}
