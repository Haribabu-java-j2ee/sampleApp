package gov.utcourts.coriscommon.dataaccess.drugcourt;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DrugCourtVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DrugCourtBO.INTCASENUM.clear());
	private TypeDate referralDate = new TypeDate("referral_date").setFieldDescriptor(DrugCourtBO.REFERRALDATE.clear());
	private TypeDate startingDate = new TypeDate("starting_date").setFieldDescriptor(DrugCourtBO.STARTINGDATE.clear());
	private TypeDate completionDate = new TypeDate("completion_date").setFieldDescriptor(DrugCourtBO.COMPLETIONDATE.clear());
	private TypeDate referralBackDate = new TypeDate("referral_back_date").setFieldDescriptor(DrugCourtBO.REFERRALBACKDATE.clear());

	public DrugCourtVO(String source) {
		super(DrugCourtBO.TABLE, DrugCourtBO.SYSTEM, DrugCourtBO.CORIS_DISTRICT_DB.setSource("D"), DrugCourtBO.CORIS_JUSTICE_DB.setSource("J"));
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
			((TypeDate) this.getPropertyList().get(DrugCourtBO.REFERRALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DrugCourtBO.REFERRALDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DrugCourtBO.STARTINGDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DrugCourtBO.STARTINGDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DrugCourtBO.COMPLETIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DrugCourtBO.COMPLETIONDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DrugCourtBO.REFERRALBACKDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DrugCourtBO.REFERRALBACKDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}