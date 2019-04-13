package top.starrysea.order.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;
import top.starrysea.order.dao.fallback.ProvinceDaoFallback;
import top.starrysea.order.object.dto.Area;

@FeignClient(name = "starrysea-dal", fallbackFactory = ProvinceDaoFallbackFactory.class)
public interface IProvinceDao {

	@RequestMapping(value = "/province/all", method = RequestMethod.GET)
	public List<Area> getAllProvinceDao();
}

@Component
class ProvinceDaoFallbackFactory implements FallbackFactory<IProvinceDao> {

	@Override
	public IProvinceDao create(Throwable cause) {
		return new ProvinceDaoFallback(cause);
	}

}
