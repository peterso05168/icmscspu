package hk.judiciary.icmscspu.svd.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.Case;
import hk.judiciary.icms.model.dao.entity.CspuJobLogDtl;

public class CaseDAO extends AbstractJpaBatchDAO<Case> {
	
	private FmkLogger fmkLogger = new FmkLogger(CspuJobLogDtlDAO.class);
	
	public static class Entities {
		public static final String SUMMON_NOTI_NAME = "summonNoti";
		public static final String HRN_SCHD_NAME = "hrnSchd";
		public static final String CASE_TYPE_NAME = "caseType";
		public static final String COURT_LVL_TYPE_NAME = "courtLvlType";
		public static final String COMPS_COURT_NAME = "compsCourt";
	}
	
	public static class Fields {
		public static final String INIT_DATE_NAME = "initDate";
		public static final String ENTITY_ID_NAME = "caseId";
		public static final String CASE_YEAR_NAME = "caseYr";
		public static final String CASE_SER_NO_NAME = "caseSerNo";
	}
	
	@Transactional
	public void commitUpdate(Case vcase) {
		fmkLogger.info("commitUpdate() start ");
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
        em.persist(vcase);
        em.getTransaction().commit();
        fmkLogger.info("commitUpdate() end ");
	}
}
