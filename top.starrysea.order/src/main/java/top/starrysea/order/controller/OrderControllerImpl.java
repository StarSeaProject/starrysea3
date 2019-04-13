package top.starrysea.order.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.Common;
import top.starrysea.common.ModelAndViewFactory;
import top.starrysea.common.ServiceResult;
import top.starrysea.order.object.dto.OrderDetail;
import top.starrysea.order.object.dto.Orders;
import top.starrysea.order.object.dto.WorkType;
import top.starrysea.order.object.view.in.OrderDetailForAddOrder;
import top.starrysea.order.object.view.in.OrderForAdd;
import top.starrysea.order.object.view.in.OrderForAll;
import top.starrysea.order.object.view.in.OrderForModify;
import top.starrysea.order.object.view.in.OrderForOne;
import top.starrysea.order.object.view.in.OrderForRemove;
import top.starrysea.order.object.view.in.WorkTypeForToAddOrders;
import top.starrysea.order.object.view.in.WorkTypesForRemoveCar;
import top.starrysea.order.object.view.in.WorkTypeForRemoveCar;
import top.starrysea.order.service.IOrderService;

import static top.starrysea.common.Const.*;
import static top.starrysea.common.ResultKey.*;

@Controller
public class OrderControllerImpl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IOrderService orderService;

	// 查询所有的订单
	@PostMapping("/index")
	@ResponseBody
	public Map<String, Object> queryAllOrderController(@RequestBody OrderForAll order) {
		ServiceResult serviceResult = orderService.queryAllOrderService(order.getCondition(), order.toDTO());
		List<Orders> result = serviceResult.getResult(LIST_1);
		List<top.starrysea.order.object.view.out.OrderForAll> voResult = result.stream().map(Orders::toVoForAll)
				.collect(Collectors.toList());
		Map<String, Object> theResult = new HashMap<>();
		theResult.put("orderName", order.getOrderName());
		theResult.put("result", voResult);
		theResult.put("nowPage", serviceResult.getNowPage());
		theResult.put("totalPage", serviceResult.getTotalPage());
		return theResult;
	}

	// 根据订单号查询一个订单的具体信息以及发货情况
	@GetMapping("/{orderNum}")
	public ModelAndView queryOrderController(@Valid OrderForOne order, BindingResult bindingResult, Device device) {
		ServiceResult serviceResult = orderService.queryOrderService(order.toDTO());
		Orders o = serviceResult.getResult(ORDER);
		List<OrderDetail> ods = serviceResult.getResult(LIST_1);
		ModelAndView modelAndView = new ModelAndView(device.isMobile() ? MOBILE + "orders_details" : "orders_details");
		modelAndView.addObject("order", o.toVoForOne());
		modelAndView.addObject("orderDetails", ods.stream().map(OrderDetail::toVoForOne).collect(Collectors.toList()));
		return modelAndView;
	}

	// 根据订单号查询一个订单的具体信息以及发货情况
	@PostMapping("/detail/ajax")
	@ResponseBody
	public Map<String, Object> queryOrderControllerAjax(@RequestBody @Valid OrderForRemove order,
			BindingResult bindingResult) {
		ServiceResult serviceResult = orderService.queryOrderService(order.toDTO());
		Orders o = serviceResult.getResult(ORDER);
		List<OrderDetail> ods = serviceResult.getResult(LIST_1);
		Map<String, Object> theResult = new HashMap<>();
		theResult.put("orders", o.toVoForOne());
		theResult.put("orderId", order.getOrderId());
		theResult.put("orderDetails", ods.stream().map(OrderDetail::toVoForOne).collect(Collectors.toList()));
		return theResult;
	}

	@PostMapping("/toAddOrder")
	public ModelAndView gotoAddOrder(@Valid WorkTypeForToAddOrders workTypes, Device device, HttpSession session) {
		ServiceResult sr = orderService.queryWorkTypeStock(workTypes.toDTO());
		if (!sr.isSuccessed()) {
			return ModelAndViewFactory.newErrorMav(sr.getErrInfo(), device);
		}
		ModelAndView modelAndView = new ModelAndView(device.isMobile() ? MOBILE + "add_order" : "add_order");
		modelAndView.addObject("workTypes", workTypes);
		modelAndView.addObject("provinces", orderService.queryAllProvinceService().getResult(MAP));
		String token = Common.getCharId(10);
		session.setAttribute(TOKEN, token);
		modelAndView.addObject(TOKEN, token);
		return modelAndView;
	}

	// 对一个作品进行下单
	@PostMapping("/add")
	public ModelAndView addOrderController(@Valid OrderForAdd order, BindingResult bindingResult, Device device,
			HttpSession session) {
		if (!order.getToken().equals(session.getAttribute(TOKEN))) {
			return ModelAndViewFactory.newErrorMav("您已经下单,请勿再次提交", device);
		}
		session.removeAttribute(TOKEN);
		ServiceResult serviceResult = orderService.addOrderService(order.toDTO(), order.toDTOOrderDetail());
		if (!serviceResult.isSuccessed()) {
			return ModelAndViewFactory.newErrorMav(serviceResult.getErrInfo(), device);
		}
		return ModelAndViewFactory.newSuccessMav("您已下单成功，之后将会为您派送！", device);
	}

	// 修改一个订单的状态
	@PostMapping("/modify/{orderId}")
	public ModelAndView modifyOrderController(@Valid OrderForModify order, BindingResult bindingResult, Device device) {
		orderService.modifyOrderService(order.toDTO());
		return ModelAndViewFactory.newSuccessMav("发货成功！", device);
	}

	// 删除一个订单
	@PostMapping("/remove/{orderId}")
	public ModelAndView removeOrderController(@Valid OrderForRemove order, BindingResult bindingResult, Device device) {
		orderService.removeOrderService(order.toDTO());
		return ModelAndViewFactory.newSuccessMav("删除成功!", device);
	}

	@GetMapping("/export")
	public void exportOrderToXlsController(HttpServletResponse response) {
		orderService.exportOrderToXlsService();
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + "result.xls");
		try {
			byte[] buff = Files.readAllBytes(Paths.get("/result.xls"));
			response.getOutputStream().write(buff);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@PostMapping("/resend")
	@ResponseBody
	public Map<String, Object> resendEmailController(@RequestBody @Valid OrderForRemove order,
			BindingResult bindingResult) {
		orderService.resendEmailService(order.toDTO());
		Map<String, Object> theResult = new HashMap<>();
		theResult.put("result", "success");
		return theResult;
	}

	@PostMapping("/car/add")
	@ResponseBody
	public Map<String, Object> addWorkToShoppingCarController(HttpSession session,
			@RequestBody @Valid OrderDetailForAddOrder orderDetail, BindingResult bindingResult, Device device) {
		List<OrderDetailForAddOrder> orderDetailList = (List<OrderDetailForAddOrder>) session.getAttribute(SHOPPINGCAR);
		if (orderDetailList == null) {
			orderDetailList = new ArrayList<>();
		}
		Map<String, Object> theResult = new HashMap<>();
		for (OrderDetailForAddOrder orderDetailForAddOrder : orderDetailList) {
			if (orderDetailForAddOrder.getWorkId() == orderDetail.getWorkId()) {
				theResult.put(INFO, "您已经将该作品放入购物车,不能重复放入");
				return theResult;
			}
		}
		orderDetailList.add(orderDetail);
		session.setAttribute(SHOPPINGCAR, orderDetailList);
		theResult.put(INFO, "添加到购物车成功!");
		return theResult;
	}

	@GetMapping("/car/remove/{index}")
	@ResponseBody
	public ModelAndView removeWorkFromShoppingCarController(HttpSession session, @Valid WorkTypeForRemoveCar workType,
			BindingResult bindingResult, Device device) {
		if (session.getAttribute(TOKEN) == null || !session.getAttribute(TOKEN).equals(workType.getToken())) {
			return ModelAndViewFactory.newErrorMav("您已经删除该作品,请勿再次提交", device);
		}
		session.removeAttribute(TOKEN);
		List<OrderDetailForAddOrder> orderDetailList = (List<OrderDetailForAddOrder>) session.getAttribute(SHOPPINGCAR);
		orderDetailList.remove((int) workType.getIndex());
		session.setAttribute(SHOPPINGCAR, orderDetailList);
		return ModelAndViewFactory.newSuccessMav("从购物车移除作品成功!", device);
	}

	@GetMapping("/car")
	public ModelAndView queryShoppingCarController(HttpSession session, Device device) {
		List<OrderDetailForAddOrder> orderDetailList = (List<OrderDetailForAddOrder>) session.getAttribute(SHOPPINGCAR);
		if (orderDetailList == null) {
			orderDetailList = new ArrayList<>();
		}
		List<WorkType> workTypes = orderService.queryAllWorkTypeForShoppingCarService(orderDetailList.stream()
				.map(orderDetail -> new WorkType.Builder().workTypeId(orderDetail.getWorkTypeId()).build())
				.collect(Collectors.toList())).getResult(LIST_1);
		ModelAndView modelAndView = new ModelAndView(device.isMobile() ? MOBILE + "shopcar" : "shopcar");
		modelAndView.addObject("workTypes", workTypes.stream().map(WorkType::toVoForCar).collect(Collectors.toList()));
		modelAndView.addObject("orderDetails", orderDetailList);
		String token = Common.getCharId(10);
		session.setAttribute(TOKEN, token);
		modelAndView.addObject(TOKEN, token);
		return modelAndView;
	}

	@PostMapping("/car/removes")
	public ModelAndView removeWorksFromShoppingCarController(HttpSession session,
			@Valid WorkTypesForRemoveCar workTypes, BindingResult bindingResult, Device device) {
		if (session.getAttribute(TOKEN) == null || !session.getAttribute(TOKEN).equals(workTypes.getToken())) {
			return ModelAndViewFactory.newErrorMav("您已经删除过这些作品,请勿再次提交", device);
		}
		session.removeAttribute(TOKEN);
		List<OrderDetailForAddOrder> orderDetailList = (List<OrderDetailForAddOrder>) session.getAttribute(SHOPPINGCAR);
		for (WorkTypeForRemoveCar workType : workTypes.getWorkTypes()) {
			orderDetailList.remove((int) workType.getIndex());
		}
		session.setAttribute(SHOPPINGCAR, orderDetailList);
		return ModelAndViewFactory.newSuccessMav("从购物车移除作品成功!", device);
	}
}
