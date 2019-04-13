package top.starrysea.dal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.common.Condition;
import top.starrysea.dal.dao.IActivityDao;
import top.starrysea.dal.entity.Activity;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private IActivityDao activityDao;

	@GetMapping("/newest")
	public Activity getNewestActivityDao() {
		return activityDao.getNewestActivityDao().getResult(Activity.class);
	}

	@PostMapping("/all")
	public List<Activity> getAllActivityDao(@RequestBody Activity activity, @RequestParam("page") int page) {
		return activityDao.getAllActivityDao(new Condition(page), activity).getResult(List.class);
	}

	@PostMapping("/count")
	public Integer getActivityCountDao(@RequestBody Activity activity, @RequestParam("page") int page) {
		return activityDao.getActivityCountDao(new Condition(page), activity).getResult(Integer.class);
	}

	@PostMapping("/one")
	public Activity getActivityDao(@RequestBody Activity activity) {
		return activityDao.getActivityDao(activity).getResult(Activity.class);
	}

	@PostMapping("/save")
	public Integer saveActivityDao(@RequestBody Activity activity) {
		return activityDao.saveActivityDao(activity).getResult(Integer.class);
	}

	@PostMapping("/update")
	public Boolean updateActivityDao(@RequestBody Activity activity) {
		return activityDao.updateActivityDao(activity).isSuccessed();
	}

	@PostMapping("/addMoney")
	public Boolean updateAddActivityMoneyDao(@RequestBody List<Activity> activitys) {
		return activityDao.updateAddActivityMoneyDao(activitys).isSuccessed();
	}

	@PostMapping("/reduceMoney")
	public Boolean updateReduceActivityMoneyDao(@RequestBody Activity activity) {
		return activityDao.updateReduceActivityMoneyDao(activity).isSuccessed();
	}

	@PostMapping("/delete")
	public Boolean deleteActivityDao(@RequestBody Activity activity) {
		return activityDao.deleteActivityDao(activity).isSuccessed();
	}
}
