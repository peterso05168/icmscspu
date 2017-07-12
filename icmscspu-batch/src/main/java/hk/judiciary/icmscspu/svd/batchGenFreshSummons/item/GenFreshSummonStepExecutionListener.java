package hk.judiciary.icmscspu.svd.batchGenFreshSummons.item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

import hk.judiciary.fmk.batch.core.BatchContext;
import hk.judiciary.fmk.batch.core.DefaultStepExecutionListener;
import hk.judiciary.fmk.common.security.user.InternalUserPropertiesConstant;
import hk.judiciary.fmk.logging.FmkLogger;
import hk.judiciary.icms.model.dao.entity.CspuDocGenJob;
import hk.judiciary.icms.model.dao.entity.CspuJobLog;
import hk.judiciary.icms.model.dao.entity.CspuJobLogParam;
import hk.judiciary.icmscspu.svd.biz.dto.CspuDocGenJobRetrieveCriteriaDTO;
import hk.judiciary.icmscspu.svd.dao.CspuDocGenJobDAO;
import hk.judiciary.icmscspu.svd.dao.CspuJobLogDAO;
import hk.judiciary.icmscspu.svd.dao.CspuJobLogParamDAO;
import hk.judiciary.icmscspu.svd.enumObject.JobExecStatusEnum;

public class GenFreshSummonStepExecutionListener extends DefaultStepExecutionListener {

	private FmkLogger fmkLogger = new FmkLogger(GenFreshSummonStepExecutionListener.class);
	
