package hk.judiciary.icmscspu.batch.item.database;

import javax.persistence.Query;

import hk.judiciary.fmk.batch.item.reader.AbstractJpaQueryProvider;
import hk.judiciary.icms.model.dao.entity.Case;


public class CaseJpaQueryProvider extends AbstractJpaQueryProvider {

	public static final String QUERY_FIND_CASE_BY_CASE_ID = "Case.findCaseByCaseId";

	@Override
	public Query createQuery() {
		return getEntityManager().createNamedQuery(QUERY_FIND_CASE_BY_CASE_ID, Case.class);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

}
