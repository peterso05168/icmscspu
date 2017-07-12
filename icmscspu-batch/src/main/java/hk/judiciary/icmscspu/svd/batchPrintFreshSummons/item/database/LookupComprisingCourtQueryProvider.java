package hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item.database;

import javax.persistence.Query;

import hk.judiciary.fmk.batch.item.reader.AbstractJpaQueryProvider;
import hk.judiciary.icmscspu.svd.dao.codeTable.CompsCourtDAO;
import hk.judiciary.icmscspu.svd.dao.codeTable.CourtLvlTypeDAO;

public class LookupComprisingCourtQueryProvider extends AbstractJpaQueryProvider {

	private final String COURT_LVL_TYPE_CD_NAME = CompsCourtDAO.Entities.COURT_LVL_TYPE_NAME+"."+CourtLvlTypeDAO.Fields.ENTITY_CD_NAME;
	
	@Override
	public Query createQuery() {
		// TODO Auto-generated method stub
		String sqlStr = "SELECT t0 FROM CompsCourt t0 "+
				" WHERE t0."+COURT_LVL_TYPE_CD_NAME+" = :courtLvlTypeCd ";
		Query query = getEntityManager().createQuery(sqlStr);
		return query;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
