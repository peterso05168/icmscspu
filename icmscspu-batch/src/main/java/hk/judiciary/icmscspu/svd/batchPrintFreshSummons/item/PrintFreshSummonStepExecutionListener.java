package hk.judiciary.icmscspu.svd.batchPrintFreshSummons.item;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

import hk.judiciary.fmk.batch.core.BatchContext;
import hk.judiciary.fmk.batch.core.DefaultStepExecutionListener;
import hk.judiciary.fmk.ejb.webservice.WSClientHandler;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icmssvd.model.snGeneration.biz.dto.PrintCheckListAndCoverSheetResultDTO;
import hk.judiciary.icmssvd.model.webservice.SnGenerationService;

public class PrintFreshSummonStepExecutionListener extends DefaultStepExecutionListener {

	private FmkLogger fmkLogger = new FmkLogger(PrintFreshSummonStepExecutionListener.class);
	
	private Integer jobNo;
	private Integer totalPageCount;
	private Integer totalCaseCount;
	private List<String> caseNos = new ArrayList<String>();
	private List<Integer> caseIds = new ArrayList<Integer>();
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		// TODO Auto-generated method stub
		fmkLogger.info("afterStep");
		fmkLogger.info("caseNos "+caseNos);
		if (arg0.getStepName().equals("lookupComprisingCourtStep")) {
			return new ExitStatus("NEXT_STEP");
		}else if (arg0.getStepName().equals("svdBatchPrintFreshSummonsStep")) {
			String endpoint = WSClientHandler.getEndpointByWSConfigCode("SVDWS");
			SnGenerationService snGenerationService = null;
			try {
				snGenerationService = (SnGenerationService) WSClientHandler.handleMessage(BatchContext.getJudiciaryUser(), SnGenerationService.class, endpoint);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (caseNos.size() > 0) {
				PrintCheckListAndCoverSheetResultDTO resultDTO = snGenerationService.batchGenCover(caseNos);
				OutputStream out;
				try {
					//FIXME FOR LOCAL TESTING, WILL BE REMOVED LATER - START
					out = new FileOutputStream("C:/TempDoc/Cover.pdf");
					out.write(resultDTO.getDocument());
					out.close();
					//FIXME FOR LOCAL TESTING, WILL BE REMOVED LATER - END
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			if (caseIds.size() > 0) {
				PrintCheckListAndCoverSheetResultDTO resultDTO = snGenerationService.batchGenCheckList(caseIds, "CHECKLIST_OF_NON_SERVICE");	
				OutputStream out;
				try {
					//FOR LOCAL TESTING - START
					out = new FileOutputStream("C:/TempDoc/Checklist.pdf");
					out.write(resultDTO.getDocument());
					out.close();
					//FOR LOCAL TESTIN - END
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		// TODO Auto-generated method stub
		fmkLogger.info("beforeStep JobNo "+getJobNo());
	}

	public Integer getJobNo() {
		return jobNo;
	}

	public void setJobNo(Integer jobNo) {
		this.jobNo = jobNo;
	}

	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Integer getTotalCaseCount() {
		return totalCaseCount;
	}

	public void setTotalCaseCount(Integer totalCaseCount) {
		this.totalCaseCount = totalCaseCount;
	}

	public FmkLogger getFmkLogger() {
		return fmkLogger;
	}

	public void setFmkLogger(FmkLogger fmkLogger) {
		this.fmkLogger = fmkLogger;
	}

	public List<String> getCaseNos() {
		return caseNos;
	}

	public void setCaseNos(List<String> caseNos) {
		this.caseNos = caseNos;
	}
	
	public List<Integer> getCaseIds() {
		return caseIds;
	}

	public void setCaseIds(List<Integer> caseIds) {
		this.caseIds = caseIds;
	}	
}
