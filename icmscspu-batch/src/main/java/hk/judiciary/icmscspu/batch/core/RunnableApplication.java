package hk.judiciary.icmscspu.batch.core;

import hk.judiciary.fmk.batch.core.BatchContext;
import hk.judiciary.fmk.batch.core.DefaultRunnableApplication;
import hk.judiciary.fmk.batch.item.reader.FmkJpaPagingItemReader;

import java.util.HashMap;
import java.util.Map;

public class RunnableApplication extends DefaultRunnableApplication {

	@Override
	protected void handleParameters(String[] params) {
		FmkJpaPagingItemReader<?> itemReader = BatchContext
				.getJpaPagingItemReader();
		Map<String, Object> parameterValues = new HashMap<String, Object>();
		parameterValues.put(params[0], Integer.parseInt(params[1]));
		itemReader.setParameterValues(parameterValues);
	}

}
