package top.starrysea.order.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;
import top.starrysea.order.dao.fallback.WorkDaoFallback;
import top.starrysea.order.object.dto.Work;

@FeignClient(name = "starrysea-dal", fallbackFactory = WorkDaoFallbackFactory.class)
public interface IWorkDao {

	@RequestMapping(value = "/work/one", method = RequestMethod.POST)
	public Work getWorkDao(@RequestBody Work work);

}

@Component
class WorkDaoFallbackFactory implements FallbackFactory<IWorkDao> {

	@Override
	public IWorkDao create(Throwable cause) {
		return new WorkDaoFallback(cause);
	}

}