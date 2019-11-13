package gov.utcourts.coriscommon.dataaccess.hearingctl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class HearingCtlVO extends BaseVO { 

	private TypeString hearingCategory = new TypeString("hearing_category").setFieldDescriptor(HearingCtlBO.HEARINGCATEGORY.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(HearingCtlBO.DESCR.clear()).setNullable();
	private TypeString type = new TypeString("type").setFieldDescriptor(HearingCtlBO.TYPE.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(HearingCtlBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(HearingCtlBO.REMOVEDFLAG.clear());
	private TypeString caseTypeCat = new TypeString("case_type_cat").setFieldDescriptor(HearingCtlBO.CASETYPECAT.clear());

	public HearingCtlVO() {
		super(HearingCtlBO.TABLE, HearingCtlBO.SYSTEM, HearingCtlBO.CORIS_DISTRICT_DB.setSource("D"), HearingCtlBO.CORIS_JUSTICE_DB.setSource("J"), HearingCtlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HearingCtlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public HearingCtlVO(String source) {
		super(HearingCtlBO.TABLE, HearingCtlBO.SYSTEM, HearingCtlBO.CORIS_DISTRICT_DB.setSource("D"), HearingCtlBO.CORIS_JUSTICE_DB.setSource("J"), HearingCtlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HearingCtlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(hearingCategory);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(type);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(caseTypeCat);
	}

}