	private Integer jobNo;
	private Integer totalPageCount;
	private Integer totalCaseCount;
	private Boolean isReservice = false;
	private CspuDocGenJobDAO cspuDocGenJobDAO;
	private CspuJobLogDAO cspuJobLogDAO;
	private CspuJobLogParamDAO cspuJobLogParamDAO;
	private Integer cspuJobLogId;
	private String cspuDocGenJobCd;
	private List<String> caseNos = new ArrayList<String>();
	private Date printDate;
	private Map<String, Object> jobParams;
	private Integer seqIndex;
	private CspuJobLog cspuJobLog;
	private List<CspuJobLogParam> cspuJobLogParams;
	// test
	private List<String> compsCourtCds = new ArrayList<String>();
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		// TODO Auto-generated method stub
//		fmkLogger.info("afterStep");
//		fmkLogger.info("caseNos "+caseNos);
//		if (arg0.getStepName().equals("lookupComprisingCourtStep")) {
//			return new ExitStatus("NEXT_STEP");
//		}else if (arg0.getStepName().equals("svdBatchPrintFreshSummonsStep")) {
//			String endpoint = WSClientHandler.getEndpointByWSConfigCode("SVDWS");
//			SnGenerationService snGenerationService = null;
//			try {
//				snGenerationService = (SnGenerationService) WSClientHandler.handleMessage(BatchContext.getJudiciaryUser(), SnGenerationService.class, endpoint);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if (caseNos.size() > 0) {
//				PrintCheckListAndCoverSheetResultDTO resultDTO = snGenerationService.batchGenCover(caseNos);
//				OutputStream out;
//				try {
//					out = new FileOutputStream("C:/TempDoc/Cover.pdf");
//					out.write(resultDTO.getDocument());
//					out.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//			if (caseIds.size() > 0) {
//				PrintCheckListAndCoverSheetResultDTO resultDTO = snGenerationService.batchGenCheckList(caseIds, "CHECKLIST_OF_NON_SERVICE");	
//				OutputStream out;
//				try {
//					out = new FileOutputStream("C:/TempDoc/Checklist.pdf");
//					out.write(resultDTO.getDocument());
//					out.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//			if(compsCourtCds.size() > 1) {
//				compsCourtCds.remove(0);
//				caseNos.clear();
//				caseIds.clear();
//	            return new ExitStatus("CONTINUE");
//	        }else {
//	            return new ExitStatus("FINISHED");
//	        }
//		}
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		
	}

	public void createNewJobLog() {
		fmkLogger.info("createNewJobLog JobNo "+getJobNo());
		// Assign same date for all printed document
		printDate = new Date();
		seqIndex = 0;
		
		if (cspuDocGenJobDAO != null) {
			// retrieve configuration
			CspuDocGenJobRetrieveCriteriaDTO retrieveCriteriaDTO = new CspuDocGenJobRetrieveCriteriaDTO();
			retrieveCriteriaDTO.setCspuDocGenJobCd(cspuDocGenJobCd);
			List<CspuDocGenJob> cspuDocGenJobs = cspuDocGenJobDAO.retrieve(retrieveCriteriaDTO);
			
			CspuJobLog cspuJobLog = new CspuJobLog();
			cspuJobLog.setCspuDocGenJob(cspuDocGenJobs.get(0));
			cspuJobLog.setExecDate(printDate);
			cspuJobLog.setExecBy((String)BatchContext.getJudiciaryUser().getInternalUserProperty(InternalUserPropertiesConstant.LOGIN_NAME));
			cspuJobLog.setExecStatus(JobExecStatusEnum.SUCCESS.getCode());
//			if (cspuJobLogDAO != null) {
//				cspuJobLogId = cspuJobLogDAO.save(cspuJobLog);
//			}
			this.cspuJobLog = cspuJobLog;
			
			List<CspuJobLogParam> cspuJobLogParams = null;
			CspuJobLogParam cspuJobLogParam = null;
			int seqNo = 0;
			String classType = null;
			Object paramValue = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
			if (jobParams != null && jobParams.keySet().size() > 0) {
				cspuJobLogParams = new ArrayList<CspuJobLogParam>();
				for (String paramName : jobParams.keySet()) {
					cspuJobLogParam = new CspuJobLogParam();
					cspuJobLogParam.setCspuJobLog(cspuJobLog);
					seqNo++;
					cspuJobLogParam.setSeqNo(seqNo);
					cspuJobLogParam.setParamName(paramName);
					classType = jobParams.get(paramName).getClass().getTypeName();
					classType = new StringBuilder(classType).reverse().toString();
					classType = classType.substring(0, classType.indexOf("."));
					classType = new StringBuilder(classType).reverse().toString();
					cspuJobLogParam.setParamType(classType);
					paramValue = jobParams.get(paramName);
					if (classType.equals("Date")) {
						cspuJobLogParam.setParamValue(sdf.format((Date)paramValue));
					} else {
						cspuJobLogParam.setParamValue(paramValue.toString());
					}
					cspuJobLogParams.add(cspuJobLogParam);
				}
//				if (cspuJobLogParamDAO != null && cspuJobLogParams.size() > 0) {
//					cspuJobLogParamDAO.save(cspuJobLogParams);
//				}
				this.cspuJobLogParams = cspuJobLogParams;
			}
		}		
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

	public List<String> getCompsCourtCds() {
		return compsCourtCds;
	}

	public void setCompsCourtCds(List<String> compsCourtCds) {
		this.compsCourtCds = compsCourtCds;
	}

	public void setCspuDocGenJobDAO(CspuDocGenJobDAO cspuDocGenJobDAO) {
		this.cspuDocGenJobDAO = cspuDocGenJobDAO;
	}

	public void setCspuJobLogDAO(CspuJobLogDAO cspuJobLogDAO) {
		this.cspuJobLogDAO = cspuJobLogDAO;
	}

	public Integer getCspuJobLogId() {
		return cspuJobLogId;
	}

	public void setCspuJobLogId(Integer cspuJobLogId) {
		this.cspuJobLogId = cspuJobLogId;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setJobParams(Map<String, Object> jobParams) {
		this.jobParams = jobParams;
	}

	public void setCspuJobLogParamDAO(CspuJobLogParamDAO cspuJobLogParamDAO) {
		this.cspuJobLogParamDAO = cspuJobLogParamDAO;
	}
	
	public Integer getSeqIndex() {
		seqIndex++;
		return seqIndex;
	}

	public CspuJobLog getCspuJobLog() {
		return cspuJobLog;
	}

	public List<CspuJobLogParam> getCspuJobLogParams() {
		return cspuJobLogParams;
	}
	
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public String getCspuDocGenJobCd() {
		return cspuDocGenJobCd;
	}

	public void setCspuDocGenJobCd(String cspuDocGenJobCd) {
		this.cspuDocGenJobCd = cspuDocGenJobCd;
	}

	public Boolean getIsReservice() {
		return isReservice;
	}

	public void setIsReservice(Boolean isReservice) {
		this.isReservice = isReservice;
	}
	
	
}
