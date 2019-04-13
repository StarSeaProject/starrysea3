package top.starrysea.work.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.common.Common;
import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.work.dao.IWorkDao;
import top.starrysea.work.dao.IWorkImageDao;
import top.starrysea.work.dao.IWorkTypeDao;
import top.starrysea.exception.UpdateException;
import top.starrysea.work.file.FileCondition;
import top.starrysea.work.file.FileType;
import top.starrysea.work.file.FileUtil;
import top.starrysea.work.object.dto.Work;
import top.starrysea.work.object.dto.WorkImage;
import top.starrysea.work.object.dto.WorkType;
import top.starrysea.work.service.IWorkService;

import static top.starrysea.common.ResultKey.*;

@Service("workService")
public class WorkServiceImpl implements IWorkService {

	// 作品每页显示条数
	public static final int PAGE_LIMIT = 10;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IWorkDao workDao;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private IWorkImageDao workImageDao;
	@Autowired
	private IWorkTypeDao workTypeDao;

	@Override
	// 查询�?有作�?
	public ServiceResult queryAllWorkService(Condition condition, Work work) {
		ServiceResult result = new ServiceResult();
		List<Work> workList = workDao.getAllWorkDao(work, condition.getPage());
		int totalPage = 0;
		int count = workDao.getWorkCountDao(work, condition.getPage());
		if (count % PAGE_LIMIT == 0) {
			totalPage = count / PAGE_LIMIT;
		} else {
			totalPage = (count / PAGE_LIMIT) + 1;
		}
		result.setSuccessed(true);
		result.setResult(LIST_1, workList);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
	}

	@Override
	// 查询�?个作品的详情�?
	public ServiceResult queryWorkService(Work work) {
		ServiceResult result = new ServiceResult();
		workDao.addWorkClick(work);
		result.setSuccessed(true);
		result.setResult(WORK, workDao.getWorkDao(work));
		result.setResult(LIST_1, workImageDao.getAllWorkImageDao(new WorkImage.Builder().work(work).build()));
		result.setResult(LIST_2, workTypeDao.getAllWorkTypeDao(new WorkType.Builder().work(work).build()));
		return result;
	}

	@Override
	// 添加�?个作�?
	@Transactional
	public ServiceResult addWorkService(MultipartFile coverFile, MultipartFile[] imageFiles, Work work,
			List<WorkType> workTypes) {
		try {
			String originCoverFileName = fileUtil.saveFile(coverFile,
					FileCondition.of(FileType.IMG, 1, work.getWorkName()));
			work.setWorkUploadTime(Common.getNowDate());
			work.setWorkCover(originCoverFileName);
			work.setWorkId(workDao.saveWorkDao(work));
			List<WorkImage> workImages = new ArrayList<>();
			for (MultipartFile imageFile : imageFiles) {
				String originImageFileName = fileUtil.saveFile(imageFile,
						FileCondition.of(FileType.IMG, 1, work.getWorkName()));
				workImages.add(new WorkImage.Builder().work(work).workImagePath(originImageFileName).build());
			}
			workImageDao.saveWorkImageDao(workImages);
			for (WorkType workType : workTypes) {
				workType.setWork(work);
			}
			workTypeDao.saveWorkTypeDao(workTypes);
			ServiceResult serviceResult = new ServiceResult(true);
			serviceResult.setResult(WORK, work);
			return serviceResult;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UpdateException(e);
		}
	}

	@Override
	// 删除�?个作�?
	public ServiceResult removeWorkService(Work work) {
		workDao.deleteWorkDao(work);
		return new ServiceResult(true);
	}

	@Override
	public ServiceResult removeWorkTypeService(WorkType workType) {
		workTypeDao.deleteWorkTypeDao(workType);
		return new ServiceResult(true);
	}

	@Override
	public ServiceResult modifyWorkTypeService(WorkType workType) {
		workTypeDao.updateWorkTypeStockDao(workType);
		return new ServiceResult(true);
	}

}
