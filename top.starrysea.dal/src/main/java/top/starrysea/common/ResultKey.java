package top.starrysea.common;

import java.util.List;
import java.util.Map;

import top.starrysea.kql.entity.Entity;

public enum ResultKey {

	LIST_1(List.class), LIST_2(List.class),

	ENTITY(Entity.class),

	INTEGER(Integer.class), BOOLEAN(Boolean.class),

	MAP(Map.class);

	private Class<?> clazz;

	ResultKey(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
