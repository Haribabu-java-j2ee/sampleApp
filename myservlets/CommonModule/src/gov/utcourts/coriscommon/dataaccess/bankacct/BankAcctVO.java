package gov.utcourts.coriscommon.dataaccess.bankacct;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BankAcctVO extends BaseVO { 

	private TypeString bankAcctNum = new TypeString("bank_acct_num").setFieldDescriptor(BankAcctBO.BANKACCTNUM.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(BankAcctBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(BankAcctBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setAsPrimaryKey();
	private TypeString type = new TypeString("type").setFieldDescriptor(BankAcctBO.TYPE.clear());
	private TypeBigDecimal beginBal = new TypeBigDecimal("begin_bal").setFieldDescriptor(BankAcctBO.BEGINBAL.clear());
	private TypeString closedFlag = new TypeString("closed_flag").setFieldDescriptor(BankAcctBO.CLOSEDFLAG.clear());
	private TypeString bankName = new TypeString("bank_name").setFieldDescriptor(BankAcctBO.BANKNAME.clear()).setNullable();
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(BankAcctBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(BankAcctBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(BankAcctBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(BankAcctBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(BankAcctBO.ZIPCODE.clear()).setNullable();
	private TypeString routingNum = new TypeString("routing_num").setFieldDescriptor(BankAcctBO.ROUTINGNUM.clear()).setNullable();
	private TypeString transitNum = new TypeString("transit_num").setFieldDescriptor(BankAcctBO.TRANSITNUM.clear()).setNullable();
	private TypeInteger nextCheckNum = new TypeInteger("next_check_num").setFieldDescriptor(BankAcctBO.NEXTCHECKNUM.clear()).setNullable();

	public BankAcctVO() {
		super(BankAcctBO.TABLE, BankAcctBO.SYSTEM, BankAcctBO.CORIS_DISTRICT_DB.setSource("D"), BankAcctBO.CORIS_JUSTICE_DB.setSource("J"), BankAcctBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BankAcctBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BankAcctVO(String source) {
		super(BankAcctBO.TABLE, BankAcctBO.SYSTEM, BankAcctBO.CORIS_DISTRICT_DB.setSource("D"), BankAcctBO.CORIS_JUSTICE_DB.setSource("J"), BankAcctBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BankAcctBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bankAcctNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(type);
		this.getPropertyList().add(beginBal);
		this.getPropertyList().add(closedFlag);
		this.getPropertyList().add(bankName);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(address2);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
		this.getPropertyList().add(routingNum);
		this.getPropertyList().add(transitNum);
		this.getPropertyList().add(nextCheckNum);
	}

}