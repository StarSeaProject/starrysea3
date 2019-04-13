package top.starrysea.order.object.view.in;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import top.starrysea.order.object.dto.WorkType;

public class WorkTypeForToAddOrders {

	@Valid
	@NotEmpty(message = "购物车中没有物品")
	private List<WorkTypeForToAddOrder> workTypes;

	public List<WorkTypeForToAddOrder> getWorkTypes() {
		return workTypes;
	}

	public void setWorkTypes(List<WorkTypeForToAddOrder> workTypes) {
		this.workTypes = workTypes;
	}

	public List<WorkType> toDTO() {
		return workTypes.stream().map(WorkTypeForToAddOrder::toDTO).collect(Collectors.toList());
	}
}
