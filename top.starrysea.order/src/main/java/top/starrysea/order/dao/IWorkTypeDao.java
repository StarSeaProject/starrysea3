package top.starrysea.order.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;
import top.starrysea.order.dao.fallback.WorkTypeDaoFallback;
import top.starrysea.order.object.dto.Orders;
import top.starrysea.order.object.dto.WorkType;

@FeignClient(name = "starrysea-dal", fallbackFactory = WorkTypeDaoFallbackFactory.class)
public interface IWorkTypeDao {

	@RequestMapping(value = "/workType/stock", method = RequestMethod.POST)
	public Integer getWorkTypeStockDao(@RequestBody WorkType workType);

	@RequestMapping(value = "/workType/name", method = RequestMethod.POST)
	public WorkType getWorkTypeNameDao(@RequestBody WorkType workType);

	@RequestMapping(value = "/workType/reduceStock", method = RequestMethod.POST)
	public Boolean reduceWorkTypeStockDao(@RequestBody WorkType workType);

	@RequestMapping(value = "/workType/updateStockByOrder", method = RequestMethod.POST)
	public Boolean updateWorkTypeStockDao(@RequestBody Orders order);

	@RequestMapping(value = "/workType/allForCar", method = RequestMethod.POST)
	public List<WorkType> getAllWorkTypeForShoppingCarDao(@RequestBody List<WorkType> workTypes);
}

@Component
class WorkTypeDaoFallbackFactory implements FallbackFactory<IWorkTypeDao> {

	@Override
	public IWorkTypeDao create(Throwable cause) {
		return new WorkTypeDaoFallback(cause);
	}

}
