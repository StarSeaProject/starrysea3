package top.starrysea.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.starrysea.kql.entity.Entity;

public class DaoResult {

	private boolean successed;
	private Map<String, Object> theResult;
	private String errInfo;

	public DaoResult() {
	}

	public DaoResult(boolean successed) {
		this.successed = successed;
	}

	public DaoResult(boolean successed, Map<?, ?> result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put("map", result);
	}

	public DaoResult(boolean successed, List<?> result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put("list", result);
	}

	public DaoResult(boolean successed, Entity result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put("entity", result);
	}

	public DaoResult(boolean successed, Integer result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put("integer", result);
	}

	public DaoResult(boolean successed, boolean result) {
		theResult = new HashMap<>();
		this.successed = successed;
		this.theResult.put("boolean", result);
	}

	public DaoResult(boolean result, String errInfo) {
		this.successed = result;
		this.errInfo = errInfo;
	}

	public boolean isSuccessed() {
		return successed;
	}

	public void setSuccessed(boolean successed) {
		this.successed = successed;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public Map<String, Object> getTheResult() {
		return theResult;
	}

	public Object getResult(String key) {
		return theResult.get(key);
	}

}
