package top.starrysea.dal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.dal.dao.IWorkTypeDao;
import top.starrysea.dal.entity.Orders;
import top.starrysea.dal.entity.WorkType;

import java.util.List;

@RestController
@RequestMapping("/workType")
public class WorkTypeController {

	@Autowired
	private IWorkTypeDao workTypeDao;

	@PostMapping("/all")
	public List<WorkType> getAllWorkTypeDao(@RequestBody WorkType workType) {
		return workTypeDao.getAllWorkTypeDao(workType).getResult(List.class);
	}

	@PostMapping("/stock")
	public Integer getWorkTypeStockDao(@RequestBody WorkType workType) {
		return workTypeDao.getWorkTypeStockDao(workType).getResult(Integer.class);
	}

	@PostMapping("/name")
	public WorkType getWorkTypeNameDao(@RequestBody WorkType workType) {
		return workTypeDao.getWorkTypeNameDao(workType).getResult(WorkType.class);
	}

	@PostMapping("/save")
	public Boolean saveWorkTypeDao(@RequestBody List<WorkType> workTypes) {
		return workTypeDao.saveWorkTypeDao(workTypes).isSuccessed();
	}

	@PostMapping("/delete")
	public Boolean deleteWorkTypeDao(@RequestBody WorkType workType) {
		return workTypeDao.deleteWorkTypeDao(workType).isSuccessed();
	}

	@PostMapping("/updateStock")
	public Boolean updateWorkTypeStockDao(@RequestBody WorkType workType) {
		return workTypeDao.updateWorkTypeStockDao(workType).isSuccessed();
	}

	@PostMapping("/reduceStock")
	public Boolean reduceWorkTypeStockDao(@RequestBody WorkType workType) {
		return workTypeDao.reduceWorkTypeStockDao(workType).isSuccessed();
	}

	@PostMapping("/updateStockByOrder")
	public Boolean updateWorkTypeStockDao(@RequestBody Orders order) {
		return workTypeDao.updateWorkTypeStockDao(order).isSuccessed();
	}

	@PostMapping("/allForCar")
	public List<WorkType> getAllWorkTypeForShoppingCarDao(@RequestBody List<WorkType> workTypes) {
		return workTypeDao.getAllWorkTypeForShoppingCarDao(workTypes).getResult(List.class);
	}
}
