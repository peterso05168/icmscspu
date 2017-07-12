package hk.judiciary.icmscspu.svd.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.Case;
import hk.judiciary.icms.model.dao.entity.CspuDocGenJob;
import hk.judiciary.icms.model.dao.entity.CspuJobLog;
import hk.judiciary.icms.model.dao.entity.CspuJobLogDtl;

public class CspuJobLogDAO extends AbstractJpaBatchDAO<CspuJobLog> {
private FmkLogger fmkLogger = new FmkLogger(CspuJobLogDAO.class);
	
	public CspuJobLog findCspuJobLog(int cspuJobLogId) {
		fmkLogger.info("findCspuJobLog start ");
		CspuJobLog cspuJobLog = getEntityManagerFactory().createEntityManager().find(CspuJobLog.class, cspuJobLogId);
        fmkLogger.info("findCspuJobLog end ");
        return cspuJobLog;
    }
	
	public Integer save(CspuJobLog entity) {
		EntityManager em = getEntityManager();
//		if (em == null) {
//			em = getEntityManagerFactory().createEntityManager();
//		}
		
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
		em.persist(entity);
//		tx.commit();
		return entity.getCspuJobLogId();
	}
}
