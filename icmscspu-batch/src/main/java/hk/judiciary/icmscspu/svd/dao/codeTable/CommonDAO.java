package hk.judiciary.icmscspu.svd.dao.codeTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import hk.judiciary.fmk.batch.dao.AbstractJpaBatchDAO;
import hk.judiciary.fmk.common.util.CommonUtil;
import hk.judiciary.fmk.ejb.dao.entity.AbstractEntity;
import hk.judiciary.icms.model.dao.entity.CourtLvlType;
import hk.judiciary.icms.model.dao.entity.CourtRmChmbr;
import hk.judiciary.icms.model.dao.entity.CourtVenue;
import hk.judiciary.icmscspu.svd.biz.dto.CodeTableRetrieveCriteriaDTO;
import hk.judiciary.icmscspu.svd.biz.dto.OrderByCriteriaDTO;

public class CommonDAO<T extends AbstractEntity> extends AbstractJpaBatchDAO<T> {

	public static class Fields {
		public static final String EFFV_START_DATE_NAME = "effvStartDate";
		public static final String EFFV_END_DATE_NAME = "effvEndDate";
		public static final String ACTIVE_FLAG_NAME = "activeFlag";
		public static final String COURT_LVL_TYPE_NAME = "courtLvlType";
		
		public static final String JOIN_COURT_LVL_TYPE_NAME = "courtLvlType";
		
		public static final String COURT_LVL_TYPE_ID_NAME = "courtLvlTypeId";
		public static final String COURT_LVL_TYPE_CD_NAME = "courtLvlTypeCd";
	}
	
	public CommonDAO() {
		super();
	}
	
