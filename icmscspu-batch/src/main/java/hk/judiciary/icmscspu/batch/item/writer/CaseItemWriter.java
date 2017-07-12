package hk.judiciary.icmscspu.batch.item.writer;

import java.util.List;

import hk.judiciary.fmk.batch.item.writer.FmkJpaItemWriter;
import hk.judiciary.icms.model.dao.entity.Case;


public class CaseItemWriter extends FmkJpaItemWriter<Case> {
	
	
	@Override
	public void write(List<? extends Case> lstCase){
		
		for (Case caze:lstCase){
			System.out.println(caze.getCaseId());
		}
		
	}

}
