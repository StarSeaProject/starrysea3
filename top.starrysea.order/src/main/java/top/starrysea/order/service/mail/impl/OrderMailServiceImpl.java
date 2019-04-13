package top.starrysea.order.service.mail.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.starrysea.order.dao.IWorkDao;
import top.starrysea.order.dao.IWorkTypeDao;
import top.starrysea.kql.entity.Entity;
import top.starrysea.order.object.dto.OrderDetail;
import top.starrysea.order.object.dto.Orders;
import top.starrysea.order.object.dto.WorkType;
import top.starrysea.order.mail.Mail;

@Service("orderMailService")
public class OrderMailServiceImpl extends MailServiceImpl {

	@Autowired
	private IWorkDao workDao;
	@Autowired
	private IWorkTypeDao workTypeDao;

	@Override
	public void sendMailService(List<? extends Entity> entitys) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (Entity entity : entitys) {
			OrderDetail orderDetail = (OrderDetail) entity;
			WorkType wt = workTypeDao.getWorkTypeNameDao(orderDetail.getWorkType());
			wt.setWork(workDao.getWorkDao(orderDetail.getWorkType().getWork()));
			orderDetail.setWorkType(wt);
			orderDetails.add(orderDetail);
		}
		Orders order = orderDetails.get(0).getOrder();
		StringBuilder orderDetailsStr = new StringBuilder(
				"<table border=1><thead><tr><td>作品名称</td><td>作品类型</td></tr></thead><tbody>");
		for (OrderDetail orderDetail : orderDetails) {
			orderDetailsStr.append("<tr>");
			orderDetailsStr.append("<td>" + orderDetail.getWorkType().getWork().getWorkName() + "</td>");
			orderDetailsStr.append("<td>" + orderDetail.getWorkType().getName() + "</td>");
			orderDetailsStr.append("</tr>");
		}
		orderDetailsStr.append("</tbody>");
		orderDetailsStr.append("</table>");
		String content = "<div>" + "这是�?封确认邮�?</div>" + "<div>您已经下单星之海的作�?,以下是您下单的详细信�?:</div>" + orderDetailsStr.toString()
				+ "<div>您的订单号为�?<b>" + order.getOrderNum() + "</b></div>"
				+ "<div>您可以根据此订单�?<a href=http://www.starrysea.top/order/" + order.getOrderNum() + ">查询您的发货信息</a></div>"
				+ "<div>我们会在发货后再发一封邮件来通知�?</div>" + "<div>如有问题请联系我们的官博或�?�邮件联系adky8a@qq.com</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿�?�公�?", content));
	}

}
