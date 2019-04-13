package top.starrysea.dal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.dal.dao.IOnlineDao;
import top.starrysea.dal.entity.Online;

@RestController
@RequestMapping("/online")
public class OnlineController {

	@Autowired
	private IOnlineDao onlineDao;

	@GetMapping("/all")
	public List<Online> getAllOnlineDao() {
		return onlineDao.getAllOnlineDao().getResult(List.class);
	}

	@PostMapping("/save")
	public Boolean saveOnlineDao(@RequestBody Online online) {
		return onlineDao.saveOnlineDao(online).isSuccessed();
	}
}
