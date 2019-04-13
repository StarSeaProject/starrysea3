package top.starrysea.order.object.view.in;

import javax.validation.constraints.NotNull;

import top.starrysea.order.object.dto.Orders;

public class OrderForOne {
	@NotNull(message = "订单号不能为空")
	private String orderNum;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Orders toDTO() {
		return new Orders.Builder().orderNum(orderNum).build();
	}
}
