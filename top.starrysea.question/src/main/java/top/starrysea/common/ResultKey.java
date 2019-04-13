package top.starrysea.common;

import java.util.List;
import java.util.Map;

public enum ResultKey {

	LIST_1(List.class), LIST_2(List.class),

	INTEGER(Integer.class), BOOLEAN(Boolean.class), DOUBLE(Double.class),

	MAP(Map.class);

	private Class<?> clazz;

	ResultKey(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
