package gov.utcourts.coriscommon.dataaccess.documentduedate;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentDueDateVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DocumentDueDateBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setNullable().setAsPrimaryKey();
	private TypeString reportCategory = new TypeString("report_category").setFieldDescriptor(DocumentDueDateBO.REPORTCATEGORY.clear()).addForeignKey("document_type","category",true).setNullable().setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(DocumentDueDateBO.PARTYNUM.clear()).addForeignKey("party","party_num",false);
	private TypeDate reportDueDate = new TypeDate("report_due_date").setFieldDescriptor(DocumentDueDateBO.REPORTDUEDATE.clear());

	public DocumentDueDateVO() {
		super(DocumentDueDateBO.TABLE, DocumentDueDateBO.SYSTEM, DocumentDueDateBO.CORIS_DISTRICT_DB.setSource("D"), DocumentDueDateBO.CORIS_JUSTICE_DB.setSource("J"), DocumentDueDateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentDueDateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentDueDateVO(String source) {
		super(DocumentDueDateBO.TABLE, DocumentDueDateBO.SYSTEM, DocumentDueDateBO.CORIS_DISTRICT_DB.setSource("D"), DocumentDueDateBO.CORIS_JUSTICE_DB.setSource("J"), DocumentDueDateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentDueDateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(reportCategory);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(reportDueDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocumentDueDateBO.REPORTDUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocumentDueDateBO.REPORTDUEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}