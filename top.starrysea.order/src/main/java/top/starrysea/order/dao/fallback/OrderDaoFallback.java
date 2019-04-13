package top.starrysea.order.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.order.dao.IOrderDao;
import top.starrysea.order.object.dto.Orders;

public class OrderDaoFallback implements IOrderDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public OrderDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}
	
	@Override
	public List<Orders> getAllOrderDao(Orders order, int page) {
		return null;
	}

	@Override
	public Integer getOrderCountDao(Orders order, int page) {
		return null;
	}

	@Override
	public Orders getOrderDao(Orders order) {
		return null;
	}

	@Override
	public Orders saveOrderDao(Orders order) {
		return null;
	}

	@Override
	public Boolean updateOrderDao(Orders order) {
		return false;
	}

	@Override
	public Boolean deleteOrderDao(Orders order) {
		return false;
	}

}
