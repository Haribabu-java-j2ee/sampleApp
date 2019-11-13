package gov.utcourts.coriscommon.dataaccess.domvioldismissal;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DomViolDismissalVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DomViolDismissalBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(DomViolDismissalBO.SEQ.clear()).setAsPrimaryKey();
	private TypeDate dvDismEventDate = new TypeDate("dv_dism_event_date").setFieldDescriptor(DomViolDismissalBO.DVDISMEVENTDATE.clear());
	private TypeString dvDismReasonCode = new TypeString("dv_dism_reason_code").setFieldDescriptor(DomViolDismissalBO.DVDISMREASONCODE.clear());

	public DomViolDismissalVO() {
		super(DomViolDismissalBO.TABLE, DomViolDismissalBO.SYSTEM, DomViolDismissalBO.CORIS_DISTRICT_DB.setSource("D"), DomViolDismissalBO.CORIS_JUSTICE_DB.setSource("J"), DomViolDismissalBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DomViolDismissalBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DomViolDismissalVO(String source) {
		super(DomViolDismissalBO.TABLE, DomViolDismissalBO.SYSTEM, DomViolDismissalBO.CORIS_DISTRICT_DB.setSource("D"), DomViolDismissalBO.CORIS_JUSTICE_DB.setSource("J"), DomViolDismissalBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DomViolDismissalBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(dvDismEventDate);
		this.getPropertyList().add(dvDismReasonCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DomViolDismissalBO.DVDISMEVENTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DomViolDismissalBO.DVDISMEVENTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}