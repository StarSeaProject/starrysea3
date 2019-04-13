package top.starrysea.activity.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;
import top.starrysea.activity.dao.fallback.ActivityImageDaoFallback;
import top.starrysea.activity.object.dto.ActivityImage;

@FeignClient(name = "starrysea-dal", fallbackFactory = ActivityImageDaoFallbackFactory.class)
public interface IActivityImageDao {

	@RequestMapping(value = "/activityImage/save", method = RequestMethod.POST)
	public Boolean saveActivityImageDao(@RequestBody List<ActivityImage> activityImages);
}

@Component
class ActivityImageDaoFallbackFactory implements FallbackFactory<IActivityImageDao> {

	@Override
	public IActivityImageDao create(Throwable cause) {
		return new ActivityImageDaoFallback(cause);
	}

}