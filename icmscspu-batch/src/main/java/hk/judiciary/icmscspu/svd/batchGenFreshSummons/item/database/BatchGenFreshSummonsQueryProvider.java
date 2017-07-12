package hk.judiciary.icmscspu.svd.batchGenFreshSummons.item.database;

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

public class BatchGenFreshSummonsQueryProvider extends AbstractJpaQueryProvider {

	private FmkLogger fmkLogger = new FmkLogger(BatchGenFreshSummonsQueryProvider.class);
	
	private static class Fields {
		private static final String CASE_CASE_TYPE_ID_NAME = CaseDAO.Entities.CASE_TYPE_NAME+"."+CaseTypeDAO.Fields.ENTITY_ID_NAME;
		private static final String CASE_COMPS_COURT_ID_NAME = CaseDAO.Entities.COMPS_COURT_NAME+"."+CompsCourtDAO.Fields.ENTITY_ID_NAME;
		private static final String HRN_SCHD_ID_NAME = CaseDAO.Entities.HRN_SCHD_NAME+"."+HrnSchdDAO.Fields.ENTITY_ID_NAME;
		private static final String SUMMON_NOTI_CASE_ID_NAME = CaseDAO.Entities.SUMMON_NOTI_NAME+"."+SummonNotiDAO.Fields.ENTITY_ID_NAME;
	}
		
	@Override
	public Query createQuery() {
		//31-5-2017 UPDATES: NO NEED TO LOOP COURT ANYMORE
		//PrintFreshSummonStepExecutionListener stepExecutionListener = BatchContext.getBean("svdBatchPrintFreshSummonsStepExecutionListener", PrintFreshSummonStepExecutionListener.class);
		
		String sqlStr = "";
		//OLD QUERY
//		sqlStr += " SELECT t0 FROM Case t0 "+
//				" INNER JOIN CaseType t1 ON t0."+Fields.CASE_CASE_TYPE_ID_NAME+" = t1."+CaseTypeDAO.Fields.ENTITY_ID_NAME+" AND t1."+CaseTypeDAO.Fields.ACTIVE_FLAG_NAME+" = '1' "+
//				" INNER JOIN CourtLvlType t2 ON t0."+Fields.CASE_COURT_LVL_TYPE_ID_NAME+" = t2."+CourtLvlTypeDAO.Fields.ENTITY_ID_NAME+" AND t2."+CourtLvlTypeDAO.Fields.ACTIVE_FLAG_NAME+" = '1' "+
//				" INNER JOIN CompsCourt t4 ON t0."+Fields.CASE_COMPS_COURT_ID_NAME+" = t4."+CompsCourtDAO.Fields.ENTITY_ID_NAME+" "+
//				" WHERE t0."+CaseDAO.Fields.INIT_DATE_NAME+" >= :dateFrom "+
//				" AND t0."+CaseDAO.Fields.INIT_DATE_NAME+" <= :dateTo "+
//				" AND t1."+CaseTypeDAO.Fields.ENTITY_CD_NAME+" = :caseTypeCd "+
//				" AND t2."+CourtLvlTypeDAO.Fields.ENTITY_CD_NAME+" = :courtLvlTypeCd "+
//				" AND t4."+CompsCourtDAO.Fields.ENTITY_CD_NAME+" = :compsCourtCd "+ 
//				" ORDER BY t0."+CaseDAO.Fields.CASE_YEAR_NAME+" ASC, t0."+CaseDAO.Fields.CASE_SER_NO_NAME+" ASC "+
//				"";
		
		//TODO: CREATE NEW QUERY ACCORDING TO CHANGES ON 31-5-2017
		sqlStr += " SELECT t0 FROM Case t0 " +
				" INNER JOIN CaseType t1 ON t0."+Fields.CASE_CASE_TYPE_ID_NAME+" = t1."+CaseTypeDAO.Fields.ENTITY_ID_NAME+" AND t1."+CaseTypeDAO.Fields.ACTIVE_FLAG_NAME+" = '1' "+
				" INNER JOIN CompsCourt t2 ON t0."+Fields.CASE_COMPS_COURT_ID_NAME+" = t2."+CompsCourtDAO.Fields.ENTITY_ID_NAME+" "+
				" INNER JOIN SummonNoti t3 ON t0."+Fields.SUMMON_NOTI_CASE_ID_NAME+" = t3."+SummonNotiDAO.Fields.ENTITY_ID_NAME+" "+
				" INNER JOIN HrnSchd t4 ON t0."+Fields.HRN_SCHD_ID_NAME+" = t4."+HrnSchdDAO.Fields.ENTITY_ID_NAME+" "+
				" WHERE t1."+CaseTypeDAO.Fields.ENTITY_CD_NAME+" = :caseTypeCd "+
				" AND t2."+CompsCourtDAO.Fields.ENTITY_CD_NAME+" = :compsCourtCd "+
				" AND t3."+SummonNotiDAO.Fields.ENTITY_ISSUE_DATE+" IS NULL "+
				" AND t4."+HrnSchdDAO.Fields.ENTITY_STATUS+" = \"A\""+
				//" AND t4."+HrnSchdDAO.Fields.ENTITY_HRN_SCHD_DATE+" > :currentDate" + 
				" ORDER BY t0."+CaseDAO.Fields.CASE_YEAR_NAME+" ASC, t0."+CaseDAO.Fields.CASE_SER_NO_NAME+" ASC ";
//		
		
		fmkLogger.info("sqlStr "+sqlStr);
		Query query = getEntityManager().createQuery(sqlStr);
		//query.setParameter("currentDate", new Date());
		return query;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
