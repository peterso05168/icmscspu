package hk.judiciary.reference.model.project.biz.dto;

import hk.judiciary.fmk.ejb.biz.dto.AbstractDTO;

/**
 * 
 * @version $Revision: 53 $ $Date: 2017-02-27 11:17:35 +0800 (Mon, 27 Feb 2017) $
 * @author $Author: michalieylho@judiciary.gov.hk $
 */
public class ProjectSearchResultDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private int projectId;

	private String projectCode;

	private String projectName;

	private Boolean active;
	

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + projectId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectSearchResultDTO other = (ProjectSearchResultDTO) obj;
		if (projectId != other.projectId)
			return false;
		return true;
	}
}
