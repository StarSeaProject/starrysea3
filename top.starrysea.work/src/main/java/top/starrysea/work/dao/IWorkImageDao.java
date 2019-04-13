package top.starrysea.work.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;
import top.starrysea.work.object.dto.WorkImage;

@FeignClient(name = "starrysea-dal", fallbackFactory = WorkImageDaoFallbackFactory.class)
public interface IWorkImageDao {

	@RequestMapping(value = "/workImage/all", method = RequestMethod.POST)
	public List<WorkImage> getAllWorkImageDao(@RequestBody WorkImage workImage);

	@RequestMapping(value = "/workImage/save", method = RequestMethod.POST)
	public Boolean saveWorkImageDao(@RequestBody List<WorkImage> workImages);
}

@Component
class WorkImageDaoFallbackFactory implements FallbackFactory<IWorkImageDao> {

	@Override
	public IWorkImageDao create(Throwable cause) {
		return null;
	}

}
