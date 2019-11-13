package gov.utcourts.coriscommon.dataaccess.prelimjudge;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PrelimJudgeVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PrelimJudgeBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeDate appearDate = new TypeDate("appear_date").setFieldDescriptor(PrelimJudgeBO.APPEARDATE.clear()).setAsPrimaryKey();
	private TypeString am = new TypeString("am").setFieldDescriptor(PrelimJudgeBO.AM.clear()).setAsPrimaryKey();
	private TypeString courtroomCode = new TypeString("courtroom_code").setFieldDescriptor(PrelimJudgeBO.COURTROOMCODE.clear()).setAsPrimaryKey();
	private TypeInteger inCustody = new TypeInteger("in_custody").setFieldDescriptor(PrelimJudgeBO.INCUSTODY.clear()).setNullable();
	private TypeInteger outOfCustody = new TypeInteger("out_of_custody").setFieldDescriptor(PrelimJudgeBO.OUTOFCUSTODY.clear()).setNullable();
	private TypeInteger noWitness = new TypeInteger("no_witness").setFieldDescriptor(PrelimJudgeBO.NOWITNESS.clear()).setNullable();

	public PrelimJudgeVO() {
		super(PrelimJudgeBO.TABLE, PrelimJudgeBO.SYSTEM, PrelimJudgeBO.CORIS_DISTRICT_DB.setSource("D"), PrelimJudgeBO.CORIS_JUSTICE_DB.setSource("J"), PrelimJudgeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrelimJudgeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PrelimJudgeVO(String source) {
		super(PrelimJudgeBO.TABLE, PrelimJudgeBO.SYSTEM, PrelimJudgeBO.CORIS_DISTRICT_DB.setSource("D"), PrelimJudgeBO.CORIS_JUSTICE_DB.setSource("J"), PrelimJudgeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrelimJudgeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(appearDate);
		this.getPropertyList().add(am);
		this.getPropertyList().add(courtroomCode);
		this.getPropertyList().add(inCustody);
		this.getPropertyList().add(outOfCustody);
		this.getPropertyList().add(noWitness);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PrelimJudgeBO.APPEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PrelimJudgeBO.APPEARDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}