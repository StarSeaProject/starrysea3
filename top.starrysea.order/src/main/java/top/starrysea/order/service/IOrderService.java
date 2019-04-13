package top.starrysea.order.service;

import java.util.List;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.order.object.dto.OrderDetail;
import top.starrysea.order.object.dto.Orders;
import top.starrysea.order.object.dto.WorkType;

public interface IOrderService {

	ServiceResult queryAllOrderService(Condition condition, Orders order);

	ServiceResult queryOrderService(Orders order);

	ServiceResult addOrderService(Orders order, List<OrderDetail> orderDetails);

	ServiceResult modifyOrderService(Orders order);

	ServiceResult removeOrderService(Orders order);

	ServiceResult queryAllProvinceService();

	ServiceResult queryWorkTypeStock(List<WorkType> workType);

	ServiceResult exportOrderToXlsService();

	ServiceResult resendEmailService(Orders order);
	
	ServiceResult queryAllWorkTypeForShoppingCarService(List<WorkType> workTypes);
}
