package top.starrysea.work.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;
import top.starrysea.work.object.dto.WorkType;

@FeignClient(name = "starrysea-dal", fallbackFactory = WorkTypeDaoFallbackFactory.class)
public interface IWorkTypeDao {

	@RequestMapping(value = "/workType/all", method = RequestMethod.POST)
	public List<WorkType> getAllWorkTypeDao(@RequestBody WorkType workType);

	@RequestMapping(value = "/workType/save", method = RequestMethod.POST)
	public Boolean saveWorkTypeDao(@RequestBody List<WorkType> workTypes);

	@RequestMapping(value = "/workType/delete", method = RequestMethod.POST)
	public Boolean deleteWorkTypeDao(@RequestBody WorkType workType);

	@RequestMapping(value = "/workType/updateStock", method = RequestMethod.POST)
	public Boolean updateWorkTypeStockDao(@RequestBody WorkType workType);
}

@Component
class WorkTypeDaoFallbackFactory implements FallbackFactory<IWorkTypeDao> {

	@Override
	public IWorkTypeDao create(Throwable cause) {
		return null;
	}

}