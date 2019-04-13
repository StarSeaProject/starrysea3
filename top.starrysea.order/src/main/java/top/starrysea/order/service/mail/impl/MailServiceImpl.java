package top.starrysea.order.service.mail.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.starrysea.kql.entity.Entity;
import top.starrysea.order.mail.MailCommon;
import top.starrysea.order.service.IMailService;

@Component
public class MailServiceImpl implements IMailService {

	@Autowired
	protected MailCommon mailCommon;

	@Override
	public void sendMailService(Entity entity) {
		throw new UnsupportedOperationException("这是公共的邮件服�?,不支持调�?,请使用具体的子类!");
	}

	@Override
	public void sendMailService(List<? extends Entity> entitys) {
		throw new UnsupportedOperationException("这是公共的邮件服�?,不支持调�?,请使用具体的子类!");
	}

}
