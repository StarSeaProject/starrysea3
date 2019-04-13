package top.starrysea.order.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.order.dao.IProvinceDao;
import top.starrysea.order.object.dto.Area;

public class ProvinceDaoFallback implements IProvinceDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ProvinceDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}
	
	@Override
	public List<Area> getAllProvinceDao() {
		return null;
	}

}
