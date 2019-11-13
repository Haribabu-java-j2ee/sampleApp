package gov.utcourts.coriscommon.dataaccess.hearingcat;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class HearingCatVO extends BaseVO { 

	private TypeString hearingCategory = new TypeString("hearing_category").setFieldDescriptor(HearingCatBO.HEARINGCATEGORY.clear()).setAsPrimaryKey();
	private TypeString caseTypeCategory = new TypeString("case_type_category").setFieldDescriptor(HearingCatBO.CASETYPECATEGORY.clear()).setAsPrimaryKey();

	public HearingCatVO(String source) {
		super(HearingCatBO.TABLE, HearingCatBO.SYSTEM, HearingCatBO.CORIS_DISTRICT_DB.setSource("D"), HearingCatBO.CORIS_JUSTICE_DB.setSource("J"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(hearingCategory);
		this.getPropertyList().add(caseTypeCategory);
	}

}