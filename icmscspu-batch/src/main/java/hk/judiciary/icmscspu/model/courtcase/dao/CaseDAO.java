package hk.judiciary.icmscspu.model.courtcase.dao;

import javax.persistence.TypedQuery;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.icms.model.dao.entity.Case;


public class CaseDAO extends AbstractJpaBatchDAO<Case> {

	public static final String CASE_DAO = "caseDAO";

	public static final String PARAM_CASE_NO = "caseNo";

	public static final String QUERY_FIND_CASE_BY_CASE_ID = "Case.findCaseByCaseId";
	
	/**
	 * retrieve the case 
	 * 
	 * @param caseNo.
	 * @return case
	 */
	public Case findCaseByCaseNo(String caseId) {
		TypedQuery<Case> query = getEntityManager().createNamedQuery(
				QUERY_FIND_CASE_BY_CASE_ID, Case.class);
		query.setParameter(PARAM_CASE_NO, caseId);
		return this.getSingleResult(query);
	}

}
