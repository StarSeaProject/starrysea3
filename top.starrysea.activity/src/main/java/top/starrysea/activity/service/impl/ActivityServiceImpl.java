package top.starrysea.activity.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.activity.dao.IActivityDao;
import top.starrysea.activity.dao.IActivityImageDao;
import top.starrysea.activity.dao.IFundingDao;
import top.starrysea.activity.file.FileCondition;
import top.starrysea.activity.file.FileType;
import top.starrysea.activity.file.FileUtil;
import top.starrysea.exception.UpdateException;
import top.starrysea.activity.object.dto.Activity;
import top.starrysea.activity.object.dto.ActivityImage;
import top.starrysea.activity.object.dto.Funding;
import top.starrysea.activity.service.IActivityService;

import static top.starrysea.common.Const.FUNDING_FACTOR;
import static top.starrysea.common.ResultKey.*;

@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IActivityDao activityDao;
	@Autowired
	private IActivityImageDao activityImageDao;
	@Autowired
	private IFundingDao fundingDao;
	@Autowired
	private FileUtil fileUtil;
	public static final int PAGE_LIMIT = 10;

	@Override
	// 查询所有众筹活动
	public ServiceResult queryAllActivityService(Condition condition, Activity activity) {
		ServiceResult result = new ServiceResult();
		Activity a = activityDao.getNewestActivityDao();
		List<Activity> activitylist = activityDao.getAllActivityDao(activity, condition.getPage());
		int totalPage = 0;
		int count = activityDao.getActivityCountDao(activity, condition.getPage());
		if (count % PAGE_LIMIT == 0)
			totalPage = count / PAGE_LIMIT;
		else
			totalPage = (count / PAGE_LIMIT) + 1;

		result.setSuccessed(true);
		result.setResult(LIST_1, activitylist);
		result.setResult(ACTIVITY, a);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
	}

	@Override
	// 查询一个众筹活动的详情页
	public ServiceResult queryActivityService(Activity activity) {
		ServiceResult result = new ServiceResult();
		Activity a = activityDao.getActivityDao(activity);
		result.setSuccessed(true);
		result.setResult(ACTIVITY, a);
		List<Funding> fundings = fundingDao.getAllFundingDao(new Funding.Builder().activity(activity).build());
		double fundingMoneySum = fundings.stream().collect(Collectors.summingDouble(Funding::getFundingMoney));
		double richThreshold = fundingMoneySum * FUNDING_FACTOR;
		List<Funding> richFundings = new ArrayList<>();
		List<Funding> normalFundings = new ArrayList<>();
		for (Funding funding : fundings) {
			if (funding.getFundingMoney() > richThreshold) {
				richFundings.add(funding);
			} else {
				normalFundings.add(funding);
			}
		}
		richFundings.addAll(normalFundings);
		result.setResult(LIST_1, richFundings);
		result.setResult(DOUBLE, richThreshold);
		return result;
	}

	@Override
	// 添加一个众筹活动
	@Transactional
	public ServiceResult addActivityService(MultipartFile coverFile, Activity activity,
			List<ActivityImage> activityImages) {
		try {
			String originCoverFileName = fileUtil.saveFile(coverFile,
					FileCondition.of(FileType.IMG, 1, activity.getActivityName()));
			activity.setActivityCover(originCoverFileName);
			activity.setActivityStatus((short) 1);
			activity.setActivityId(activityDao.saveActivityDao(activity));
			for (ActivityImage activityImage : activityImages) {
				activityImage.setActivity(activity);
			}
			activityImageDao.saveActivityImageDao(activityImages);
			return new ServiceResult(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}
	}

	@Override
	// 修改一个众筹活动的状态
	public ServiceResult modifyActivityService(Activity activity) {
		activityDao.updateActivityDao(activity);
		return new ServiceResult(true);
	}

	@Override
	// 删除一个众筹活动
	public ServiceResult removeActivityService(Activity activity) {
		activityDao.deleteActivityDao(activity);
		return new ServiceResult(true);
	}

	@Override
	@Transactional
	public ServiceResult addFundingService(List<Funding> fundings) {
		try {
			fundingDao.saveFundingDao(fundings);
			List<Activity> activitys = new ArrayList<>();
			for (Funding funding : fundings) {
				Activity activity = new Activity.Builder().activityMoney(funding.getFundingMoney())
						.activityId(funding.getActivity().getActivityId()).build();
				activitys.add(activity);
			}
			activityDao.updateAddActivityMoneyDao(activitys);
			return new ServiceResult(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}
	}

	@Override
	@Transactional
	public ServiceResult removeFundingService(Funding funding) {
		try {
			fundingDao.deleteFundingDao(funding);
			Activity activity = new Activity.Builder().activityMoney(funding.getFundingMoney())
					.activityId(funding.getActivity().getActivityId()).build();
			activityDao.updateReduceActivityMoneyDao(activity);
			return new ServiceResult(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}
	}

}
