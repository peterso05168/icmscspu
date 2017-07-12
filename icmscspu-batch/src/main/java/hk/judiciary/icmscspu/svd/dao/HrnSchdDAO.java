package hk.judiciary.icmscspu.svd.dao;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.icms.model.dao.entity.SummonNoti;

public class HrnSchdDAO extends AbstractJpaBatchDAO<SummonNoti> {
	public static class Entities {
		public static final String CASE_NAME = "vcase";
	}
	public static class Fields {
		public static final String ENTITY_ID_NAME = "hrnSchdId";
		public static final String ENTITY_HRN_START_TIME = "hrnStartTime";
		public static final String ENTITY_HRN_SCHD_DATE = "hrnSchdDate";
		public static final String ENTITY_STATUS = "status";
	}
}
