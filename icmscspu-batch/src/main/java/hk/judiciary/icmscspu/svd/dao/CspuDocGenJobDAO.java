package hk.judiciary.icmscspu.svd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.Case;
import hk.judiciary.icms.model.dao.entity.CspuDocGenJob;
import hk.judiciary.icmscspu.svd.batchGenFreshSummons.core.RunnableApplication;
import hk.judiciary.icmscspu.svd.biz.dto.CspuDocGenJobRetrieveCriteriaDTO;

public class CspuDocGenJobDAO extends AbstractJpaBatchDAO<CspuDocGenJob> {
	
	private FmkLogger fmkLogger = new FmkLogger(CspuDocGenJobDAO.class);
	
	public static class Entities {
		
	}
	
	public static class Fields {
		public static final String CSPU_DOC_GEN_JOB_CD_NAME = "cspuDocGenJobCd";
	}
	
	public List<CspuDocGenJob> retrieve(CspuDocGenJobRetrieveCriteriaDTO retrieveCriteriaDTO) {
		EntityManager em = getEntityManager();
		if (em == null) {
			em = getEntityManagerFactory().createEntityManager();
		}
		
		fmkLogger.info("EntityManager "+em);
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<CspuDocGenJob> criteriaQuery = criteriaBuilder.createQuery(CspuDocGenJob.class);
		Root<CspuDocGenJob> entityRoot = criteriaQuery.from(CspuDocGenJob.class);
		criteriaQuery.select(entityRoot);
		
		List<Predicate> criterias = new ArrayList<Predicate>();
		Predicate criteria = null;
		
		if (retrieveCriteriaDTO.getCspuDocGenJobCd() != null) {
			criteria = criteriaBuilder.equal(entityRoot.get(Fields.CSPU_DOC_GEN_JOB_CD_NAME), retrieveCriteriaDTO.getCspuDocGenJobCd());
			criterias.add(criteria);
		}
		
		if (criterias.size() > 0) {
			criteria = criteriaBuilder.and((Predicate[])criterias.toArray(new Predicate[0]));
			criteriaQuery.where(criteria);
		}	
		
		TypedQuery<CspuDocGenJob> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
}
