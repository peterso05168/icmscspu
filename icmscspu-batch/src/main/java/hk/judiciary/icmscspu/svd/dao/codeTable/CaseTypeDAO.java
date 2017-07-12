package hk.judiciary.icmscspu.svd.dao.codeTable;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.icms.model.dao.entity.CaseType;

public class CaseTypeDAO extends AbstractJpaBatchDAO<CaseType> {
	public static class Fields {
		public static final String ENTITY_ID_NAME = "caseTypeId";
		public static final String ENTITY_CD_NAME = "caseTypeCd";
		public static final String ACTIVE_FLAG_NAME = "activeFlag";
	}
}
