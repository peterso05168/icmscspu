package hk.judiciary.icmscspu.svd.biz.dto;

import hk.judiciary.icmscspu.svd.dao.codeTable.CommonRetrieveCriteriaDTO;

public class CspuDocGenJobRetrieveCriteriaDTO extends CommonRetrieveCriteriaDTO {
	private String cspuDocGenJobCd;

	public String getCspuDocGenJobCd() {
		return cspuDocGenJobCd;
	}

	public void setCspuDocGenJobCd(String cspuDocGenJobCd) {
		this.cspuDocGenJobCd = cspuDocGenJobCd;
	}
	
}
