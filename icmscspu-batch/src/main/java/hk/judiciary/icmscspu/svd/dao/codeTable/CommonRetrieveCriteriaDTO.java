package hk.judiciary.icmscspu.svd.dao.codeTable;

import java.io.Serializable;
import java.util.List;

import hk.judiciary.icmscspu.svd.biz.dto.CourtLvlTypeDTO;
import hk.judiciary.icmscspu.svd.biz.dto.OrderByCriteriaDTO;

public class CommonRetrieveCriteriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean effvStartDateCheck = false;
	private Boolean effvEndDateCheck = false;
	private String activeFlag;
	private CourtLvlTypeDTO courtLvlType;
	private List<OrderByCriteriaDTO> orderByCriterias;
	// Pagination
	private Integer pageSize;
	private Integer pageIndex;
	public Boolean getEffvStartDateCheck() {
		return effvStartDateCheck;
	}
	public void setEffvStartDateCheck(Boolean effvStartDateCheck) {
		this.effvStartDateCheck = effvStartDateCheck;
	}
	public Boolean getEffvEndDateCheck() {
		return effvEndDateCheck;
	}
	public void setEffvEndDateCheck(Boolean effvEndDateCheck) {
		this.effvEndDateCheck = effvEndDateCheck;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public List<OrderByCriteriaDTO> getOrderByCriterias() {
		return orderByCriterias;
	}
	public void setOrderByCriterias(List<OrderByCriteriaDTO> orderByCriterias) {
		this.orderByCriterias = orderByCriterias;
	}
	public CourtLvlTypeDTO getCourtLvlType() {
		return courtLvlType;
	}
	public void setCourtLvlType(CourtLvlTypeDTO courtLvlType) {
		this.courtLvlType = courtLvlType;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

}
