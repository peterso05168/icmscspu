package hk.judiciary.icmscspu.svd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.icms.model.dao.entity.CspuJobLogParam;

public class CspuJobLogParamDAO extends AbstractJpaBatchDAO<CspuJobLogParam> {

	public void save(List<CspuJobLogParam> entities) {
		EntityManager em = getEntityManager();
//		if (em == null) {
//			em = getEntityManagerFactory().createEntityManager();
//		}
		
		if (entities != null && entities.size() > 0) {
//			EntityTransaction tx = em.getTransaction();
//			tx.begin();
			for (CspuJobLogParam entity : entities) {
				em.persist(entity);
			}
//			tx.commit();
		}
	}
}
