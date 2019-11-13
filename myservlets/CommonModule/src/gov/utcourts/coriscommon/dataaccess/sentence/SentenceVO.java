package gov.utcourts.coriscommon.dataaccess.sentence;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SentenceVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SentenceBO.INTCASENUM.clear()).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SentenceBO.PARTYNUM.clear()).setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(SentenceBO.PARTYCODE.clear()).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(SentenceBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString sentCode = new TypeString("sent_code").setFieldDescriptor(SentenceBO.SENTCODE.clear()).addForeignKey("sent_type","sent_code",false).setAsPrimaryKey();
	private TypeString value = new TypeString("value").setFieldDescriptor(SentenceBO.VALUE.clear());
	private TypeString units = new TypeString("units").setFieldDescriptor(SentenceBO.UNITS.clear()).setNullable();
	private TypeBigDecimal lieuOfAmt = new TypeBigDecimal("lieu_of_amt").setFieldDescriptor(SentenceBO.LIEUOFAMT.clear()).setNullable();

	public SentenceVO() {
		super(SentenceBO.TABLE, SentenceBO.SYSTEM, SentenceBO.CORIS_DISTRICT_DB.setSource("D"), SentenceBO.CORIS_JUSTICE_DB.setSource("J"), SentenceBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SentenceBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SentenceVO(String source) {
		super(SentenceBO.TABLE, SentenceBO.SYSTEM, SentenceBO.CORIS_DISTRICT_DB.setSource("D"), SentenceBO.CORIS_JUSTICE_DB.setSource("J"), SentenceBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SentenceBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(sentCode);
		this.getPropertyList().add(value);
		this.getPropertyList().add(units);
		this.getPropertyList().add(lieuOfAmt);
	}

}