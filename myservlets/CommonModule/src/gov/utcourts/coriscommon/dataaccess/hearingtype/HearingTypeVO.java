package gov.utcourts.coriscommon.dataaccess.hearingtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class HearingTypeVO extends BaseVO { 

	private TypeString hearingCode = new TypeString("hearing_code").setFieldDescriptor(HearingTypeBO.HEARINGCODE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(HearingTypeBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(HearingTypeBO.COURTTYPE.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(HearingTypeBO.DESCR.clear()).setNullable();
	private TypeString category = new TypeString("category").setFieldDescriptor(HearingTypeBO.CATEGORY.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(HearingTypeBO.REMOVEDFLAG.clear());
	private TypeString defltHearingLen = new TypeString("deflt_hearing_len").setFieldDescriptor(HearingTypeBO.DEFLTHEARINGLEN.clear());

	public HearingTypeVO() {
		super(HearingTypeBO.TABLE, HearingTypeBO.SYSTEM, HearingTypeBO.CORIS_DISTRICT_DB.setSource("D"), HearingTypeBO.CORIS_JUSTICE_DB.setSource("J"), HearingTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HearingTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public HearingTypeVO(String source) {
		super(HearingTypeBO.TABLE, HearingTypeBO.SYSTEM, HearingTypeBO.CORIS_DISTRICT_DB.setSource("D"), HearingTypeBO.CORIS_JUSTICE_DB.setSource("J"), HearingTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HearingTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(hearingCode);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(category);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(defltHearingLen);
	}

}