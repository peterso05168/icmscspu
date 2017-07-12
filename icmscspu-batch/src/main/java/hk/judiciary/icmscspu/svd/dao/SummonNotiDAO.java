package hk.judiciary.icmscspu.svd.dao;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.icms.model.dao.entity.SummonNoti;

public class SummonNotiDAO extends AbstractJpaBatchDAO<SummonNoti> {
	public static class Entities {
		public static final String CASE_NAME = "vcase";
	}
	public static class Fields {
		public static final String ENTITY_ID_NAME = "summonNotiId";
		public static final String ENTITY_ISSUE_DATE = "summonDemandNoteIssueDate";
	}
	
	public void update(SummonNoti summonNoti) {
		this.getEntityManager().persist(summonNoti);
	}
}
