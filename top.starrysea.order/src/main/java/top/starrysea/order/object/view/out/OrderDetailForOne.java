package top.starrysea.order.object.view.out;

import top.starrysea.order.object.dto.OrderDetail;

public class OrderDetailForOne {

	private String workTypeName;
	private String workName;

	public OrderDetailForOne(OrderDetail orderDetail) {
		this.workTypeName = orderDetail.getWorkType().getName();
		this.workName = orderDetail.getWorkType().getWork().getWorkName();
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public String getWorkName() {
		return workName;
	}

}
