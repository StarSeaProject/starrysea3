package top.starrysea.order.service.mail.impl;

import org.springframework.stereotype.Service;

import top.starrysea.kql.entity.Entity;
import top.starrysea.order.object.dto.Orders;
import top.starrysea.order.mail.Mail;

@Service("sendOrderMailService")
public class SendOrderMailServiceImpl extends MailServiceImpl {

	@Override
	public void sendMailService(Entity entity) {
		Orders order = (Orders) entity;
		String content = "<div>这是�?封�?�知发货的邮�?</div>" + "<div>您订阅的星之海作品已经发�?</div>" + "<div>您的快�?�单号为"
				+ order.getOrderExpressnum() + ",我们默认统一使用顺丰到付</div>" + "<div>您也可以使用订单�?" + order.getOrderNum()
				+ "在官�?<a href=http://www.starrysea.top/order/" + order.getOrderNum()
				+ ">查询快�?�单�?</a>,但物流的详细信息�?要您去顺丰官网查�?</div>" + "<div>感谢您的订阅</div>"
				+ "<div>如有问题请联系我们的官博或�?�邮件联系adky8a@qq.com</div>";
		mailCommon.send(new Mail(order.getOrderEMail(), "星之海志愿�?�公�?", content));
	}

}
