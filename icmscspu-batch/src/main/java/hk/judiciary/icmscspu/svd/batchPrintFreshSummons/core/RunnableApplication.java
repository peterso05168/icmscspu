package hk.judiciary.icmscspu.svd.batchPrintFreshSummons.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import hk.judiciary.fmk.batch.core.BatchContext;
import hk.judiciary.fmk.batch.core.DefaultRunnableApplication;
import hk.judiciary.fmk.batch.item.reader.FmkJpaPagingItemReader;
import hk.judiciary.fmk.common.security.user.JudiciaryUser;
import hk.judiciary.fmk.ejb.dao.AbstractJpaDAO;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.CompsCourt;
import hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item.PrintFreshSummonStepExecutionListener;
import hk.judiciary.icmscspu.svd.biz.dto.CodeTableRetrieveCriteriaDTO;
import hk.judiciary.icmscspu.svd.dao.codeTable.CompsCourtDAO;

public class RunnableApplication extends DefaultRunnableApplication {

	private FmkLogger fmkLogger = new FmkLogger(RunnableApplication.class);
	
	private void addParameterValue(String tag, String value, Map<String, Object> map, List<String> tags) throws ParseException {
		if (tags.contains(tag)) {
			if (tag.equals("dateFrom") ||
				tag.equals("dateTo")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
				map.put(tag, sdf.parse(value));
			} else {
				map.put(tag, value);
			}
		}
	}
	
	@Override
	protected void handleParameters(String[] params) {
		// TODO Auto-generated method stub
		try {
			FmkJpaPagingItemReader<?> svdBatchPrintFreshSummonsItemReader = BatchContext.getJpaPagingItemReader("svdBatchPrintFreshSummonsReader");
			Map<String, Object> svdBatchPrintFreshSummonsParameterValues = new HashMap<String, Object>();
			
			List<String> svdBatchPrintFreshSummonsTags = new ArrayList<String>();

	
			PrintFreshSummonStepExecutionListener stepExecutionListener = BatchContext.getBean("svdBatchPrintFreshSummonsStepExecutionListener", PrintFreshSummonStepExecutionListener.class);
			stepExecutionListener.setJobNo(Integer.parseInt(params[1]));
			
			for (int i=2; i<params.length; i+=2) {
				fmkLogger.info("parameterValues name "+params[i]+" value "+params[i+1]);
				addParameterValue(params[i], params[i+1], svdBatchPrintFreshSummonsParameterValues, svdBatchPrintFreshSummonsTags);
				}
			svdBatchPrintFreshSummonsItemReader.setParameterValues(svdBatchPrintFreshSummonsParameterValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
