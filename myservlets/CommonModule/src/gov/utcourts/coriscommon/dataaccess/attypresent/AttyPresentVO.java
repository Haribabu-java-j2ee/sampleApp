package gov.utcourts.coriscommon.dataaccess.attypresent;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AttyPresentVO extends BaseVO { 

	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(AttyPresentBO.CREATEDATETIME.clear()).setAsPrimaryKey();
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(AttyPresentBO.JUDGEID.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(AttyPresentBO.BARNUM.clear()).addForeignKey("attorney","bar_num",false).setAsPrimaryKey();
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(AttyPresentBO.BARSTATE.clear()).addForeignKey("attorney","bar_state",false).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(AttyPresentBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(AttyPresentBO.MEID.clear()).addForeignKey("case_me","me_id",false);

	public AttyPresentVO() {
		super(AttyPresentBO.TABLE, AttyPresentBO.SYSTEM, AttyPresentBO.CORIS_DISTRICT_DB.setSource("D"), AttyPresentBO.CORIS_JUSTICE_DB.setSource("J"), AttyPresentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AttyPresentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AttyPresentVO(String source) {
		super(AttyPresentBO.TABLE, AttyPresentBO.SYSTEM, AttyPresentBO.CORIS_DISTRICT_DB.setSource("D"), AttyPresentBO.CORIS_JUSTICE_DB.setSource("J"), AttyPresentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AttyPresentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
			((TypeDate) this.getPropertyList().get(AttyPresentBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AttyPresentBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}