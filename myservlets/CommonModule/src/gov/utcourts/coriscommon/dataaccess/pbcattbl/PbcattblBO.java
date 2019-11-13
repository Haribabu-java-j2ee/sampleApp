package gov.utcourts.coriscommon.dataaccess.pbcattbl;

import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.courtscommon.dataaccess.base.BaseBO;
import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.ForeignKeyDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FromDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.GroupByCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.IntoDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.JoinDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.JoinFindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.NativeDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortOrdinalDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringLiteral;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SystemDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.UnionDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.test.TestingContainer;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Operation;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.UnionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.connection.ConnectionProperties;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dispatcher.BaseSearchDispatcher;
import gov.utcourts.courtscommon.dispatcher.PropertyFileHelper;
import gov.utcourts.courtscommon.util.TextUtil;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.List;
import java.util.HashMap;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PbcattblBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("pbcattbl", "gov.utcourts.coriscommon.dataaccess.pbcattbl.Pbcattbl");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor PBTTNAM = new FieldDescriptor(TABLE, "pbt_tnam", "setPbtTnam", 0);
	public static final FieldDescriptor PBTTID = new FieldDescriptor(TABLE, "pbt_tid", "setPbtTid", 1);
	public static final FieldDescriptor PBTOWNR = new FieldDescriptor(TABLE, "pbt_ownr", "setPbtOwnr", 2);
	public static final FieldDescriptor PBDFHGT = new FieldDescriptor(TABLE, "pbd_fhgt", "setPbdFhgt", 3);
	public static final FieldDescriptor PBDFWGT = new FieldDescriptor(TABLE, "pbd_fwgt", "setPbdFwgt", 4);
	public static final FieldDescriptor PBDFITL = new FieldDescriptor(TABLE, "pbd_fitl", "setPbdFitl", 5);
	public static final FieldDescriptor PBDFUNL = new FieldDescriptor(TABLE, "pbd_funl", "setPbdFunl", 6);
	public static final FieldDescriptor PBDFCHR = new FieldDescriptor(TABLE, "pbd_fchr", "setPbdFchr", 7);
	public static final FieldDescriptor PBDFPTC = new FieldDescriptor(TABLE, "pbd_fptc", "setPbdFptc", 8);
	public static final FieldDescriptor PBDFFCE = new FieldDescriptor(TABLE, "pbd_ffce", "setPbdFfce", 9);
	public static final FieldDescriptor PBHFHGT = new FieldDescriptor(TABLE, "pbh_fhgt", "setPbhFhgt", 10);
	public static final FieldDescriptor PBHFWGT = new FieldDescriptor(TABLE, "pbh_fwgt", "setPbhFwgt", 11);
	public static final FieldDescriptor PBHFITL = new FieldDescriptor(TABLE, "pbh_fitl", "setPbhFitl", 12);
	public static final FieldDescriptor PBHFUNL = new FieldDescriptor(TABLE, "pbh_funl", "setPbhFunl", 13);
	public static final FieldDescriptor PBHFCHR = new FieldDescriptor(TABLE, "pbh_fchr", "setPbhFchr", 14);
	public static final FieldDescriptor PBHFPTC = new FieldDescriptor(TABLE, "pbh_fptc", "setPbhFptc", 15);
	public static final FieldDescriptor PBHFFCE = new FieldDescriptor(TABLE, "pbh_ffce", "setPbhFfce", 16);
	public static final FieldDescriptor PBLFHGT = new FieldDescriptor(TABLE, "pbl_fhgt", "setPblFhgt", 17);
	public static final FieldDescriptor PBLFWGT = new FieldDescriptor(TABLE, "pbl_fwgt", "setPblFwgt", 18);
	public static final FieldDescriptor PBLFITL = new FieldDescriptor(TABLE, "pbl_fitl", "setPblFitl", 19);
	public static final FieldDescriptor PBLFUNL = new FieldDescriptor(TABLE, "pbl_funl", "setPblFunl", 20);
	public static final FieldDescriptor PBLFCHR = new FieldDescriptor(TABLE, "pbl_fchr", "setPblFchr", 21);
	public static final FieldDescriptor PBLFPTC = new FieldDescriptor(TABLE, "pbl_fptc", "setPblFptc", 22);
	public static final FieldDescriptor PBLFFCE = new FieldDescriptor(TABLE, "pbl_ffce", "setPblFfce", 23);
	public static final FieldDescriptor PBTCMNT = new FieldDescriptor(TABLE, "pbt_cmnt", "setPbtCmnt", 24);

	public PbcattblBO() {
		super(new PbcattblVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public PbcattblBO(String source) {
		super(new PbcattblVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public PbcattblBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getPbtTnam() {
		return getBaseVO().getTypeString(PBTTNAM).getValue();
	}
	
	public PbcattblBO setPbtTnam(String pbtTnam) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBTTNAM, pbtTnam);
		return this;
	}

	public int getPbtTid() {
		return getBaseVO().getTypeInteger(PBTTID).getValue();
	}
	
	public PbcattblBO setPbtTid(Integer pbtTid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBTTID, pbtTid);
		return this;
	}

	public String getPbtOwnr() {
		return getBaseVO().getTypeString(PBTOWNR).getValue();
	}
	
	public PbcattblBO setPbtOwnr(String pbtOwnr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBTOWNR, pbtOwnr);
		return this;
	}

	public int getPbdFhgt() {
		return getBaseVO().getTypeInteger(PBDFHGT).getValue();
	}
	
	public PbcattblBO setPbdFhgt(Integer pbdFhgt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBDFHGT, pbdFhgt);
		return this;
	}

	public int getPbdFwgt() {
		return getBaseVO().getTypeInteger(PBDFWGT).getValue();
	}
	
	public PbcattblBO setPbdFwgt(Integer pbdFwgt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBDFWGT, pbdFwgt);
		return this;
	}

	public String getPbdFitl() {
		return getBaseVO().getTypeString(PBDFITL).getValue();
	}
	
	public PbcattblBO setPbdFitl(String pbdFitl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBDFITL, pbdFitl);
		return this;
	}

	public String getPbdFunl() {
		return getBaseVO().getTypeString(PBDFUNL).getValue();
	}
	
	public PbcattblBO setPbdFunl(String pbdFunl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBDFUNL, pbdFunl);
		return this;
	}

	public int getPbdFchr() {
		return getBaseVO().getTypeInteger(PBDFCHR).getValue();
	}
	
	public PbcattblBO setPbdFchr(Integer pbdFchr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBDFCHR, pbdFchr);
		return this;
	}

	public int getPbdFptc() {
		return getBaseVO().getTypeInteger(PBDFPTC).getValue();
	}
	
	public PbcattblBO setPbdFptc(Integer pbdFptc) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBDFPTC, pbdFptc);
		return this;
	}

	public String getPbdFfce() {
		return getBaseVO().getTypeString(PBDFFCE).getValue();
	}
	
	public PbcattblBO setPbdFfce(String pbdFfce) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBDFFCE, pbdFfce);
		return this;
	}

	public int getPbhFhgt() {
		return getBaseVO().getTypeInteger(PBHFHGT).getValue();
	}
	
	public PbcattblBO setPbhFhgt(Integer pbhFhgt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBHFHGT, pbhFhgt);
		return this;
	}

	public int getPbhFwgt() {
		return getBaseVO().getTypeInteger(PBHFWGT).getValue();
	}
	
	public PbcattblBO setPbhFwgt(Integer pbhFwgt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBHFWGT, pbhFwgt);
		return this;
	}

	public String getPbhFitl() {
		return getBaseVO().getTypeString(PBHFITL).getValue();
	}
	
	public PbcattblBO setPbhFitl(String pbhFitl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBHFITL, pbhFitl);
		return this;
	}

	public String getPbhFunl() {
		return getBaseVO().getTypeString(PBHFUNL).getValue();
	}
	
	public PbcattblBO setPbhFunl(String pbhFunl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBHFUNL, pbhFunl);
		return this;
	}

	public int getPbhFchr() {
		return getBaseVO().getTypeInteger(PBHFCHR).getValue();
	}
	
	public PbcattblBO setPbhFchr(Integer pbhFchr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBHFCHR, pbhFchr);
		return this;
	}

	public int getPbhFptc() {
		return getBaseVO().getTypeInteger(PBHFPTC).getValue();
	}
	
	public PbcattblBO setPbhFptc(Integer pbhFptc) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBHFPTC, pbhFptc);
		return this;
	}

	public String getPbhFfce() {
		return getBaseVO().getTypeString(PBHFFCE).getValue();
	}
	
	public PbcattblBO setPbhFfce(String pbhFfce) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBHFFCE, pbhFfce);
		return this;
	}

	public int getPblFhgt() {
		return getBaseVO().getTypeInteger(PBLFHGT).getValue();
	}
	
	public PbcattblBO setPblFhgt(Integer pblFhgt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBLFHGT, pblFhgt);
		return this;
	}

	public int getPblFwgt() {
		return getBaseVO().getTypeInteger(PBLFWGT).getValue();
	}
	
	public PbcattblBO setPblFwgt(Integer pblFwgt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBLFWGT, pblFwgt);
		return this;
	}

	public String getPblFitl() {
		return getBaseVO().getTypeString(PBLFITL).getValue();
	}
	
	public PbcattblBO setPblFitl(String pblFitl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBLFITL, pblFitl);
		return this;
	}

	public String getPblFunl() {
		return getBaseVO().getTypeString(PBLFUNL).getValue();
	}
	
	public PbcattblBO setPblFunl(String pblFunl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBLFUNL, pblFunl);
		return this;
	}

	public int getPblFchr() {
		return getBaseVO().getTypeInteger(PBLFCHR).getValue();
	}
	
	public PbcattblBO setPblFchr(Integer pblFchr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBLFCHR, pblFchr);
		return this;
	}

	public int getPblFptc() {
		return getBaseVO().getTypeInteger(PBLFPTC).getValue();
	}
	
	public PbcattblBO setPblFptc(Integer pblFptc) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBLFPTC, pblFptc);
		return this;
	}

	public String getPblFfce() {
		return getBaseVO().getTypeString(PBLFFCE).getValue();
	}
	
	public PbcattblBO setPblFfce(String pblFfce) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBLFFCE, pblFfce);
		return this;
	}

	public String getPbtCmnt() {
		return getBaseVO().getTypeString(PBTCMNT).getValue();
	}
	
	public PbcattblBO setPbtCmnt(String pbtCmnt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBTCMNT, pbtCmnt);
		return this;
	}
	
	public PbcattblBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<PbcattblBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new PbcattblBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new PbcattblBO(getBaseVO());
		}
	}
	
	public PbcattblBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public PbcattblBO insert(FieldDescriptor... specificFields) throws Exception {
		return new PbcattblBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public PbcattblBO update(FieldDescriptor... specificFields) throws Exception {
		return new PbcattblBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public PbcattblBO delete() throws Exception {
		return new PbcattblBO(super.delete(getNativeDescriptor()));
	}
	
	public PbcattblBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public PbcattblBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public PbcattblBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public PbcattblBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public PbcattblBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public PbcattblBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public PbcattblBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public PbcattblBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public PbcattblBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public PbcattblBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PbcattblBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PbcattblBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public PbcattblBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public PbcattblBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public PbcattblBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PbcattblBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PbcattblBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public PbcattblBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public PbcattblBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public PbcattblBO where(FieldDescriptor findBy, Object value) {
		if (findBy instanceof FieldDescriptor && value instanceof StringLiteral) {
			setFinder(new FindDescriptor(new Expression(findBy, value)));
		} else if (findBy instanceof Expression && value instanceof Expression) {
			setFinder(new FindDescriptor(findBy));
			setFinder(new FindDescriptor((Expression) value));
		} else {
			setFinder(findBy, value, null, null);
		}
		return this;
	}
	
	public PbcattblBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public PbcattblBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public PbcattblBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public PbcattblBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public PbcattblBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.COUNT.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public int getCount() throws Exception {
		Object countValue = get(FieldOperationType.COUNT.toString());
		if (countValue instanceof String) 
			return Integer.parseInt((String) get(FieldOperationType.COUNT.toString()));
		else if (countValue instanceof Integer)
			return ((Integer) get(FieldOperationType.COUNT.toString()));
		else 
			return 0;
	}
	
	public PbcattblBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public PbcattblBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public PbcattblBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public PbcattblBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public PbcattblBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public PbcattblBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public PbcattblBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public PbcattblBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public PbcattblBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public PbcattblBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public PbcattblBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<PbcattblBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
		BaseSearchDispatcher searchDispatcher = getSearchDispatcher().search(fieldDescriptors);
		return searchDispatcher.returnNull() ? null : searchDispatcher.getResults();
	}
	
	@SuppressWarnings("rawtypes")
	public List searchAndGetResults(FieldDescriptor... fieldDescriptors) throws Exception {
		search(fieldDescriptors);
		return getSearchDispatcher().returnNull() ? null : getSearchDispatcher().getResults();
	}
	
	@SuppressWarnings("rawtypes")
	public List searchAndGetColumnResults(FieldDescriptor columnObject) throws Exception {
		BaseSearchDispatcher searchDispatcher = getSearchDispatcher().search(columnObject);
		return getSearchDispatcher().returnNull() ? null : searchDispatcher.getColumnResults(columnObject);
	}
	
	public PbcattblBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public PbcattblBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public PbcattblBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public PbcattblBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public PbcattblBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public PbcattblBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public PbcattblBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public PbcattblBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public PbcattblBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public PbcattblBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public PbcattblBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public PbcattblBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public PbcattblBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public PbcattblBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public PbcattblBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public PbcattblBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public PbcattblBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public PbcattblBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
		getBaseVO().setAttribute(fieldDescriptor.getDateFormatterKey(), dateFormat);
		getBaseVO().setPropertyListValue(SET_BY_USER, fieldDescriptor, null, dateFormat);
		return this;
	}
	
	public String getSql() throws Exception {
		return getSql(Operation.SEARCH, (FieldDescriptor[]) null);
	}
	public String getSql(Operation operation, FieldDescriptor... specificFields) throws Exception {
		switch (operation) {
			case FIND:
			case INSERT: 
			case UPDATE: 
			case DELETE: 
				return super.getSql(operation, getNativeDescriptor(), getBaseVO(), specificFields);
	
			case SEARCH: return getSearchDispatcher().getSql(specificFields);
		}
		return ".getSql operation not supported - " + operation.toString();
	}
	
	private NativeDescriptor getNativeDescriptor() {
		return (searchDispatcher != null) ? searchDispatcher.getSearchDispatcherParameters().getNativeDescriptor() : null;
	}
	
	public PbcattblBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public PbcattblBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public PbcattblBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public PbcattblBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public PbcattblBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public PbcattblBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public PbcattblBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public PbcattblBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative PbcattblBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public PbcattblBO setQuestionMarks(Object... values) {
		getSearchDispatcher().getSearchDispatcherParameters().getNativeDescriptor().setQuestionMarks(values);
		return this;
	}
	
	public Object getAttribute(FieldDescriptor fieldDescriptor) {
		return getBaseVO().getAttribute(fieldDescriptor.getKey());
	}
	
	public Object getAttribute(String key) {
		return getBaseVO().getAttribute(key);
	}
	public Object setAttribute(String key, Object value) {
		return getBaseVO().setAttribute(key, value);
	}
	public PbcattblBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public PbcattblBO setIsolationDirtyRead(boolean isolationDirtyRead) {
		getBaseVO().setIsolationDirtyRead(isolationDirtyRead);
		return this;
	}
	
	public int getToString() {
		return getBaseVO().getToString();
	}
	
	public Object get(Object descriptorObject) throws Exception {
		return getBaseVO().get(descriptorObject);
	}
	
	@SuppressWarnings("rawtypes")
	public List<FieldType> getPropertyList() {
		return getBaseVO().getPropertyList();
	}
	
	@SuppressWarnings("rawtypes")
	public PbcattblBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public PbcattblBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public PbcattblBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public PbcattblBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public PbcattblBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public PbcattblBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public PbcattblBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public PbcattblBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public PbcattblBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public PbcattblBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public PbcattblBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}