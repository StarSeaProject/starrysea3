package top.starrysea.dal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.dal.dao.IFundingDao;
import top.starrysea.dal.entity.Funding;

import java.util.List;

@RestController
@RequestMapping("/funding")
public class FundingController {

	@Autowired
	private IFundingDao fundingDao;

	@PostMapping("/all")
	public List<Funding> getAllFundingDao(@RequestBody Funding funding) {
		return fundingDao.getAllFundingDao(funding).getResult(List.class);
	}

	@PostMapping("/save")
	public Boolean saveFundingDao(@RequestBody List<Funding> fundings) {
		return fundingDao.saveFundingDao(fundings).isSuccessed();
	}

	@PostMapping("/delete")
	public Boolean deleteFundingDao(@RequestBody Funding funding) {
		return fundingDao.deleteFundingDao(funding).isSuccessed();
	}
}
