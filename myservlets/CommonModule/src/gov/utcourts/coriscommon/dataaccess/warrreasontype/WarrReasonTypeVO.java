package gov.utcourts.coriscommon.dataaccess.warrreasontype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class WarrReasonTypeVO extends BaseVO { 

	private TypeString warrReasonCode = new TypeString("warr_reason_code").setFieldDescriptor(WarrReasonTypeBO.WARRREASONCODE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(WarrReasonTypeBO.LOCNCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(WarrReasonTypeBO.COURTTYPE.clear()).setNullable().setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(WarrReasonTypeBO.DESCR.clear());
	private TypeString type = new TypeString("type").setFieldDescriptor(WarrReasonTypeBO.TYPE.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(WarrReasonTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(WarrReasonTypeBO.REMOVEDFLAG.clear());
	private TypeString shortDescr = new TypeString("short_descr").setFieldDescriptor(WarrReasonTypeBO.SHORTDESCR.clear());

	public WarrReasonTypeVO() {
		super(WarrReasonTypeBO.TABLE, WarrReasonTypeBO.SYSTEM, WarrReasonTypeBO.CORIS_DISTRICT_DB.setSource("D"), WarrReasonTypeBO.CORIS_JUSTICE_DB.setSource("J"), WarrReasonTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarrReasonTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public WarrReasonTypeVO(String source) {
		super(WarrReasonTypeBO.TABLE, WarrReasonTypeBO.SYSTEM, WarrReasonTypeBO.CORIS_DISTRICT_DB.setSource("D"), WarrReasonTypeBO.CORIS_JUSTICE_DB.setSource("J"), WarrReasonTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarrReasonTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(warrReasonCode);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(type);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(shortDescr);
	}

}