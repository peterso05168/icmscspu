package hk.judiciary.icmscspu.svd.biz.dto;

import java.io.Serializable;

public class CourtLvlTypeDTO extends CommonDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@GenericCodeTable(type="id")
	private Integer courtLvlTypeId;
	@GenericCodeTable(type="code")
	private String courtLvlTypeCd;
	@GenericCodeTable(type="descEng")
	private String courtLvlTypeDesc;
	public Integer getCourtLvlTypeId() {
		return courtLvlTypeId;
	}
	public void setCourtLvlTypeId(Integer courtLvlTypeId) {
		this.courtLvlTypeId = courtLvlTypeId;
	}
	public String getCourtLvlTypeCd() {
		return courtLvlTypeCd;
	}
	public void setCourtLvlTypeCd(String courtLvlTypeCd) {
		this.courtLvlTypeCd = courtLvlTypeCd;
	}
	public String getCourtLvlTypeDesc() {
		return courtLvlTypeDesc;
	}
	public void setCourtLvlTypeDesc(String courtLvlTypeDesc) {
		this.courtLvlTypeDesc = courtLvlTypeDesc;
	}
}
