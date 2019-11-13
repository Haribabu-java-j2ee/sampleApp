package gov.utcourts.coriscommon.dataaccess.pbcatcol;

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
public class PbcatcolBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("pbcatcol", "gov.utcourts.coriscommon.dataaccess.pbcatcol.Pbcatcol");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor PBCTNAM = new FieldDescriptor(TABLE, "pbc_tnam", "setPbcTnam", 0);
	public static final FieldDescriptor PBCTID = new FieldDescriptor(TABLE, "pbc_tid", "setPbcTid", 1);
	public static final FieldDescriptor PBCOWNR = new FieldDescriptor(TABLE, "pbc_ownr", "setPbcOwnr", 2);
	public static final FieldDescriptor PBCCNAM = new FieldDescriptor(TABLE, "pbc_cnam", "setPbcCnam", 3);
	public static final FieldDescriptor PBCLABL = new FieldDescriptor(TABLE, "pbc_labl", "setPbcLabl", 4);
	public static final FieldDescriptor PBCLPOS = new FieldDescriptor(TABLE, "pbc_lpos", "setPbcLpos", 5);
	public static final FieldDescriptor PBCHDR = new FieldDescriptor(TABLE, "pbc_hdr", "setPbcHdr", 6);
	public static final FieldDescriptor PBCHPOS = new FieldDescriptor(TABLE, "pbc_hpos", "setPbcHpos", 7);
	public static final FieldDescriptor PBCJTFY = new FieldDescriptor(TABLE, "pbc_jtfy", "setPbcJtfy", 8);
	public static final FieldDescriptor PBCMASK = new FieldDescriptor(TABLE, "pbc_mask", "setPbcMask", 9);
	public static final FieldDescriptor PBCCASE = new FieldDescriptor(TABLE, "pbc_case", "setPbcCase", 10);
	public static final FieldDescriptor PBCHGHT = new FieldDescriptor(TABLE, "pbc_hght", "setPbcHght", 11);
	public static final FieldDescriptor PBCWDTH = new FieldDescriptor(TABLE, "pbc_wdth", "setPbcWdth", 12);
	public static final FieldDescriptor PBCPTRN = new FieldDescriptor(TABLE, "pbc_ptrn", "setPbcPtrn", 13);
	public static final FieldDescriptor PBCBMAP = new FieldDescriptor(TABLE, "pbc_bmap", "setPbcBmap", 14);
	public static final FieldDescriptor PBCINIT = new FieldDescriptor(TABLE, "pbc_init", "setPbcInit", 15);
	public static final FieldDescriptor PBCEDIT = new FieldDescriptor(TABLE, "pbc_edit", "setPbcEdit", 16);
	public static final FieldDescriptor PBCCMNT = new FieldDescriptor(TABLE, "pbc_cmnt", "setPbcCmnt", 17);
	public static final FieldDescriptor PBCTAG = new FieldDescriptor(TABLE, "pbc_tag", "setPbcTag", 18);

	public PbcatcolBO() {
		super(new PbcatcolVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public PbcatcolBO(String source) {
		super(new PbcatcolVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public PbcatcolBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getPbcTnam() {
		return getBaseVO().getTypeString(PBCTNAM).getValue();
	}
	
	public PbcatcolBO setPbcTnam(String pbcTnam) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCTNAM, pbcTnam);
		return this;
	}

	public int getPbcTid() {
		return getBaseVO().getTypeInteger(PBCTID).getValue();
	}
	
	public PbcatcolBO setPbcTid(Integer pbcTid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCTID, pbcTid);
		return this;
	}

	public String getPbcOwnr() {
		return getBaseVO().getTypeString(PBCOWNR).getValue();
	}
	
	public PbcatcolBO setPbcOwnr(String pbcOwnr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCOWNR, pbcOwnr);
		return this;
	}

	public String getPbcCnam() {
		return getBaseVO().getTypeString(PBCCNAM).getValue();
	}
	
	public PbcatcolBO setPbcCnam(String pbcCnam) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCCNAM, pbcCnam);
		return this;
	}

	public String getPbcLabl() {
		return getBaseVO().getTypeString(PBCLABL).getValue();
	}
	
	public PbcatcolBO setPbcLabl(String pbcLabl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCLABL, pbcLabl);
		return this;
	}

	public int getPbcLpos() {
		return getBaseVO().getTypeInteger(PBCLPOS).getValue();
	}
	
	public PbcatcolBO setPbcLpos(Integer pbcLpos) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCLPOS, pbcLpos);
		return this;
	}

	public String getPbcHdr() {
		return getBaseVO().getTypeString(PBCHDR).getValue();
	}
	
	public PbcatcolBO setPbcHdr(String pbcHdr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCHDR, pbcHdr);
		return this;
	}

	public int getPbcHpos() {
		return getBaseVO().getTypeInteger(PBCHPOS).getValue();
	}
	
	public PbcatcolBO setPbcHpos(Integer pbcHpos) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCHPOS, pbcHpos);
		return this;
	}

	public int getPbcJtfy() {
		return getBaseVO().getTypeInteger(PBCJTFY).getValue();
	}
	
	public PbcatcolBO setPbcJtfy(Integer pbcJtfy) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCJTFY, pbcJtfy);
		return this;
	}

	public String getPbcMask() {
		return getBaseVO().getTypeString(PBCMASK).getValue();
	}
	
	public PbcatcolBO setPbcMask(String pbcMask) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCMASK, pbcMask);
		return this;
	}

	public int getPbcCase() {
		return getBaseVO().getTypeInteger(PBCCASE).getValue();
	}
	
	public PbcatcolBO setPbcCase(Integer pbcCase) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCCASE, pbcCase);
		return this;
	}

	public int getPbcHght() {
		return getBaseVO().getTypeInteger(PBCHGHT).getValue();
	}
	
	public PbcatcolBO setPbcHght(Integer pbcHght) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCHGHT, pbcHght);
		return this;
	}

	public int getPbcWdth() {
		return getBaseVO().getTypeInteger(PBCWDTH).getValue();
	}
	
	public PbcatcolBO setPbcWdth(Integer pbcWdth) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCWDTH, pbcWdth);
		return this;
	}

	public String getPbcPtrn() {
		return getBaseVO().getTypeString(PBCPTRN).getValue();
	}
	
	public PbcatcolBO setPbcPtrn(String pbcPtrn) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCPTRN, pbcPtrn);
		return this;
	}

	public String getPbcBmap() {
		return getBaseVO().getTypeString(PBCBMAP).getValue();
	}
	
	public PbcatcolBO setPbcBmap(String pbcBmap) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCBMAP, pbcBmap);
		return this;
	}

	public String getPbcInit() {
		return getBaseVO().getTypeString(PBCINIT).getValue();
	}
	
	public PbcatcolBO setPbcInit(String pbcInit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCINIT, pbcInit);
		return this;
	}

	public String getPbcEdit() {
		return getBaseVO().getTypeString(PBCEDIT).getValue();
	}
	
	public PbcatcolBO setPbcEdit(String pbcEdit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCEDIT, pbcEdit);
		return this;
	}

	public String getPbcCmnt() {
		return getBaseVO().getTypeString(PBCCMNT).getValue();
	}
	
	public PbcatcolBO setPbcCmnt(String pbcCmnt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCCMNT, pbcCmnt);
		return this;
	}

	public String getPbcTag() {
		return getBaseVO().getTypeString(PBCTAG).getValue();
	}
	
	public PbcatcolBO setPbcTag(String pbcTag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PBCTAG, pbcTag);
		return this;
	}
	
	public PbcatcolBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<PbcatcolBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new PbcatcolBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new PbcatcolBO(getBaseVO());
		}
	}
	
	public PbcatcolBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public PbcatcolBO insert(FieldDescriptor... specificFields) throws Exception {
		return new PbcatcolBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public PbcatcolBO update(FieldDescriptor... specificFields) throws Exception {
		return new PbcatcolBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public PbcatcolBO delete() throws Exception {
		return new PbcatcolBO(super.delete(getNativeDescriptor()));
	}
	
	public PbcatcolBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public PbcatcolBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public PbcatcolBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public PbcatcolBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public PbcatcolBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public PbcatcolBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public PbcatcolBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public PbcatcolBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public PbcatcolBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public PbcatcolBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PbcatcolBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PbcatcolBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public PbcatcolBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public PbcatcolBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public PbcatcolBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PbcatcolBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PbcatcolBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public PbcatcolBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public PbcatcolBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public PbcatcolBO where(FieldDescriptor findBy, Object value) {
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
	
	public PbcatcolBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public PbcatcolBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public PbcatcolBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public PbcatcolBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public PbcatcolBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public PbcatcolBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public PbcatcolBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public PbcatcolBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public PbcatcolBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public PbcatcolBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public PbcatcolBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public PbcatcolBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public PbcatcolBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public PbcatcolBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public PbcatcolBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public PbcatcolBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<PbcatcolBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public PbcatcolBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public PbcatcolBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public PbcatcolBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public PbcatcolBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public PbcatcolBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public PbcatcolBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public PbcatcolBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public PbcatcolBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public PbcatcolBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public PbcatcolBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public PbcatcolBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public PbcatcolBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public PbcatcolBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public PbcatcolBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public PbcatcolBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public PbcatcolBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public PbcatcolBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public PbcatcolBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public PbcatcolBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public PbcatcolBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public PbcatcolBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public PbcatcolBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public PbcatcolBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public PbcatcolBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public PbcatcolBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public PbcatcolBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative PbcatcolBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public PbcatcolBO setQuestionMarks(Object... values) {
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
	public PbcatcolBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public PbcatcolBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public PbcatcolBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public PbcatcolBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public PbcatcolBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public PbcatcolBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public PbcatcolBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public PbcatcolBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public PbcatcolBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public PbcatcolBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public PbcatcolBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public PbcatcolBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public PbcatcolBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}