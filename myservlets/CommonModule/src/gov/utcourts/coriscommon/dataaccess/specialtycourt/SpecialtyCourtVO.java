package gov.utcourts.coriscommon.dataaccess.specialtycourt;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SpecialtyCourtVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SpecialtyCourtBO.INTCASENUM.clear()).setAsPrimaryKey();
	private TypeString specialCourt = new TypeString("special_court").setFieldDescriptor(SpecialtyCourtBO.SPECIALCOURT.clear()).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(SpecialtyCourtBO.CREATEDATETIME.clear()).setAsPrimaryKey();
	private TypeDate referralDate = new TypeDate("referral_date").setFieldDescriptor(SpecialtyCourtBO.REFERRALDATE.clear());
	private TypeDate acceptedDate = new TypeDate("accepted_date").setFieldDescriptor(SpecialtyCourtBO.ACCEPTEDDATE.clear());
	private TypeDate notAcceptedDate = new TypeDate("not_accepted_date").setFieldDescriptor(SpecialtyCourtBO.NOTACCEPTEDDATE.clear());
	private TypeDate startingDate = new TypeDate("starting_date").setFieldDescriptor(SpecialtyCourtBO.STARTINGDATE.clear());
	private TypeDate completionDate = new TypeDate("completion_date").setFieldDescriptor(SpecialtyCourtBO.COMPLETIONDATE.clear());
	private TypeDate referralBackDate = new TypeDate("referral_back_date").setFieldDescriptor(SpecialtyCourtBO.REFERRALBACKDATE.clear());

	public SpecialtyCourtVO(String source) {
		super(SpecialtyCourtBO.TABLE, SpecialtyCourtBO.SYSTEM, SpecialtyCourtBO.CORIS_DISTRICT_DB.setSource("D"), SpecialtyCourtBO.CORIS_JUSTICE_DB.setSource("J"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(specialCourt);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(referralDate);
		this.getPropertyList().add(acceptedDate);
		this.getPropertyList().add(notAcceptedDate);
		this.getPropertyList().add(startingDate);
		this.getPropertyList().add(completionDate);
		this.getPropertyList().add(referralBackDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SpecialtyCourtBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SpecialtyCourtBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SpecialtyCourtBO.REFERRALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SpecialtyCourtBO.REFERRALDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SpecialtyCourtBO.ACCEPTEDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SpecialtyCourtBO.ACCEPTEDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SpecialtyCourtBO.NOTACCEPTEDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SpecialtyCourtBO.NOTACCEPTEDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SpecialtyCourtBO.STARTINGDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SpecialtyCourtBO.STARTINGDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SpecialtyCourtBO.COMPLETIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SpecialtyCourtBO.COMPLETIONDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SpecialtyCourtBO.REFERRALBACKDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SpecialtyCourtBO.REFERRALBACKDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}