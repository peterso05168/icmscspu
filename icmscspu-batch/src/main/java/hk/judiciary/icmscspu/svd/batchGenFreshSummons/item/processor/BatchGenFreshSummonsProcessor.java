package hk.judiciary.icmscspu.svd.batchGenFreshSummons.item.processor;

import java.util.Date;

import hk.judiciary.fmk.batch.core.BatchContext;
import hk.judiciary.fmk.batch.item.processor.AbstractItemProcessor;
import hk.judiciary.fmk.ejb.webservice.WSClientHandler;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.Case;
import hk.judiciary.icms.model.dao.entity.CspuJobLogDtl;
import hk.judiciary.icms.model.dao.entity.SummonNoti;
import hk.judiciary.icmscspu.svd.batchGenFreshSummons.item.GenFreshSummonStepExecutionListener;
import hk.judiciary.icmscspu.svd.dao.CspuJobLogDAO;
import hk.judiciary.icmscspu.svd.dao.CspuJobLogDtlDAO;
import hk.judiciary.icmscspu.svd.dao.CspuJobLogParamDAO;
import hk.judiciary.icmscspu.svd.dao.SummonNotiDAO;
import hk.judiciary.icmssvd.model.snGeneration.biz.dto.PrintFreshSummonsResultDTO;
import hk.judiciary.icmssvd.model.webservice.SnGenerationService;

public class BatchGenFreshSummonsProcessor extends AbstractItemProcessor<Case, Case>{
	
