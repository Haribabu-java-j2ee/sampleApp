package gov.utcourts.coriscommon.dataaccess.evidencehist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EvidenceHistVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EvidenceHistBO.INTCASENUM.clear()).addForeignKey("evidence","int_case_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(EvidenceHistBO.SEQ.clear()).addForeignKey("evidence","seq",false).setAsPrimaryKey();
	private TypeDate actionDatetime = new TypeDate("action_datetime").setFieldDescriptor(EvidenceHistBO.ACTIONDATETIME.clear()).setAsPrimaryKey();
	private TypeString actionCode = new TypeString("action_code").setFieldDescriptor(EvidenceHistBO.ACTIONCODE.clear()).setAsPrimaryKey();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(EvidenceHistBO.CLERKID.clear());

	public EvidenceHistVO() {
		super(EvidenceHistBO.TABLE, EvidenceHistBO.SYSTEM, EvidenceHistBO.CORIS_DISTRICT_DB.setSource("D"), EvidenceHistBO.CORIS_JUSTICE_DB.setSource("J"), EvidenceHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EvidenceHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EvidenceHistVO(String source) {
		super(EvidenceHistBO.TABLE, EvidenceHistBO.SYSTEM, EvidenceHistBO.CORIS_DISTRICT_DB.setSource("D"), EvidenceHistBO.CORIS_JUSTICE_DB.setSource("J"), EvidenceHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EvidenceHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(actionDatetime);
		this.getPropertyList().add(actionCode);
		this.getPropertyList().add(clerkId);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(EvidenceHistBO.ACTIONDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(EvidenceHistBO.ACTIONDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}