	private <E extends AbstractEntity> boolean isColumnExist(String colName, E entity) {
		boolean isExist = false;
		for (Field field : entity.getClass().getDeclaredFields()) {
			if (field.getName().equals(colName)) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	private boolean isColumnExist(String colName, Class<T> entityClass) {
		boolean isExist = false;
		for (Field field : entityClass.getDeclaredFields()) {
			if (field.getName().equals(colName)) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	protected <D extends CommonRetrieveCriteriaDTO, E extends AbstractEntity> List<Predicate> appendCommonCriteriaByCriteriaBuilder(CriteriaBuilder criteriaBuilder, Root<T> entityRoot, List<Predicate> criterias, D retrieveCriteriaDTO, Class<T> entityClass) throws InstantiationException, IllegalAccessException  {
		E entity = (E) entityClass.newInstance();
		
		Predicate criteria = null;
		if (retrieveCriteriaDTO.getEffvStartDateCheck() && isColumnExist(Fields.EFFV_START_DATE_NAME, entity)) {
			GregorianCalendar gCalendar = new GregorianCalendar();
			gCalendar.setTime(new Date());
			gCalendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gCalendar.set(GregorianCalendar.MINUTE, 0);
			gCalendar.set(GregorianCalendar.SECOND, 0);
			
			Expression<Date> expDate = entityRoot.get(Fields.EFFV_START_DATE_NAME);
			
			criteria = criteriaBuilder.lessThanOrEqualTo(expDate, gCalendar.getTime());
			criterias.add(criteria); 
		}
		if (retrieveCriteriaDTO.getEffvEndDateCheck() && isColumnExist(Fields.EFFV_END_DATE_NAME, entity)) {
			GregorianCalendar gCalendar = new GregorianCalendar();
			gCalendar.setTime(new Date());
			gCalendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gCalendar.set(GregorianCalendar.MINUTE, 0);
			gCalendar.set(GregorianCalendar.SECOND, 0);
			gCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
			
			Expression<Date> expDate = entityRoot.get(Fields.EFFV_END_DATE_NAME);
			
			criteria = criteriaBuilder.greaterThanOrEqualTo(expDate, gCalendar.getTime());
			criterias.add(criteria);
		}
		if (retrieveCriteriaDTO.getActiveFlag() != null && isColumnExist(Fields.ACTIVE_FLAG_NAME, entity)) {
			criteria = criteriaBuilder.equal(entityRoot.get(Fields.ACTIVE_FLAG_NAME), retrieveCriteriaDTO.getActiveFlag());
			criterias.add(criteria);
		}
		if (retrieveCriteriaDTO.getCourtLvlType() != null && isColumnExist(Fields.COURT_LVL_TYPE_NAME, entity)) {
			Join<E, CourtLvlType> entity2CourtLvlTypeJoin = null;
			entity2CourtLvlTypeJoin = entityRoot.join(Fields.JOIN_COURT_LVL_TYPE_NAME, JoinType.INNER);
			criteria = criteriaBuilder.equal(entity2CourtLvlTypeJoin.get(Fields.COURT_LVL_TYPE_ID_NAME), retrieveCriteriaDTO.getCourtLvlType().getCourtLvlTypeId());
			criterias.add(criteria);
			if (retrieveCriteriaDTO.getCourtLvlType().getCourtLvlTypeCd() != null) {
				criteria = criteriaBuilder.equal(entity2CourtLvlTypeJoin.get(Fields.COURT_LVL_TYPE_CD_NAME), retrieveCriteriaDTO.getCourtLvlType().getCourtLvlTypeCd());
			}
			criterias.add(criteria);
		}
		
		return criterias;
	}
	
	protected <D extends CommonRetrieveCriteriaDTO> List<Order> genCommonCriteriaOrderList(CriteriaBuilder criteriaBuilder, Root<T> entityRoot, D retrieveCriteriaDTO) {
		List<Order> orders = null;
		Order order = null;
		if (retrieveCriteriaDTO.getOrderByCriterias() != null) {
			orders = new ArrayList<Order>();
			OrderByCriteriaDTO orderByCriteriaDTO = null;
			for (int i=0; i<retrieveCriteriaDTO.getOrderByCriterias().size(); i++) {
				orderByCriteriaDTO = retrieveCriteriaDTO.getOrderByCriterias().get(i);
				if ("ASC".equals(orderByCriteriaDTO.getSortOrder())) {
					order = criteriaBuilder.asc(entityRoot.get(orderByCriteriaDTO.getFieldName()));
				} else if ("DESC".equals(orderByCriteriaDTO.getSortOrder())) {
					order = criteriaBuilder.desc(entityRoot.get(orderByCriteriaDTO.getFieldName()));
				}
				if (!CommonUtil.isNull(order)) {
					orders.add(order);
				}
			}
		}
		return orders;
	}
	
	public List<T> commonRetrieve(CodeTableRetrieveCriteriaDTO retrieveCriteriaDTO, Class<T> entityClass) throws InstantiationException, IllegalAccessException {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<T> entityRoot = criteriaQuery.from(entityClass);
		criteriaQuery.select(entityRoot);
		
		List<Predicate> criterias = new ArrayList<Predicate>();
		Predicate criteria = null;
		
		String entityName = entityClass.getName();
		entityName = new StringBuilder(entityName).reverse().toString();
		entityName = entityName.substring(0, entityName.indexOf("."));
		entityName = new StringBuilder(entityName).reverse().toString();
		entityName = entityName.substring(0, 1).toLowerCase()+entityName.substring(1, entityName.length());
		
		String idName = entityName+"Id";
		String cdName1 = entityName+"Cd";
		String cdName2 = "cd"; // Entity: ChrgType, ChrgAppDateOfOccType, CourtPrfxType, FpAmt, FpAppNatType, FpCost, GendrType, OrgIdType, PersonIdType, SodVarType, TrafficLbltyType
		// Last 2 = Cd: CourtFacReqsStatus, RelshpType
		
		String seqNoName = "seqNo";
		String descName1 = entityName+"Desc";
		String descName2 = "descEng"; // Entity: CourtRmAlias, HrnTimeWgt
		String descName3 = entityName+"Name"; // Entity: JjoRolePreced, Ws
		
		boolean autoOrdering = false;
		
		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);
			
			if (field.getName() != null && field.getName().equals(idName)) {
				if (retrieveCriteriaDTO.getId() != null) {
					criteria = criteriaBuilder.equal(entityRoot.get(field.getName()), retrieveCriteriaDTO.getId());
					criterias.add(criteria);
				}
				if (retrieveCriteriaDTO.getIds() != null) {
					criteria = entityRoot.get(field.getName()).in(retrieveCriteriaDTO.getIds());
					criterias.add(criteria);
				}
			} else if (field.getName().equals(cdName1) ||
					   field.getName().equals(cdName2) ||
					   field.getName().substring(field.getName().length()-2, field.getName().length()).equals("Cd")) {
				if (retrieveCriteriaDTO.getCode() != null) {
					criteria = criteriaBuilder.equal(entityRoot.get(field.getName()), retrieveCriteriaDTO.getCode());
					criterias.add(criteria);
				}
				if (retrieveCriteriaDTO.getCodes() != null) {
					criteria = entityRoot.get(field.getName()).in(retrieveCriteriaDTO.getCodes());
					criterias.add(criteria);
				}
			}
		}
		
		criterias = this.appendCommonCriteriaByCriteriaBuilder(criteriaBuilder, entityRoot, criterias, retrieveCriteriaDTO, entityClass);
		
		if (criterias.size() > 0) {
			criteria = criteriaBuilder.and((Predicate[])criterias.toArray(new Predicate[0]));
			criteriaQuery.where(criteria);
		}	
		
		if (retrieveCriteriaDTO.getOrderByCriterias() == null) {
			autoOrdering = true;
			List<OrderByCriteriaDTO> orderByCriteriaDTOs = new ArrayList<OrderByCriteriaDTO>();
			// SEQ_NO
			if (isColumnExist(seqNoName, entityClass)) {
				orderByCriteriaDTOs.add(newOrderByCriteriaDTO(seqNoName, "ASC"));
			}
			// DESC_ENG
			// special condition which name is not in standard format
			if (entityClass.getName().equals(CourtVenue.class.getName())) {
//				orderByCriteriaDTOs.add(newOrderByCriteriaDTO(CourtVenueMCDAO.Fields.COURT_VENUE_NAME_NAME, "ASC"));
			} else if (entityClass.getName().equals(CourtRmChmbr.class.getName())) {
//				orderByCriteriaDTOs.add(newOrderByCriteriaDTO(CourtRoomChamberMCDAO.Fields.COURT_ROOM_CHAMBER_NAME, "ASC"));
			} else {
				if (isColumnExist(descName1, entityClass)) {
					orderByCriteriaDTOs.add(newOrderByCriteriaDTO(descName1, "ASC"));
				} else if (isColumnExist(descName2, entityClass)) {
					orderByCriteriaDTOs.add(newOrderByCriteriaDTO(descName2, "ASC"));
				} else if (isColumnExist(descName3, entityClass)) {
					orderByCriteriaDTOs.add(newOrderByCriteriaDTO(descName3, "ASC"));
				}
			}
			retrieveCriteriaDTO.setOrderByCriterias(orderByCriteriaDTOs);
		}
		
		List<Order> orders = this.genCommonCriteriaOrderList(criteriaBuilder, entityRoot, retrieveCriteriaDTO);
		if (orders != null && orders.size() > 0) {
			criteriaQuery.orderBy(orders);
		}
		
		if (autoOrdering) {
			retrieveCriteriaDTO.setOrderByCriterias(null);
		}
		
		TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	private OrderByCriteriaDTO newOrderByCriteriaDTO(String fieldName, String sortOrder) {
		OrderByCriteriaDTO orderByCriteriaDTO = new OrderByCriteriaDTO();
		orderByCriteriaDTO.setFieldName(fieldName);
		orderByCriteriaDTO.setSortOrder(sortOrder);
		return orderByCriteriaDTO;
	}
}
