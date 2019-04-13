package top.starrysea.dal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.dal.dao.IOrderDetailDao;
import top.starrysea.dal.entity.OrderDetail;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

	@Autowired
	private IOrderDetailDao orderDetailDao;

	@PostMapping("/all")
	public List<OrderDetail> getAllOrderDetailDao(@RequestBody OrderDetail orderDetail) {
		return orderDetailDao.getAllOrderDetailDao(orderDetail).getResult(List.class);
	}

	@PostMapping("/save")
	public Boolean saveOrderDetailsDao(@RequestBody List<OrderDetail> orderDetails) {
		return orderDetailDao.saveOrderDetailsDao(orderDetails).isSuccessed();
	}

	@PostMapping("/isExist")
	public Boolean isOrderDetailExistDao(@RequestBody OrderDetail orderDetail) {
		return orderDetailDao.isOrderDetailExistDao(orderDetail).getResult(Boolean.class);
	}

	@GetMapping("/xls")
	public List<OrderDetail> getAllOrderDetailForXls() {
		return orderDetailDao.getAllOrderDetailForXls().getResult(List.class);
	}
}
