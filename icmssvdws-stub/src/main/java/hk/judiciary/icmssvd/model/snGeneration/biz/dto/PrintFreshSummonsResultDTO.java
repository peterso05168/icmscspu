package hk.judiciary.icmssvd.model.snGeneration.biz.dto;

public class PrintFreshSummonsResultDTO extends CommonWsDTO {
	private Integer totalPageCount;
	private boolean svdWsSuccess;
	private String failureMsg;
	
	public boolean isSvdWsSuccess() {
		return svdWsSuccess;
	}
	public void setSvdWsSuccess(boolean svdWsSuccess) {
		this.svdWsSuccess = svdWsSuccess;
	}
	public Integer getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public String getFailureMsg() {
		return failureMsg;
	}
	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}
	
}
