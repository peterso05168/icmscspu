package hk.judiciary.icmscspu.svd.batchGenFreshSummons.core;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hk.judiciary.fmk.batch.core.BatchContext;
import hk.judiciary.fmk.batch.core.DefaultRunnableApplication;
import hk.judiciary.fmk.batch.item.reader.FmkJpaPagingItemReader;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.CspuDocGenJob;
import hk.judiciary.icms.model.dao.entity.CspuJobLog;
import hk.judiciary.icmscspu.svd.batchGenFreshSummons.item.GenFreshSummonStepExecutionListener;
import hk.judiciary.icmscspu.svd.dao.CspuDocGenJobDAO;
import hk.judiciary.icmscspu.svd.dao.CspuJobLogDAO;

public class RunnableApplication extends DefaultRunnableApplication {

	private FmkLogger fmkLogger = new FmkLogger(RunnableApplication.class);
	private GenFreshSummonStepExecutionListener stepExecutionListener = BatchContext.getBean("svdBatchGenFreshSummonsStepExecutionListener", GenFreshSummonStepExecutionListener.class);
	
	private void addParameterValue(String tag, String value, Map<String, Object> map, List<String> tags) throws ParseException {
		if (tags.contains(tag)) {
			if (tag.equals("cspuDocGenJobCd")) {
				stepExecutionListener.setCspuDocGenJobCd(value);
			}else if (tag.equals("isReservice")) {
				stepExecutionListener.setIsReservice(Boolean.valueOf(value));
			}else{
				map.put(tag, value);
			}			
		}
	}
	
	@Override
	protected void handleParameters(String[] params) {
		try {
			FmkJpaPagingItemReader<?> svdBatchGenFreshSummonsItemReader = BatchContext.getJpaPagingItemReader("svdBatchGenFreshSummonsReader");
			Map<String, Object> svdBatchGenFreshSummonsParameterValues = new HashMap<String, Object>();
			
			List<String> svdBatchGenFreshSummonsTags = new ArrayList<String>();
			svdBatchGenFreshSummonsTags.add("cspuDocGenJobCd");
			svdBatchGenFreshSummonsTags.add("caseTypeCd");
			svdBatchGenFreshSummonsTags.add("compsCourtCd");
			
			stepExecutionListener.setJobNo(Integer.parseInt(params[1]));
			
			for (int i=2; i<params.length; i+=2) {
				fmkLogger.info("parameterValues name "+params[i]+" value "+params[i+1]);
				addParameterValue(params[i], params[i+1], svdBatchGenFreshSummonsParameterValues, svdBatchGenFreshSummonsTags);
			}
			stepExecutionListener.setJobParams(svdBatchGenFreshSummonsParameterValues);
			stepExecutionListener.createNewJobLog();
			svdBatchGenFreshSummonsItemReader.setParameterValues(svdBatchGenFreshSummonsParameterValues);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
