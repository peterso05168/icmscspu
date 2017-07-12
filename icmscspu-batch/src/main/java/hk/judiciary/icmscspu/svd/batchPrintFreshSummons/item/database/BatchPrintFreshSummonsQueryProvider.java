package hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item.database;

import java.util.Date;
import javax.persistence.Query;

import hk.judiciary.fmk.batch.item.reader.AbstractJpaQueryProvider;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icmscspu.svd.dao.CaseDAO;
import hk.judiciary.icmscspu.svd.dao.HrnSchdDAO;
import hk.judiciary.icmscspu.svd.dao.SummonNotiDAO;
import hk.judiciary.icmscspu.svd.dao.codeTable.CaseTypeDAO;
import hk.judiciary.icmscspu.svd.dao.codeTable.CompsCourtDAO;
import hk.judiciary.icmscspu.svd.dao.codeTable.CourtLvlTypeDAO;

public class BatchPrintFreshSummonsQueryProvider extends AbstractJpaQueryProvider {

	private FmkLogger fmkLogger = new FmkLogger(BatchPrintFreshSummonsQueryProvider.class);
	
	private static class Fields {
		private static final String CASE_CASE_TYPE_ID_NAME = CaseDAO.Entities.CASE_TYPE_NAME+"."+CaseTypeDAO.Fields.ENTITY_ID_NAME;
		private static final String CASE_COMPS_COURT_ID_NAME = CaseDAO.Entities.COMPS_COURT_NAME+"."+CompsCourtDAO.Fields.ENTITY_ID_NAME;
		private static final String HRN_SCHD_ID_NAME = CaseDAO.Entities.HRN_SCHD_NAME+"."+HrnSchdDAO.Fields.ENTITY_ID_NAME;
		private static final String SUMMON_NOTI_CASE_ID_NAME = CaseDAO.Entities.SUMMON_NOTI_NAME+"."+SummonNotiDAO.Fields.ENTITY_ID_NAME;
	}
		
	@Override
	public Query createQuery() {
		String sqlStr = "";
	
		
		fmkLogger.info("sqlStr "+sqlStr);
		Query query = getEntityManager().createQuery(sqlStr);
		return query;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
