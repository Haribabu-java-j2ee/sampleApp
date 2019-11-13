package gov.utcourts.coriscommon.dataaccess.workcalcase;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class WorkCalCaseVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(WorkCalCaseBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString hearingCode = new TypeString("hearing_code").setFieldDescriptor(WorkCalCaseBO.HEARINGCODE.clear()).setAsPrimaryKey();
	private TypeDate appearDate = new TypeDate("appear_date").setFieldDescriptor(WorkCalCaseBO.APPEARDATE.clear()).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(WorkCalCaseBO.CREATEDATETIME.clear()).setAsPrimaryKey();
	private TypeDate hearingTime = new TypeDate("hearing_time").setFieldDescriptor(WorkCalCaseBO.HEARINGTIME.clear());
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(WorkCalCaseBO.CASETYPE.clear());
	private TypeInteger priority = new TypeInteger("priority").setFieldDescriptor(WorkCalCaseBO.PRIORITY.clear()).setNullable();
	private TypeInteger plaintiffCount = new TypeInteger("plaintiff_count").setFieldDescriptor(WorkCalCaseBO.PLAINTIFFCOUNT.clear()).setNullable();
	private TypeInteger defendantCount = new TypeInteger("defendant_count").setFieldDescriptor(WorkCalCaseBO.DEFENDANTCOUNT.clear()).setNullable();
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(WorkCalCaseBO.JUDGEID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger titlePlaNum = new TypeInteger("title_pla_num").setFieldDescriptor(WorkCalCaseBO.TITLEPLANUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger titleDefNum = new TypeInteger("title_def_num").setFieldDescriptor(WorkCalCaseBO.TITLEDEFNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(WorkCalCaseBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(WorkCalCaseBO.COURTTYPE.clear()).setNullable();
	private TypeString caseTypeCategory = new TypeString("case_type_category").setFieldDescriptor(WorkCalCaseBO.CASETYPECATEGORY.clear()).setNullable();
	private TypeString room = new TypeString("room").setFieldDescriptor(WorkCalCaseBO.ROOM.clear()).setNullable();
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(WorkCalCaseBO.CASENUM.clear()).setNullable();
	private TypeString caseTypeDescr = new TypeString("case_type_descr").setFieldDescriptor(WorkCalCaseBO.CASETYPEDESCR.clear()).setNullable();
	private TypeString hearingDescr = new TypeString("hearing_descr").setFieldDescriptor(WorkCalCaseBO.HEARINGDESCR.clear()).setNullable();
	private TypeString judgeFirstName = new TypeString("judge_first_name").setFieldDescriptor(WorkCalCaseBO.JUDGEFIRSTNAME.clear()).setNullable();
	private TypeString judgeLastName = new TypeString("judge_last_name").setFieldDescriptor(WorkCalCaseBO.JUDGELASTNAME.clear()).setNullable();
	private TypeString eventDescr = new TypeString("event_descr").setFieldDescriptor(WorkCalCaseBO.EVENTDESCR.clear()).setNullable();
	private TypeString caseTitle = new TypeString("case_title").setFieldDescriptor(WorkCalCaseBO.CASETITLE.clear()).setNullable();
	private TypeString caseSecurity = new TypeString("case_security").setFieldDescriptor(WorkCalCaseBO.CASESECURITY.clear()).addForeignKey("security_type","code",false);

	public WorkCalCaseVO() {
		super(WorkCalCaseBO.TABLE, WorkCalCaseBO.SYSTEM, WorkCalCaseBO.CORIS_DISTRICT_DB.setSource("D"), WorkCalCaseBO.CORIS_JUSTICE_DB.setSource("J"), WorkCalCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WorkCalCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public WorkCalCaseVO(String source) {
		super(WorkCalCaseBO.TABLE, WorkCalCaseBO.SYSTEM, WorkCalCaseBO.CORIS_DISTRICT_DB.setSource("D"), WorkCalCaseBO.CORIS_JUSTICE_DB.setSource("J"), WorkCalCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WorkCalCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(hearingCode);
		this.getPropertyList().add(appearDate);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(hearingTime);
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(priority);
		this.getPropertyList().add(plaintiffCount);
		this.getPropertyList().add(defendantCount);
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(titlePlaNum);
		this.getPropertyList().add(titleDefNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(caseTypeCategory);
		this.getPropertyList().add(room);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(caseTypeDescr);
		this.getPropertyList().add(hearingDescr);
		this.getPropertyList().add(judgeFirstName);
		this.getPropertyList().add(judgeLastName);
		this.getPropertyList().add(eventDescr);
		this.getPropertyList().add(caseTitle);
		this.getPropertyList().add(caseSecurity);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(WorkCalCaseBO.APPEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(WorkCalCaseBO.APPEARDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(WorkCalCaseBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(WorkCalCaseBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(WorkCalCaseBO.HEARINGTIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(WorkCalCaseBO.HEARINGTIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}