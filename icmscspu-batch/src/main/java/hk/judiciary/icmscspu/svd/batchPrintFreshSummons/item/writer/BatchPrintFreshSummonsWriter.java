package hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item.writer;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import hk.judiciary.fmk.ejb.webservice.WSClientHandler;
import hk.judiciary.fmk.logging.FmkLogger;

import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;

import hk.judiciary.fmk.batch.core.BatchContext;
import hk.judiciary.fmk.batch.item.writer.FmkJpaItemWriter;
import hk.judiciary.fmk.common.resource.AppResourceUtil;
import hk.judiciary.fmk.common.util.CommonUtil;
import hk.judiciary.icms.model.dao.entity.Case;
import hk.judiciary.icms.model.dao.entity.SummonNoti;
import hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item.PrintFreshSummonStepExecutionListener;
import hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item.processor.BatchPrintFreshSummonsProcessor;

public class BatchPrintFreshSummonsWriter extends FmkJpaItemWriter<Case> {
	
	private FmkLogger fmkLogger = new FmkLogger(BatchPrintFreshSummonsProcessor.class);
	
	private PrintFreshSummonStepExecutionListener stepExecutionListener;
	private String persistenceUnit;

	@Override
	public void write(List<? extends Case> caseList){
		
		
	}
	
	protected EntityManager getEntityManager() {
		try {
			InitialContext ex = new InitialContext();
			String persistenceContext = null;
			if (CommonUtil.isNullOrEmpty(this.persistenceUnit)) {
				persistenceContext = "java:jboss/persistence/"
						+ AppResourceUtil.getInstance().getAppConfigData().getValue("JPA.PERSISTENCE.NAME");
			} else {
				persistenceContext = "java:jboss/persistence/" + this.persistenceUnit;
			}

			EntityManager em = (EntityManager) ex.lookup(persistenceContext);
			return em;
		} catch (Exception arg3) {
			arg3.printStackTrace();
			return null;
		}
	}
	
}
