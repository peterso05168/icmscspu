package hk.judiciary.icmscspu.batch.item.processor;

import java.util.Date;

import hk.judiciary.fmk.batch.item.processor.AbstractItemProcessor;
import hk.judiciary.icms.model.dao.entity.Case;


public class CaseItemProcessor extends AbstractItemProcessor<Case, Case> {

	@Override
	public Case process(Case courtCase) throws Exception {
//		CaseDAO dao = BatchContext.getDAO("caseDAO", CaseDAO.class);
//		courtCase = dao.findCaseByCaseNo("SPEA4/2015");
//		courtCase.setFilingDate(new Date());
		courtCase.setPreviousVersion(courtCase.getVersion());
		
//		CourtCase c = dao.find(CourtCase.class, 2);
//		c.setHearingFirstFreeDate(new java.util.Date());
//		c.setPreviousVersion(c.getVersion());
//		dao.persist(c);
		
		return courtCase;
	}

}
