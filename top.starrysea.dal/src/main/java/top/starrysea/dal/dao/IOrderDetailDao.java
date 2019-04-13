package top.starrysea.dal.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.OrderDetail;

public interface IOrderDetailDao {

	DaoResult getAllOrderDetailDao(OrderDetail orderDetail);
	
	DaoResult saveOrderDetailsDao(List<OrderDetail> orderDetails);
	
	DaoResult isOrderDetailExistDao(OrderDetail orderDetail);
	
	DaoResult getAllOrderDetailForXls();
}
