package gov.utcourts.coriscommon.dataaccess.duicourt;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DuiCourtVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DuiCourtBO.INTCASENUM.clear());
	private TypeDate referralDate = new TypeDate("referral_date").setFieldDescriptor(DuiCourtBO.REFERRALDATE.clear());
	private TypeDate startingDate = new TypeDate("starting_date").setFieldDescriptor(DuiCourtBO.STARTINGDATE.clear());
	private TypeDate completionDate = new TypeDate("completion_date").setFieldDescriptor(DuiCourtBO.COMPLETIONDATE.clear());
	private TypeDate referralBackDate = new TypeDate("referral_back_date").setFieldDescriptor(DuiCourtBO.REFERRALBACKDATE.clear());

	public DuiCourtVO(String source) {
		super(DuiCourtBO.TABLE, DuiCourtBO.SYSTEM, DuiCourtBO.CORIS_DISTRICT_DB.setSource("D"), DuiCourtBO.CORIS_JUSTICE_DB.setSource("J"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(referralDate);
		this.getPropertyList().add(startingDate);
		this.getPropertyList().add(completionDate);
		this.getPropertyList().add(referralBackDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DuiCourtBO.REFERRALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DuiCourtBO.REFERRALDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DuiCourtBO.STARTINGDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DuiCourtBO.STARTINGDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DuiCourtBO.COMPLETIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DuiCourtBO.COMPLETIONDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DuiCourtBO.REFERRALBACKDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DuiCourtBO.REFERRALBACKDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}