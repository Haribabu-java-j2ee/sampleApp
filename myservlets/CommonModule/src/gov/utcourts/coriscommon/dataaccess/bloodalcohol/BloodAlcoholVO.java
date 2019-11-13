package gov.utcourts.coriscommon.dataaccess.bloodalcohol;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BloodAlcoholVO extends BaseVO { 

	private TypeInteger baId = new TypeInteger("ba_id").setFieldDescriptor(BloodAlcoholBO.BAID.clear()).addForeignKey("dui_info","blood_alcohol",true).setAsPrimaryKey();
	private TypeString baDescr = new TypeString("ba_descr").setFieldDescriptor(BloodAlcoholBO.BADESCR.clear());
	private TypeInteger baSortOrder = new TypeInteger("ba_sort_order").setFieldDescriptor(BloodAlcoholBO.BASORTORDER.clear());

	public BloodAlcoholVO() {
		super(BloodAlcoholBO.TABLE, BloodAlcoholBO.SYSTEM, BloodAlcoholBO.CORIS_DISTRICT_DB.setSource("D"), BloodAlcoholBO.CORIS_JUSTICE_DB.setSource("J"), BloodAlcoholBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BloodAlcoholBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BloodAlcoholVO(String source) {
		super(BloodAlcoholBO.TABLE, BloodAlcoholBO.SYSTEM, BloodAlcoholBO.CORIS_DISTRICT_DB.setSource("D"), BloodAlcoholBO.CORIS_JUSTICE_DB.setSource("J"), BloodAlcoholBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BloodAlcoholBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(baId);
		this.getPropertyList().add(baDescr);
		this.getPropertyList().add(baSortOrder);
	}

}