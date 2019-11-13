package gov.utcourts.coriscommon.dataaccess.ssummaryevent;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SSummaryEventVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SSummaryEventBO.SINTCASENUM.clear()).setNullable();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SSummaryEventBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SSummaryEventBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SSummaryEventBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SSummaryEventBO.SOPERATION.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SSummaryEventBO.INTCASENUM.clear()).setNullable();
	private TypeDate eventDatetime = new TypeDate("event_datetime").setFieldDescriptor(SSummaryEventBO.EVENTDATETIME.clear()).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(SSummaryEventBO.CREATEDATETIME.clear()).setNullable();
	private TypeString funcId = new TypeString("func_id").setFieldDescriptor(SSummaryEventBO.FUNCID.clear()).setNullable();
	private TypeString displayFlag = new TypeString("display_flag").setFieldDescriptor(SSummaryEventBO.DISPLAYFLAG.clear()).setNullable();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(SSummaryEventBO.REMOVEDFLAG.clear()).setNullable();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(SSummaryEventBO.DESCR.clear()).setNullable();
	private TypeString key1 = new TypeString("key_1").setFieldDescriptor(SSummaryEventBO.KEY1.clear()).setNullable();
	private TypeString key2 = new TypeString("key_2").setFieldDescriptor(SSummaryEventBO.KEY2.clear()).setNullable();
	private TypeString key3 = new TypeString("key_3").setFieldDescriptor(SSummaryEventBO.KEY3.clear()).setNullable();
	private TypeString key4 = new TypeString("key_4").setFieldDescriptor(SSummaryEventBO.KEY4.clear()).setNullable();

	public SSummaryEventVO() {
		super(SSummaryEventBO.TABLE, SSummaryEventBO.SYSTEM, SSummaryEventBO.CORIS_DISTRICT_DB.setSource("D"), SSummaryEventBO.CORIS_JUSTICE_DB.setSource("J"), SSummaryEventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SSummaryEventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SSummaryEventVO(String source) {
		super(SSummaryEventBO.TABLE, SSummaryEventBO.SYSTEM, SSummaryEventBO.CORIS_DISTRICT_DB.setSource("D"), SSummaryEventBO.CORIS_JUSTICE_DB.setSource("J"), SSummaryEventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SSummaryEventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(eventDatetime);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(funcId);
		this.getPropertyList().add(displayFlag);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(key1);
		this.getPropertyList().add(key2);
		this.getPropertyList().add(key3);
		this.getPropertyList().add(key4);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SSummaryEventBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SSummaryEventBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SSummaryEventBO.EVENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SSummaryEventBO.EVENTDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SSummaryEventBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SSummaryEventBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}