	private FmkLogger fmkLogger = new FmkLogger(BatchGenFreshSummonsProcessor.class);
	private CspuJobLogDAO cspuJobLogDAO;
	private CspuJobLogParamDAO cspuJobLogParamDAO;
	private CspuJobLogDtlDAO cspuJobLogDtlDAO;
	private SummonNotiDAO summonNotiDAO;
	private GenFreshSummonStepExecutionListener stepExecutionListener;

	
	@Override
	public Case process(Case arg0) throws Exception {
		fmkLogger.info("process "+arg0.getCaseId());
		stepExecutionListener = BatchContext.getBean("svdBatchGenFreshSummonsStepExecutionListener", GenFreshSummonStepExecutionListener.class);
		
		Integer seqNo = stepExecutionListener.getSeqIndex();
		if (seqNo.equals(1)){
			//Integer cspuJobLogId = cspuJobLogDAO.save(stepExecutionListener.getCspuJobLog());
			//stepExecutionListener.setCspuJobLogId(cspuJobLogId);
			//cspuJobLogParamDAO.save(stepExecutionListener.getCspuJobLogParams());
		}
		
		//DEPENDS ON CASE TYPE WILL BE CHANGED
		String[] copyTypeList = {"D", "C"};
		
		String endpoint = WSClientHandler.getEndpointByWSConfigCode("SVDWS");
		System.out.println(endpoint);
		SnGenerationService snGenerationService = null;
		try {
			snGenerationService = (SnGenerationService) WSClientHandler.handleMessage(BatchContext.getJudiciaryUser(), SnGenerationService.class, endpoint);
		} catch (Exception e) {
			e.printStackTrace();
		}

		fmkLogger.info("process "+ arg0.getCaseId());
		
		//fmkLogger.info("stepExecutionListener "+stepExecutionListener.getJobNo());
		//fmkLogger.info("totalPageCount "+stepExecutionListener.getTotalPageCount());
		//stepExecutionListener.setTotalPageCount(stepExecutionListener.getTotalPageCount()+1);
		Integer jobNo = stepExecutionListener.getJobNo();
		Integer totalCaseCount = stepExecutionListener.getTotalCaseCount();
		totalCaseCount++;
		Integer totalPageCount = stepExecutionListener.getTotalPageCount();
		
		fmkLogger.info("JobNo "+jobNo+" CaseCount "+totalCaseCount+" PageCount "+totalPageCount);			
		fmkLogger.info("Case ID "+ arg0.getCaseId()+" Summon Notice Issue Date "+ arg0.getSummonNoti().getSummonNotiIssueDate());
		
		PrintFreshSummonsResultDTO resultDTO = new PrintFreshSummonsResultDTO();
		CspuJobLogDtl cspuJobLogDtl = new CspuJobLogDtl();
		cspuJobLogDtl.setCspuJobLog(stepExecutionListener.getCspuJobLog());
		cspuJobLogDtl.setSeqNo(seqNo);
		cspuJobLogDtl.setVcase(arg0);
		cspuJobLogDtl.setDocGenStatus("U");
		
		for (int i = 0; i < copyTypeList.length; i++) {
			// Only perform generation and filing to ECF/CFS when summon notice issue date is null
			if (arg0.getSummonNoti().getSummonNotiIssueDate() == null) {
				resultDTO = snGenerationService.batchGenSummons(arg0.getCaseId(), 
						copyTypeList[i],
						arg0.getCaseType().getCaseTypeCd(),
						jobNo.toString(), 
						stepExecutionListener.getIsReservice(),
						totalCaseCount.toString(), 
						totalPageCount.toString());
								
				if (resultDTO.getReturnCode().equals("SRV0700")) {				
					//UPDATE PAGE INFO
					stepExecutionListener.setTotalCaseCount(totalCaseCount);
					stepExecutionListener.setTotalPageCount(resultDTO.getTotalPageCount());
					
					//TODO: GENERATE SERVICE REQUEST TO SVD
					boolean generateServiceRequest = true;
					
					//UPDATE SUMMON NOTICE ISSUE DATE AND JOB VARIABLE IF THE ABOVE REQUEST IS SUCCESSFUL					
					if (generateServiceRequest) {
						//UPDATE SUMMON NOTICE ISSUE DATE IF ALL SERVICE CALL ARE COMPLETED SUCCESSFULLY
						SummonNoti summonNoti = summonNotiDAO.find(SummonNoti.class, arg0.getSummonNoti().getSummonNotiId());
						summonNoti.setSummonNotiIssueDate(stepExecutionListener.getPrintDate());
						
						try {
							//summonNotiDAO.update(summonNoti);
						} catch (Exception e) {
							e.printStackTrace();
						}
						cspuJobLogDtl.setSeqNo(stepExecutionListener.getSeqIndex());
						cspuJobLogDtl.setDocGenStatus("S");
						
					}else {
						fmkLogger.info("Case ID "+ arg0.getCaseId()+" with exception "+ resultDTO.getReturnCode());
						//FIXME: REPLACE THE PARAMETER AFTER SERVICE REQEUST IS DONE.
						cspuJobLogDtl.setDocGenFailureLog("GENERATE SERVICE REQUEST FAIL MSG FIELD IN CORRESPONDING DTO");
						cspuJobLogDtl.setDocGenStatus("F");
					}
					
				}else {
					fmkLogger.info("Case ID "+ arg0.getCaseId()+" with exception "+ resultDTO.getReturnCode());
					cspuJobLogDtl.setDocGenFailureLog(resultDTO.getFailureMsg());
					cspuJobLogDtl.setDocGenStatus("F");
				}
				try {
					//cspuJobLogDtlDAO.save(cspuJobLogDtl);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}				
		}
					
		stepExecutionListener.setTotalCaseCount(totalCaseCount);
		stepExecutionListener.setTotalPageCount(totalPageCount);
		
			
		//PRINT JOB VARIABLES, NO LONGER USES
//			String caseNo = vcase.getCompsCourt().getCompsCourtCd() +
//					vcase.getCaseType().getCaseTypeCd() + " " +
//					vcase.getCaseSerNo() + "/" +
//					vcase.getCaseYr();
//			
//			stepExecutionListener.getCaseNos().add(caseNo);
//			stepExecutionListener.getCaseIds().add(vcase.getCaseId());	
		return arg0;
	
	}
	
	public void setSummonNotiDAO(SummonNotiDAO summonNotiDAO) {
		this.summonNotiDAO = summonNotiDAO;
	}

	public void setCspuJobLogDtlDAO(CspuJobLogDtlDAO cspuJobLogDtlDAO) {
		this.cspuJobLogDtlDAO = cspuJobLogDtlDAO;
	}

	public void setCspuJobLogDAO(CspuJobLogDAO cspuJobLogDAO) {
		this.cspuJobLogDAO = cspuJobLogDAO;
	}

	public void setCspuJobLogParamDAO(CspuJobLogParamDAO cspuJobLogParamDAO) {
		this.cspuJobLogParamDAO = cspuJobLogParamDAO;
	}
	
}
