package top.starrysea.dal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.dao.IAdminDao;
import top.starrysea.dal.entity.Admin;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminDao adminDao;

	@PostMapping("/login")
	public Object loginDao(@RequestBody Admin admin) {
		DaoResult daoResult = adminDao.loginDao(admin);
		return daoResult.isSuccessed() ? daoResult.getResult(Admin.class) : daoResult.getErrInfo();
	}
}
