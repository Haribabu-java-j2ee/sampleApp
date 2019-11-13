package gov.utcourts.coriscommon.dataaccess.sattypresent;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SAttyPresentVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SAttyPresentBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SAttyPresentBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SAttyPresentBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SAttyPresentBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SAttyPresentBO.SOPERATION.clear()).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(SAttyPresentBO.CREATEDATETIME.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(SAttyPresentBO.JUDGEID.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(SAttyPresentBO.BARNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(SAttyPresentBO.BARSTATE.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SAttyPresentBO.INTCASENUM.clear()).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(SAttyPresentBO.MEID.clear()).setNullable();

	public SAttyPresentVO() {
		super(SAttyPresentBO.TABLE, SAttyPresentBO.SYSTEM, SAttyPresentBO.CORIS_DISTRICT_DB.setSource("D"), SAttyPresentBO.CORIS_JUSTICE_DB.setSource("J"), SAttyPresentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAttyPresentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SAttyPresentVO(String source) {
		super(SAttyPresentBO.TABLE, SAttyPresentBO.SYSTEM, SAttyPresentBO.CORIS_DISTRICT_DB.setSource("D"), SAttyPresentBO.CORIS_JUSTICE_DB.setSource("J"), SAttyPresentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAttyPresentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(barState);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(meId);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SAttyPresentBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAttyPresentBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAttyPresentBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAttyPresentBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}