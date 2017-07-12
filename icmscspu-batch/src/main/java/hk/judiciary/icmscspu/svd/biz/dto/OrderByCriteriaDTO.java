package hk.judiciary.icmscspu.svd.biz.dto;

import java.io.Serializable;

public class OrderByCriteriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String sortOrder;
	public OrderByCriteriaDTO() {
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public void setCriteria(String fieldName, String sortOrder) {
		this.fieldName = fieldName;
		this.sortOrder = sortOrder;

	}
}
