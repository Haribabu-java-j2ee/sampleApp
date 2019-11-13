package gov.utcourts.coriscommon.dataaccess.offense;

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

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OffenseBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("offense", "gov.utcourts.coriscommon.dataaccess.offense.Offense");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor OFFSVIOLCODE = new FieldDescriptor(TABLE, "offs_viol_code", "setOffsViolCode", 0);
	public static final FieldDescriptor LASTEFFECTDATE = new FieldDescriptor(TABLE, "last_effect_date", "setLastEffectDate", 1);
	public static final FieldDescriptor DESCR = new FieldDescriptor(TABLE, "descr", "setDescr", 2);
	public static final FieldDescriptor CATEGORY = new FieldDescriptor(TABLE, "category", "setCategory", 3);
	public static final FieldDescriptor NCICCODE = new FieldDescriptor(TABLE, "ncic_code", "setNcicCode", 4);
	public static final FieldDescriptor AAMVACODE = new FieldDescriptor(TABLE, "aamva_code", "setAamvaCode", 5);
	public static final FieldDescriptor DRLICRPT = new FieldDescriptor(TABLE, "dr_lic_rpt", "setDrLicRpt", 6);
	public static final FieldDescriptor BCIRPT = new FieldDescriptor(TABLE, "bci_rpt", "setBciRpt", 7);
	public static final FieldDescriptor SPECPROCATTR = new FieldDescriptor(TABLE, "spec_proc_attr", "setSpecProcAttr", 8);
	public static final FieldDescriptor FTAFLAG = new FieldDescriptor(TABLE, "fta_flag", "setFtaFlag", 9);
	public static final FieldDescriptor WARRFLAG = new FieldDescriptor(TABLE, "warr_flag", "setWarrFlag", 10);
	public static final FieldDescriptor MANDAPPEARFLAG = new FieldDescriptor(TABLE, "mand_appear_flag", "setMandAppearFlag", 11);
	public static final FieldDescriptor DEFLTSEVERITY = new FieldDescriptor(TABLE, "deflt_severity", "setDefltSeverity", 12);
	public static final FieldDescriptor SUGGESTBAIL = new FieldDescriptor(TABLE, "suggest_bail", "setSuggestBail", 13);
	public static final FieldDescriptor COMPLIANCECREDIT = new FieldDescriptor(TABLE, "compliance_credit", "setComplianceCredit", 14);
	public static final FieldDescriptor NONMOVETRAF = new FieldDescriptor(TABLE, "non_move_traf", "setNonMoveTraf", 15);
	public static final FieldDescriptor JCSECURITYSCHG = new FieldDescriptor(TABLE, "jc_security_schg", "setJcSecuritySchg", 16);

	public OffenseBO() {
		super(new OffenseVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public OffenseBO(String source) {
		super(new OffenseVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public OffenseBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getOffsViolCode() {
		return getBaseVO().getTypeString(OFFSVIOLCODE).getValue();
	}
	
	public OffenseBO setOffsViolCode(String offsViolCode) {
		setPrimaryKey(OFFSVIOLCODE, offsViolCode);
		return this;
	}

	public Date getLastEffectDate() {
		return getBaseVO().getTypeDate(LASTEFFECTDATE).getValue();
	}
	
	public OffenseBO setLastEffectDate(Date lastEffectDate) {
		setPrimaryKey(LASTEFFECTDATE, lastEffectDate);
		return this;
	}
	
	public OffenseBO setLastEffectDate(Date lastEffectDate, DateFormat dateFormat) {
		setPrimaryKey(LASTEFFECTDATE, lastEffectDate);
		getBaseVO().setAttribute(LASTEFFECTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getDescr() {
		return getBaseVO().getTypeString(DESCR).getValue();
	}
	
	public OffenseBO setDescr(String descr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DESCR, descr);
		return this;
	}

	public String getCategory() {
		return getBaseVO().getTypeString(CATEGORY).getValue();
	}
	
	public OffenseBO setCategory(String category) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CATEGORY, category);
		return this;
	}

	public String getNcicCode() {
		return getBaseVO().getTypeString(NCICCODE).getValue();
	}
	
	public OffenseBO setNcicCode(String ncicCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NCICCODE, ncicCode);
		return this;
	}

	public String getAamvaCode() {
		return getBaseVO().getTypeString(AAMVACODE).getValue();
	}
	
	public OffenseBO setAamvaCode(String aamvaCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AAMVACODE, aamvaCode);
		return this;
	}

	public String getDrLicRpt() {
		return getBaseVO().getTypeString(DRLICRPT).getValue();
	}
	
	public OffenseBO setDrLicRpt(String drLicRpt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPT, drLicRpt);
		return this;
	}

	public String getBciRpt() {
		return getBaseVO().getTypeString(BCIRPT).getValue();
	}
	
	public OffenseBO setBciRpt(String bciRpt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BCIRPT, bciRpt);
		return this;
	}

	public String getSpecProcAttr() {
		return getBaseVO().getTypeString(SPECPROCATTR).getValue();
	}
	
	public OffenseBO setSpecProcAttr(String specProcAttr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SPECPROCATTR, specProcAttr);
		return this;
	}

	public String getFtaFlag() {
		return getBaseVO().getTypeString(FTAFLAG).getValue();
	}
	
	public OffenseBO setFtaFlag(String ftaFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAFLAG, ftaFlag);
		return this;
	}

	public String getWarrFlag() {
		return getBaseVO().getTypeString(WARRFLAG).getValue();
	}
	
	public OffenseBO setWarrFlag(String warrFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, WARRFLAG, warrFlag);
		return this;
	}

	public String getMandAppearFlag() {
		return getBaseVO().getTypeString(MANDAPPEARFLAG).getValue();
	}
	
	public OffenseBO setMandAppearFlag(String mandAppearFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MANDAPPEARFLAG, mandAppearFlag);
		return this;
	}

	public String getDefltSeverity() {
		return getBaseVO().getTypeString(DEFLTSEVERITY).getValue();
	}
	
	public OffenseBO setDefltSeverity(String defltSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEFLTSEVERITY, defltSeverity);
		return this;
	}

	public BigDecimal getSuggestBail() {
		return getBaseVO().getTypeBigDecimal(SUGGESTBAIL).getValue();
	}
	
	public OffenseBO setSuggestBail(BigDecimal suggestBail) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SUGGESTBAIL, suggestBail);
		return this;
	}
	
	public OffenseBO setSuggestBail(double suggestBail) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SUGGESTBAIL, new BigDecimal(String.valueOf(new Double(suggestBail))));
		return this;
	}

	public BigDecimal getComplianceCredit() {
		return getBaseVO().getTypeBigDecimal(COMPLIANCECREDIT).getValue();
	}
	
	public OffenseBO setComplianceCredit(BigDecimal complianceCredit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COMPLIANCECREDIT, complianceCredit);
		return this;
	}
	
	public OffenseBO setComplianceCredit(double complianceCredit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COMPLIANCECREDIT, new BigDecimal(String.valueOf(new Double(complianceCredit))));
		return this;
	}

	public String getNonMoveTraf() {
		return getBaseVO().getTypeString(NONMOVETRAF).getValue();
	}
	
	public OffenseBO setNonMoveTraf(String nonMoveTraf) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NONMOVETRAF, nonMoveTraf);
		return this;
	}

	public String getJcSecuritySchg() {
		return getBaseVO().getTypeString(JCSECURITYSCHG).getValue();
	}
	
	public OffenseBO setJcSecuritySchg(String jcSecuritySchg) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JCSECURITYSCHG, jcSecuritySchg);
		return this;
	}
	
	public OffenseBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<OffenseBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new OffenseBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new OffenseBO(getBaseVO());
		}
	}
	
	public OffenseBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public OffenseBO insert(FieldDescriptor... specificFields) throws Exception {
		return new OffenseBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public OffenseBO update(FieldDescriptor... specificFields) throws Exception {
		return new OffenseBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public OffenseBO delete() throws Exception {
		return new OffenseBO(super.delete(getNativeDescriptor()));
	}
	
	public OffenseBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public OffenseBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public OffenseBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public OffenseBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public OffenseBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public OffenseBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public OffenseBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public OffenseBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public OffenseBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public OffenseBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public OffenseBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public OffenseBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public OffenseBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public OffenseBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public OffenseBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public OffenseBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public OffenseBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public OffenseBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public OffenseBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public OffenseBO where(FieldDescriptor findBy, Object value) {
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
	
	public OffenseBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public OffenseBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public OffenseBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public OffenseBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public OffenseBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public OffenseBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public OffenseBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public OffenseBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public OffenseBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public OffenseBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public OffenseBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public OffenseBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public OffenseBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public OffenseBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public OffenseBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public OffenseBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<OffenseBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public OffenseBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public OffenseBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public OffenseBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public OffenseBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public OffenseBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public OffenseBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public OffenseBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public OffenseBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public OffenseBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public OffenseBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public OffenseBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public OffenseBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public OffenseBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public OffenseBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public OffenseBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public OffenseBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public OffenseBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public OffenseBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public OffenseBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public OffenseBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public OffenseBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public OffenseBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public OffenseBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public OffenseBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public OffenseBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public OffenseBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative OffenseBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public OffenseBO setQuestionMarks(Object... values) {
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
	public OffenseBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public OffenseBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public OffenseBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public OffenseBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public OffenseBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public OffenseBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public OffenseBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public OffenseBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public OffenseBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public OffenseBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public OffenseBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public OffenseBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public OffenseBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}