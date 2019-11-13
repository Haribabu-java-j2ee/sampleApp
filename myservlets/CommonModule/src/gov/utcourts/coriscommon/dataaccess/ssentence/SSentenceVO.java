package gov.utcourts.coriscommon.dataaccess.ssentence;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SSentenceVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SSentenceBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SSentenceBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SSentenceBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SSentenceBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SSentenceBO.SOPERATION.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SSentenceBO.INTCASENUM.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SSentenceBO.PARTYNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(SSentenceBO.PARTYCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(SSentenceBO.SEQ.clear()).setNullable().setAsPrimaryKey();
	private TypeString sentCode = new TypeString("sent_code").setFieldDescriptor(SSentenceBO.SENTCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeString value = new TypeString("value").setFieldDescriptor(SSentenceBO.VALUE.clear()).setNullable();
	private TypeString units = new TypeString("units").setFieldDescriptor(SSentenceBO.UNITS.clear()).setNullable();
	private TypeBigDecimal lieuOfAmt = new TypeBigDecimal("lieu_of_amt").setFieldDescriptor(SSentenceBO.LIEUOFAMT.clear()).setNullable();

	public SSentenceVO() {
		super(SSentenceBO.TABLE, SSentenceBO.SYSTEM, SSentenceBO.CORIS_DISTRICT_DB.setSource("D"), SSentenceBO.CORIS_JUSTICE_DB.setSource("J"), SSentenceBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SSentenceBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SSentenceVO(String source) {
		super(SSentenceBO.TABLE, SSentenceBO.SYSTEM, SSentenceBO.CORIS_DISTRICT_DB.setSource("D"), SSentenceBO.CORIS_JUSTICE_DB.setSource("J"), SSentenceBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SSentenceBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(sentCode);
		this.getPropertyList().add(value);
		this.getPropertyList().add(units);
		this.getPropertyList().add(lieuOfAmt);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SSentenceBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SSentenceBO.SDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}