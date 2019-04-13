package top.starrysea.activity.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.activity.dao.IFundingDao;
import top.starrysea.activity.object.dto.Funding;

public class FundingDaoFallback implements IFundingDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public FundingDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}

	@Override
	public List<Funding> getAllFundingDao(Funding funding) {
		return null;
	}

	@Override
	public Boolean saveFundingDao(List<Funding> fundings) {
		return false;
	}

	@Override
	public Boolean deleteFundingDao(Funding funding) {
		return false;
	}

}
