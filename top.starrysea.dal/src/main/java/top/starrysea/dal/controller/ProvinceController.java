package top.starrysea.dal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.dal.dao.IProvinceDao;
import top.starrysea.dal.entity.Area;

@RestController
@RequestMapping("/province")
public class ProvinceController {

	@Autowired
	private IProvinceDao provinceDao;

	@GetMapping("/all")
	public List<Area> getAllProvinceDao() {
		return provinceDao.getAllProvinceDao().getResult(List.class);
	}
}
