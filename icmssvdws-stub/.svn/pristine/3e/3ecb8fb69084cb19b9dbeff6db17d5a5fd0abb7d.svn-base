package hk.judiciary.reference.webservice.project;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import hk.judiciary.reference.model.project.biz.dto.ProjectSearchResultDTO;

@WebService(targetNamespace = "http://project.webservice.reference.judiciary.hk/")
@SOAPBinding(style = Style.RPC)
public interface ProjectService {

    @WebResult(name = "ProjectSearchResultDTOList", targetNamespace = "http://project.webservice.reference.judiciary.hk/")
	@WebMethod List<ProjectSearchResultDTO> getProject (
				@WebParam(name = "active") boolean active); //throws WebServiceException;
}
