package gov.utcourts.coriscommon.dataaccess.checkbatchdetl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CheckBatchDetlVO extends BaseVO { 

	private TypeInteger batchNum = new TypeInteger("batch_num").setFieldDescriptor(CheckBatchDetlBO.BATCHNUM.clear()).addForeignKey("check_batch","batch_num",false).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CheckBatchDetlBO.LOCNCODE.clear()).addForeignKey("check_batch","locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CheckBatchDetlBO.COURTTYPE.clear()).addForeignKey("check_batch","court_type",false).setAsPrimaryKey();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(CheckBatchDetlBO.ACCTNUM.clear()).addForeignKey("acct_trust","acct_num",false).setAsPrimaryKey();
	private TypeBigDecimal checkAmt = new TypeBigDecimal("check_amt").setFieldDescriptor(CheckBatchDetlBO.CHECKAMT.clear());
	private TypeString checkStubDescr = new TypeString("check_stub_descr").setFieldDescriptor(CheckBatchDetlBO.CHECKSTUBDESCR.clear()).setNullable();
	private TypeInteger payeePartyNum = new TypeInteger("payee_party_num").setFieldDescriptor(CheckBatchDetlBO.PAYEEPARTYNUM.clear());
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(CheckBatchDetlBO.FIRSTNAME.clear()).setNullable();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(CheckBatchDetlBO.LASTNAME.clear()).setNullable();
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(CheckBatchDetlBO.ADDRESS1.clear()).setNullable();
	private TypeString cityStateZip = new TypeString("city_state_zip").setFieldDescriptor(CheckBatchDetlBO.CITYSTATEZIP.clear()).setNullable();

	public CheckBatchDetlVO() {
		super(CheckBatchDetlBO.TABLE, CheckBatchDetlBO.SYSTEM, CheckBatchDetlBO.CORIS_DISTRICT_DB.setSource("D"), CheckBatchDetlBO.CORIS_JUSTICE_DB.setSource("J"), CheckBatchDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CheckBatchDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CheckBatchDetlVO(String source) {
		super(CheckBatchDetlBO.TABLE, CheckBatchDetlBO.SYSTEM, CheckBatchDetlBO.CORIS_DISTRICT_DB.setSource("D"), CheckBatchDetlBO.CORIS_JUSTICE_DB.setSource("J"), CheckBatchDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CheckBatchDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(batchNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(checkAmt);
		this.getPropertyList().add(checkStubDescr);
		this.getPropertyList().add(payeePartyNum);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(cityStateZip);
	}

}