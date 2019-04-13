package top.starrysea.dal.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.Funding;

public interface IFundingDao {

	DaoResult getAllFundingDao(Funding funding);
	
	DaoResult saveFundingDao(List<Funding> fundings);
	
	DaoResult deleteFundingDao(Funding funding);
}
