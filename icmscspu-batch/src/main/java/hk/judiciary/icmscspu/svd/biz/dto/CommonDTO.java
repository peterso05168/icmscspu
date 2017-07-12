package hk.judiciary.icmscspu.svd.biz.dto;

import java.util.Date;

import hk.judiciary.fmk.ejb.biz.dto.AbstractDTO;

public class CommonDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	
	private Date effvStartDate;
	private Date effvEndDate;
	private String activeFlag;
	private String createBy;
	private Date createDt;
	private String lastUpdBy;
	private Date lastUpdDt;
	private String versionTime;
	private String versionNanos;
	private String recordStatus;
	private String dtoId;
	public Date getEffvStartDate() {
		return effvStartDate;
	}
	public void setEffvStartDate(Date effvStartDate) {
		this.effvStartDate = effvStartDate;
	}
	public Date getEffvEndDate() {
		return effvEndDate;
	}
	public void setEffvEndDate(Date effvEndDate) {
		this.effvEndDate = effvEndDate;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getLastUpdBy() {
		return lastUpdBy;
	}
	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}
	public Date getLastUpdDt() {
		return lastUpdDt;
	}
	public void setLastUpdDt(Date lastUpdDt) {
		this.lastUpdDt = lastUpdDt;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getVersionTime() {
		return versionTime;
	}
	public void setVersionTime(String versionTime) {
		this.versionTime = versionTime;
	}
	public String getVersionNanos() {
		return versionNanos;
	}
	public void setVersionNanos(String versionNanos) {
		this.versionNanos = versionNanos;
	}
	public String getDtoId() {
		return dtoId;
	}
	public void setDtoId(String dtoId) {
		this.dtoId = dtoId;
	}
}
