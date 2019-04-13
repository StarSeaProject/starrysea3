package top.starrysea.dal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.common.Condition;
import top.starrysea.dal.dao.IWorkDao;
import top.starrysea.dal.entity.Work;

@RestController
@RequestMapping("/work")
public class WorkController {

	@Autowired
	private IWorkDao workDao;

	@PostMapping("/all")
	public List<Work> getAllWorkDao(@RequestBody Work work, @RequestParam("page") int page) {
		return workDao.getAllWorkDao(new Condition(page), work).getResult(List.class);
	}

	@PostMapping("/count")
	public Integer getWorkCountDao(@RequestBody Work work, @RequestParam("page") int page) {
		return workDao.getWorkCountDao(new Condition(page), work).getResult(Integer.class);
	}

	@PostMapping("/one")
	public Work getWorkDao(@RequestBody Work work) {
		return workDao.getWorkDao(work).getResult(Work.class);
	}

	@PostMapping("/save")
	public Integer saveWorkDao(@RequestBody Work work) {
		return workDao.saveWorkDao(work).getResult(Integer.class);
	}

	@PostMapping("/delete")
	public Boolean deleteWorkDao(@RequestBody Work work) {
		return workDao.deleteWorkDao(work).isSuccessed();
	}

	@PostMapping("/addClick")
	public Boolean addWorkClick(@RequestBody Work work) {
		return workDao.addWorkClick(work).isSuccessed();
	}
}
