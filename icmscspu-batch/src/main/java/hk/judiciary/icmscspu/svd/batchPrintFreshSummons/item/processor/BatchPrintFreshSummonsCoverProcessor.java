package hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item.processor;

import hk.judiciary.fmk.batch.core.BatchContext;
import hk.judiciary.fmk.batch.item.processor.AbstractItemProcessor;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item.PrintFreshSummonStepExecutionListener;

public class BatchPrintFreshSummonsCoverProcessor extends AbstractItemProcessor {

	private FmkLogger fmkLogger = new FmkLogger(BatchPrintFreshSummonsCoverProcessor.class);
	
	@Override
	public Object process(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		PrintFreshSummonStepExecutionListener stepExecutionListener = BatchContext.getBean("svdBatchPrintFreshSummonsStepExecutionListener", PrintFreshSummonStepExecutionListener.class);
		
		fmkLogger.info("BatchPrintFreshSummonsCoverProcessor "+stepExecutionListener.getCaseNos());
		return null;
	}

}
