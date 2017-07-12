package hk.judiciary.icmscspu.svd.biz.dto;

import java.util.List;

import hk.judiciary.icmscspu.svd.dao.codeTable.CommonRetrieveCriteriaDTO;

public class CodeTableRetrieveCriteriaDTO extends CommonRetrieveCriteriaDTO {
	private static final long serialVersionUID = 1L;
	private String code;
	private List<String> codes;
	private String id;
	private List<String> ids;
	private String notId;
	private String notCode;
	// Special condition
	private Integer caseTypeId;
	public CodeTableRetrieveCriteriaDTO() {
		
	}
	
	public CodeTableRetrieveCriteriaDTO(boolean effvStartDateCheck, boolean effvEndDateCheck, String activeFlag) {
		this.setEffvStartDateCheck(effvStartDateCheck);
		this.setEffvEndDateCheck(effvEndDateCheck);
		this.setActiveFlag(activeFlag);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getId() {
		if (id != null) {
			return Integer.parseInt(id);
		}
		return null;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setId(Integer id) {
		if (id != null) {
			this.id = id.toString();
		}
	}
	public List<String> getCodes() {
		return codes;
	}
	public void setCodes(List<String> codes) {
		this.codes = codes;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public Integer getNotId() {
		if (notId != null) {
			return Integer.parseInt(notId);
		}
		return null;
	}
	public void setNotId(String notId) {
		this.notId = notId;
	}
	public void setNotId(Integer notId) {
		if (notId != null) {
			this.notId = notId.toString();
		}
	}
	public Integer getCaseTypeId() {
		return caseTypeId;
	}

	public void setCaseTypeId(Integer caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public String getNotCode() {
		return notCode;
	}

	public void setNotCode(String notCode) {
		this.notCode = notCode;
	}
}
