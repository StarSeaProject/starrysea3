package top.starrysea.dal.dao;

import java.util.List;

import top.starrysea.common.DaoResult;
import top.starrysea.dal.entity.Activity;
import top.starrysea.dal.entity.ActivityImage;

public interface IActivityImageDao {

	DaoResult getAllActivityImageDao(Activity activity);

	DaoResult saveActivityImageDao(List<ActivityImage> activityImages);
}
