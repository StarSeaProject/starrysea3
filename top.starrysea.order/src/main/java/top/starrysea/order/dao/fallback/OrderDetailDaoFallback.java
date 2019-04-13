package top.starrysea.order.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.order.dao.IOrderDetailDao;
import top.starrysea.order.object.dto.OrderDetail;

public class OrderDetailDaoFallback implements IOrderDetailDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public OrderDetailDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}
	
	@Override
	public List<OrderDetail> getAllOrderDetailDao(OrderDetail orderDetail) {
		return null;
	}

	@Override
	public Boolean saveOrderDetailsDao(List<OrderDetail> orderDetails) {
		return false;
	}

	@Override
	public Boolean isOrderDetailExistDao(OrderDetail orderDetail) {
		return false;
	}

	@Override
	public List<OrderDetail> getAllOrderDetailForXls() {
		return null;
	}

}
