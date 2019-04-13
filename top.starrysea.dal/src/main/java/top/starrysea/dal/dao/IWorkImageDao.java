package top.starrysea.dal.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.WorkImage;

public interface IWorkImageDao {

	DaoResult getAllWorkImageDao(WorkImage workImage);

	DaoResult saveWorkImageDao(List<WorkImage> workImages);
}
