package hk.judiciary.icmscspu.svd.batchGenFreshSummons.item;

import org.springframework.batch.core.ItemProcessListener;

import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.Case;

public class GenFreshSummonsItemProcessListener implements ItemProcessListener<Case, Case> {

	private FmkLogger fmkLogger = new FmkLogger(GenFreshSummonsItemProcessListener.class);
	
	@Override
	public void beforeProcess(Case arg0) {
		// TODO Auto-generated method stub
		fmkLogger.info("beforeProcess");
	}

	@Override
	public void afterProcess(Case arg0, Case arg1) {
		// TODO Auto-generated method stub
		fmkLogger.info("afterProcess");
	}

	@Override
	public void onProcessError(Case arg0, Exception arg1) {
		// TODO Auto-generated method stub
		fmkLogger.info("onProcessError");
	}
}
