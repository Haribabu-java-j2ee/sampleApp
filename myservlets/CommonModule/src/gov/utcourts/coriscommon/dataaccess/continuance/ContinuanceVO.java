package gov.utcourts.coriscommon.dataaccess.continuance;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ContinuanceVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(ContinuanceBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate rulingDatetime = new TypeDate("ruling_datetime").setFieldDescriptor(ContinuanceBO.RULINGDATETIME.clear()).setAsPrimaryKey();
	private TypeString whoseMotion = new TypeString("whose_motion").setFieldDescriptor(ContinuanceBO.WHOSEMOTION.clear());
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(ContinuanceBO.JUDGEID.clear()).setNullable();
	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(ContinuanceBO.BARNUM.clear()).setNullable();
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(ContinuanceBO.BARSTATE.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(ContinuanceBO.PARTYNUM.clear()).setNullable();
	private TypeString reason = new TypeString("reason").setFieldDescriptor(ContinuanceBO.REASON.clear()).setNullable();
	private TypeString ruling = new TypeString("ruling").setFieldDescriptor(ContinuanceBO.RULING.clear()).setNullable();
	private TypeString argued = new TypeString("argued").setFieldDescriptor(ContinuanceBO.ARGUED.clear()).setNullable();

	public ContinuanceVO() {
		super(ContinuanceBO.TABLE, ContinuanceBO.SYSTEM, ContinuanceBO.CORIS_DISTRICT_DB.setSource("D"), ContinuanceBO.CORIS_JUSTICE_DB.setSource("J"), ContinuanceBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ContinuanceBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ContinuanceVO(String source) {
		super(ContinuanceBO.TABLE, ContinuanceBO.SYSTEM, ContinuanceBO.CORIS_DISTRICT_DB.setSource("D"), ContinuanceBO.CORIS_JUSTICE_DB.setSource("J"), ContinuanceBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ContinuanceBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(rulingDatetime);
		this.getPropertyList().add(whoseMotion);
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(barState);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(reason);
		this.getPropertyList().add(ruling);
		this.getPropertyList().add(argued);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(ContinuanceBO.RULINGDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(ContinuanceBO.RULINGDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}