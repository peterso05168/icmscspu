package hk.judiciary.icmscspu.svd.dao.codeTable;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.icms.model.dao.entity.CourtLvlType;

public class CourtLvlTypeDAO extends AbstractJpaBatchDAO<CourtLvlType> {
	public static class Entities {
		
	}
	public static class Fields {
		public static final String ENTITY_ID_NAME = "courtLvlTypeId";
		public static final String ENTITY_CD_NAME = "courtLvlTypeCd";
		public static final String ACTIVE_FLAG_NAME = "activeFlag";
	}
	
	public static final String NAME = "courtLvlTypeDAO";
}
