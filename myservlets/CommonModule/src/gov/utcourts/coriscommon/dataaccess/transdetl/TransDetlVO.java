package gov.utcourts.coriscommon.dataaccess.transdetl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TransDetlVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(TransDetlBO.INTJOURNALNUM.clear()).addForeignKey("trans","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(TransDetlBO.TRANSNUM.clear()).addForeignKey("trans","trans_num",false).setAsPrimaryKey();
	private TypeString tenderType = new TypeString("tender_type").setFieldDescriptor(TransDetlBO.TENDERTYPE.clear()).setAsPrimaryKey();
	private TypeString tenderDescr = new TypeString("tender_descr").setFieldDescriptor(TransDetlBO.TENDERDESCR.clear()).setNullable();
	private TypeString checkCode = new TypeString("check_code").setFieldDescriptor(TransDetlBO.CHECKCODE.clear()).setNullable();
	private TypeBigDecimal revnAmt = new TypeBigDecimal("revn_amt").setFieldDescriptor(TransDetlBO.REVNAMT.clear());
	private TypeBigDecimal trustAmt = new TypeBigDecimal("trust_amt").setFieldDescriptor(TransDetlBO.TRUSTAMT.clear());

	public TransDetlVO() {
		super(TransDetlBO.TABLE, TransDetlBO.SYSTEM, TransDetlBO.CORIS_DISTRICT_DB.setSource("D"), TransDetlBO.CORIS_JUSTICE_DB.setSource("J"), TransDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TransDetlVO(String source) {
		super(TransDetlBO.TABLE, TransDetlBO.SYSTEM, TransDetlBO.CORIS_DISTRICT_DB.setSource("D"), TransDetlBO.CORIS_JUSTICE_DB.setSource("J"), TransDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(tenderType);
		this.getPropertyList().add(tenderDescr);
		this.getPropertyList().add(checkCode);
		this.getPropertyList().add(revnAmt);
		this.getPropertyList().add(trustAmt);
	}

}