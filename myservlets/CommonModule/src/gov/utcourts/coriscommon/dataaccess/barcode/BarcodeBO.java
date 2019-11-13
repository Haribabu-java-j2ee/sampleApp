package gov.utcourts.coriscommon.dataaccess.barcode;

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
public class BarcodeBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("barcode", "gov.utcourts.coriscommon.dataaccess.barcode.Barcode");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor REFTYPE = new FieldDescriptor(TABLE, "ref_type", "setRefType", 0);
	public static final FieldDescriptor REFNUM = new FieldDescriptor(TABLE, "ref_num", "setRefNum", 1);
	public static final FieldDescriptor CLASSTYPE = new FieldDescriptor(TABLE, "class_type", "setClassType", 2);
	public static final FieldDescriptor CASENUM = new FieldDescriptor(TABLE, "case_num", "setCaseNum", 3);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 4);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 5);
	public static final FieldDescriptor LASTNAME = new FieldDescriptor(TABLE, "last_name", "setLastName", 6);
	public static final FieldDescriptor FIRSTNAME = new FieldDescriptor(TABLE, "first_name", "setFirstName", 7);
	public static final FieldDescriptor DOCDATE = new FieldDescriptor(TABLE, "doc_date", "setDocDate", 8);
	public static final FieldDescriptor DOCTITLE = new FieldDescriptor(TABLE, "doc_title", "setDocTitle", 9);
	public static final FieldDescriptor DOCCATEGORY = new FieldDescriptor(TABLE, "doc_category", "setDocCategory", 10);
	public static final FieldDescriptor DOCTYPE = new FieldDescriptor(TABLE, "doc_type", "setDocType", 11);
	public static final FieldDescriptor CASETYPE = new FieldDescriptor(TABLE, "case_type", "setCaseType", 12);
	public static final FieldDescriptor SSN = new FieldDescriptor(TABLE, "ssn", "setSsn", 13);
	public static final FieldDescriptor CITNUM = new FieldDescriptor(TABLE, "cit_num", "setCitNum", 14);
	public static final FieldDescriptor VIOLDATE = new FieldDescriptor(TABLE, "viol_date", "setViolDate", 15);
	public static final FieldDescriptor POSTINGPARTY = new FieldDescriptor(TABLE, "posting_party", "setPostingParty", 16);
	public static final FieldDescriptor BONDAMT = new FieldDescriptor(TABLE, "bond_amt", "setBondAmt", 17);
	public static final FieldDescriptor NUMPAGES = new FieldDescriptor(TABLE, "num_pages", "setNumPages", 18);
	public static final FieldDescriptor CMENTRYDATE = new FieldDescriptor(TABLE, "cm_entry_date", "setCmEntryDate", 19);
	public static final FieldDescriptor USERIDSRL = new FieldDescriptor(TABLE, "userid_srl", "setUseridSrl", 20);
	public static final FieldDescriptor PETLASTNAME = new FieldDescriptor(TABLE, "pet_last_name", "setPetLastName", 21);
	public static final FieldDescriptor PETFIRSTNAME = new FieldDescriptor(TABLE, "pet_first_name", "setPetFirstName", 22);
	public static final FieldDescriptor DMDOCID = new FieldDescriptor(TABLE, "dm_docid", "setDmDocid", 23);
	public static final FieldDescriptor CASESECURITY = new FieldDescriptor(TABLE, "case_security", "setCaseSecurity", 24);
	public static final FieldDescriptor DOCSECURITY = new FieldDescriptor(TABLE, "doc_security", "setDocSecurity", 25);

	public BarcodeBO() {
		super(new BarcodeVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public BarcodeBO(String source) {
		super(new BarcodeVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public BarcodeBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getRefType() {
		return getBaseVO().getTypeString(REFTYPE).getValue();
	}
	
	public BarcodeBO setRefType(String refType) {
		setPrimaryKey(REFTYPE, refType);
		return this;
	}

	public int getRefNum() {
		return getBaseVO().getTypeInteger(REFNUM).getValue();
	}
	
	public BarcodeBO setRefNum(Integer refNum) {
		setPrimaryKey(REFNUM, refNum);
		return this;
	}

	public String getClassType() {
		return getBaseVO().getTypeString(CLASSTYPE).getValue();
	}
	
	public BarcodeBO setClassType(String classType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CLASSTYPE, classType);
		return this;
	}

	public String getCaseNum() {
		return getBaseVO().getTypeString(CASENUM).getValue();
	}
	
	public BarcodeBO setCaseNum(String caseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASENUM, caseNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public BarcodeBO setLocnCode(String locnCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public BarcodeBO setCourtType(String courtType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTYPE, courtType);
		return this;
	}

	public String getLastName() {
		return getBaseVO().getTypeString(LASTNAME).getValue();
	}
	
	public BarcodeBO setLastName(String lastName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTNAME, lastName);
		return this;
	}

	public String getFirstName() {
		return getBaseVO().getTypeString(FIRSTNAME).getValue();
	}
	
	public BarcodeBO setFirstName(String firstName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FIRSTNAME, firstName);
		return this;
	}

	public Date getDocDate() {
		return getBaseVO().getTypeDate(DOCDATE).getValue();
	}
	
	public BarcodeBO setDocDate(Date docDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DOCDATE, docDate);
		return this;
	}
	
	public BarcodeBO setDocDate(Date docDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DOCDATE, docDate, dateFormat);
		getBaseVO().setAttribute(DOCDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getDocTitle() {
		return getBaseVO().getTypeString(DOCTITLE).getValue();
	}
	
	public BarcodeBO setDocTitle(String docTitle) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DOCTITLE, docTitle);
		return this;
	}

	public String getDocCategory() {
		return getBaseVO().getTypeString(DOCCATEGORY).getValue();
	}
	
	public BarcodeBO setDocCategory(String docCategory) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DOCCATEGORY, docCategory);
		return this;
	}

	public String getDocType() {
		return getBaseVO().getTypeString(DOCTYPE).getValue();
	}
	
	public BarcodeBO setDocType(String docType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DOCTYPE, docType);
		return this;
	}

	public String getCaseType() {
		return getBaseVO().getTypeString(CASETYPE).getValue();
	}
	
	public BarcodeBO setCaseType(String caseType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASETYPE, caseType);
		return this;
	}

	public String getSsn() {
		return getBaseVO().getTypeString(SSN).getValue();
	}
	
	public BarcodeBO setSsn(String ssn) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SSN, ssn);
		return this;
	}

	public String getCitNum() {
		return getBaseVO().getTypeString(CITNUM).getValue();
	}
	
	public BarcodeBO setCitNum(String citNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CITNUM, citNum);
		return this;
	}

	public Date getViolDate() {
		return getBaseVO().getTypeDate(VIOLDATE).getValue();
	}
	
	public BarcodeBO setViolDate(Date violDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLDATE, violDate);
		return this;
	}
	
	public BarcodeBO setViolDate(Date violDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLDATE, violDate, dateFormat);
		getBaseVO().setAttribute(VIOLDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getPostingParty() {
		return getBaseVO().getTypeString(POSTINGPARTY).getValue();
	}
	
	public BarcodeBO setPostingParty(String postingParty) {
		getBaseVO().setPropertyListValue(SET_BY_USER, POSTINGPARTY, postingParty);
		return this;
	}

	public BigDecimal getBondAmt() {
		return getBaseVO().getTypeBigDecimal(BONDAMT).getValue();
	}
	
	public BarcodeBO setBondAmt(BigDecimal bondAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BONDAMT, bondAmt);
		return this;
	}
	
	public BarcodeBO setBondAmt(double bondAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BONDAMT, new BigDecimal(String.valueOf(new Double(bondAmt))));
		return this;
	}

	public String getNumPages() {
		return getBaseVO().getTypeString(NUMPAGES).getValue();
	}
	
	public BarcodeBO setNumPages(String numPages) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NUMPAGES, numPages);
		return this;
	}

	public Date getCmEntryDate() {
		return getBaseVO().getTypeDate(CMENTRYDATE).getValue();
	}
	
	public BarcodeBO setCmEntryDate(Date cmEntryDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CMENTRYDATE, cmEntryDate);
		return this;
	}
	
	public BarcodeBO setCmEntryDate(Date cmEntryDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CMENTRYDATE, cmEntryDate, dateFormat);
		getBaseVO().setAttribute(CMENTRYDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getUseridSrl() {
		return getBaseVO().getTypeInteger(USERIDSRL).getValue();
	}
	
	public BarcodeBO setUseridSrl(Integer useridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, USERIDSRL, useridSrl);
		return this;
	}

	public String getPetLastName() {
		return getBaseVO().getTypeString(PETLASTNAME).getValue();
	}
	
	public BarcodeBO setPetLastName(String petLastName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PETLASTNAME, petLastName);
		return this;
	}

	public String getPetFirstName() {
		return getBaseVO().getTypeString(PETFIRSTNAME).getValue();
	}
	
	public BarcodeBO setPetFirstName(String petFirstName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PETFIRSTNAME, petFirstName);
		return this;
	}

	public int getDmDocid() {
		return getBaseVO().getTypeInteger(DMDOCID).getValue();
	}
	
	public BarcodeBO setDmDocid(Integer dmDocid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DMDOCID, dmDocid);
		return this;
	}

	public String getCaseSecurity() {
		return getBaseVO().getTypeString(CASESECURITY).getValue();
	}
	
	public BarcodeBO setCaseSecurity(String caseSecurity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASESECURITY, caseSecurity);
		return this;
	}

	public String getDocSecurity() {
		return getBaseVO().getTypeString(DOCSECURITY).getValue();
	}
	
	public BarcodeBO setDocSecurity(String docSecurity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DOCSECURITY, docSecurity);
		return this;
	}
	
	public BarcodeBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<BarcodeBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new BarcodeBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new BarcodeBO(getBaseVO());
		}
	}
	
	public BarcodeBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public BarcodeBO insert(FieldDescriptor... specificFields) throws Exception {
		return new BarcodeBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public BarcodeBO update(FieldDescriptor... specificFields) throws Exception {
		return new BarcodeBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public BarcodeBO delete() throws Exception {
		return new BarcodeBO(super.delete(getNativeDescriptor()));
	}
	
	public BarcodeBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public BarcodeBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public BarcodeBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public BarcodeBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public BarcodeBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public BarcodeBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public BarcodeBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public BarcodeBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public BarcodeBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public BarcodeBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public BarcodeBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public BarcodeBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public BarcodeBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public BarcodeBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public BarcodeBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public BarcodeBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public BarcodeBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public BarcodeBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public BarcodeBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public BarcodeBO where(FieldDescriptor findBy, Object value) {
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
	
	public BarcodeBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public BarcodeBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public BarcodeBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public BarcodeBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public BarcodeBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public BarcodeBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public BarcodeBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public BarcodeBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public BarcodeBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public BarcodeBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public BarcodeBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public BarcodeBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public BarcodeBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public BarcodeBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public BarcodeBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public BarcodeBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<BarcodeBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public BarcodeBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public BarcodeBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public BarcodeBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public BarcodeBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public BarcodeBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public BarcodeBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public BarcodeBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public BarcodeBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public BarcodeBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public BarcodeBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public BarcodeBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public BarcodeBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public BarcodeBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public BarcodeBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public BarcodeBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public BarcodeBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public BarcodeBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public BarcodeBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public BarcodeBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public BarcodeBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public BarcodeBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public BarcodeBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public BarcodeBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public BarcodeBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public BarcodeBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public BarcodeBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative BarcodeBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public BarcodeBO setQuestionMarks(Object... values) {
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
	public BarcodeBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public BarcodeBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public BarcodeBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public BarcodeBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public BarcodeBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public BarcodeBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public BarcodeBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public BarcodeBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public BarcodeBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public BarcodeBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public BarcodeBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public BarcodeBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public BarcodeBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}