package gov.utcourts.coriscommon.dataaccess.proofcase;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProofCaseVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(ProofCaseBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(ProofCaseBO.DESCR.clear()).setAsPrimaryKey();
	private TypeDate dueDate = new TypeDate("due_date").setFieldDescriptor(ProofCaseBO.DUEDATE.clear()).setNullable();
	private TypeBigDecimal suspendAmt = new TypeBigDecimal("suspend_amt").setFieldDescriptor(ProofCaseBO.SUSPENDAMT.clear()).setNullable();
	private TypeDate shownDate = new TypeDate("shown_date").setFieldDescriptor(ProofCaseBO.SHOWNDATE.clear()).setNullable();

	public ProofCaseVO() {
		super(ProofCaseBO.TABLE, ProofCaseBO.SYSTEM, ProofCaseBO.CORIS_DISTRICT_DB.setSource("D"), ProofCaseBO.CORIS_JUSTICE_DB.setSource("J"), ProofCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProofCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ProofCaseVO(String source) {
		super(ProofCaseBO.TABLE, ProofCaseBO.SYSTEM, ProofCaseBO.CORIS_DISTRICT_DB.setSource("D"), ProofCaseBO.CORIS_JUSTICE_DB.setSource("J"), ProofCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProofCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(dueDate);
		this.getPropertyList().add(suspendAmt);
		this.getPropertyList().add(shownDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(ProofCaseBO.DUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ProofCaseBO.DUEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ProofCaseBO.SHOWNDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ProofCaseBO.SHOWNDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}