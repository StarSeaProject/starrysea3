package top.starrysea.activity.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;
import top.starrysea.activity.dao.fallback.FundingDaoFallback;
import top.starrysea.activity.object.dto.Funding;

@FeignClient(name = "starrysea-dal", fallbackFactory = FundingDaoFallbackFactory.class)
public interface IFundingDao {

	@RequestMapping(value = "/funding/all", method = RequestMethod.POST)
	public List<Funding> getAllFundingDao(@RequestBody Funding funding);

	@RequestMapping(value = "/funding/save", method = RequestMethod.POST)
	public Boolean saveFundingDao(@RequestBody List<Funding> fundings);

	@RequestMapping(value = "/funding/delete", method = RequestMethod.POST)
	public Boolean deleteFundingDao(@RequestBody Funding funding);
}

@Component
class FundingDaoFallbackFactory implements FallbackFactory<IFundingDao> {

	@Override
	public IFundingDao create(Throwable cause) {
		return new FundingDaoFallback(cause);
	}

}