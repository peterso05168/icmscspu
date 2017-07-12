package hk.judiciary.icmscspu.svd.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.Case;
import hk.judiciary.icms.model.dao.entity.CspuDocGenJob;
import hk.judiciary.icms.model.dao.entity.CspuJobLog;
import hk.judiciary.icms.model.dao.entity.CspuJobLogDtl;

public class CspuJobLogDtlDAO extends AbstractJpaBatchDAO<CspuJobLogDtl> {

private FmkLogger fmkLogger = new FmkLogger(CspuJobLogDtlDAO.class);

	public void save(CspuJobLogDtl cspuJobLogDtl) {
		this.getEntityManager().persist(cspuJobLogDtl);
	}
	
}
