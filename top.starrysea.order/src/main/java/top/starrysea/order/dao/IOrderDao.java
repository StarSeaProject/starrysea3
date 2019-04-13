package top.starrysea.order.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import feign.hystrix.FallbackFactory;
import top.starrysea.order.dao.fallback.OrderDaoFallback;
import top.starrysea.order.object.dto.Orders;

@FeignClient(name = "starrysea-dal", fallbackFactory = OrderDaoFallbackFactory.class)
public interface IOrderDao {

	@RequestMapping(value = "/order/all", method = RequestMethod.POST)
	public List<Orders> getAllOrderDao(@RequestBody Orders order, @RequestParam("page") int page);

	@RequestMapping(value = "/order/count", method = RequestMethod.POST)
	public Integer getOrderCountDao(@RequestBody Orders order, @RequestParam("page") int page);

	@RequestMapping(value = "/order/one", method = RequestMethod.POST)
	public Orders getOrderDao(@RequestBody Orders order);

	@RequestMapping(value = "/order/save", method = RequestMethod.POST)
	public Orders saveOrderDao(@RequestBody Orders order);

	@RequestMapping(value = "/order/update", method = RequestMethod.POST)
	public Boolean updateOrderDao(@RequestBody Orders order);

	@RequestMapping(value = "/order/delete", method = RequestMethod.POST)
	public Boolean deleteOrderDao(@RequestBody Orders order);
}

@Component
class OrderDaoFallbackFactory implements FallbackFactory<IOrderDao> {

	@Override
	public IOrderDao create(Throwable cause) {
		return new OrderDaoFallback(cause);
	}

}
