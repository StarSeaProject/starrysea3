package top.starrysea.dal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.common.Condition;
import top.starrysea.dal.dao.IOrderDao;
import top.starrysea.dal.entity.Orders;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderDao orderDao;

	@PostMapping("/all")
	public List<Orders> getAllOrderDao(@RequestBody Orders order, @RequestParam("page") int page) {
		return orderDao.getAllOrderDao(new Condition(page), order).getResult(List.class);
	}

	@PostMapping("/count")
	public Integer getOrderCountDao(@RequestBody Orders order, @RequestParam("page") int page) {
		return orderDao.getOrderCountDao(new Condition(page), order).getResult(Integer.class);
	}

	@PostMapping("/one")
	public Orders getOrderDao(@RequestBody Orders order) {
		return orderDao.getOrderDao(order).getResult(Orders.class);
	}

	@PostMapping("/save")
	public Orders saveOrderDao(@RequestBody Orders order) {
		return orderDao.saveOrderDao(order).getResult(Orders.class);
	}

	@PostMapping("/update")
	public Boolean updateOrderDao(@RequestBody Orders order) {
		return orderDao.updateOrderDao(order).isSuccessed();
	}

	@PostMapping("/delete")
	public Boolean deleteOrderDao(@RequestBody Orders order) {
		return orderDao.deleteOrderDao(order).isSuccessed();
	}
}
