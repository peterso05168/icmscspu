package hk.judiciary.icmscspu.svd.dao.codeTable;

import java.util.List;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.icms.model.dao.entity.CompsCourt;
import hk.judiciary.icmscspu.svd.biz.dto.CodeTableRetrieveCriteriaDTO;

public class CompsCourtDAO extends CommonDAO<CompsCourt> {

	public static class Entities {
		public static String COURT_LVL_TYPE_NAME = "courtLvlType";
	}
	public static class Fields {
		public static final String ENTITY_ID_NAME = "compsCourtId";
		public static final String ENTITY_CD_NAME = "compsCourtCd";
	}
	
	public static final String NAME = "compsCourtDAO";
	
	public CompsCourtDAO() {
		super();
	}
	
	public List<CompsCourt> retrieve(CodeTableRetrieveCriteriaDTO retrieveCriteriaDTO) throws InstantiationException, IllegalAccessException {
		return commonRetrieve(retrieveCriteriaDTO, CompsCourt.class);
	}
}
