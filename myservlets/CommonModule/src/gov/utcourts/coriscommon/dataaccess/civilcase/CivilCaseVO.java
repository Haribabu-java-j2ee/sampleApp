package gov.utcourts.coriscommon.dataaccess.civilcase;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CivilCaseVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CivilCaseBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger titleDefNum = new TypeInteger("title_def_num").setFieldDescriptor(CivilCaseBO.TITLEDEFNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger titlePlaNum = new TypeInteger("title_pla_num").setFieldDescriptor(CivilCaseBO.TITLEPLANUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeString title = new TypeString("title").setFieldDescriptor(CivilCaseBO.TITLE.clear()).setNullable();
	private TypeString divMinorChildren = new TypeString("div_minor_children").setFieldDescriptor(CivilCaseBO.DIVMINORCHILDREN.clear());
	private TypeString taxAcctNum = new TypeString("tax_acct_num").setFieldDescriptor(CivilCaseBO.TAXACCTNUM.clear()).setNullable();
	private TypeBigDecimal amtInControversy = new TypeBigDecimal("amt_in_controversy").setFieldDescriptor(CivilCaseBO.AMTINCONTROVERSY.clear()).setNullable();
	private TypeString adrEligible = new TypeString("adr_eligible").setFieldDescriptor(CivilCaseBO.ADRELIGIBLE.clear()).setNullable();
	private TypeString discoveryTier = new TypeString("discovery_tier").setFieldDescriptor(CivilCaseBO.DISCOVERYTIER.clear()).setNullable();
	private TypeString filingFeeStatus = new TypeString("filing_fee_status").setFieldDescriptor(CivilCaseBO.FILINGFEESTATUS.clear()).setNullable();

	public CivilCaseVO() {
		super(CivilCaseBO.TABLE, CivilCaseBO.SYSTEM, CivilCaseBO.CORIS_DISTRICT_DB.setSource("D"), CivilCaseBO.CORIS_JUSTICE_DB.setSource("J"), CivilCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CivilCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CivilCaseVO(String source) {
		super(CivilCaseBO.TABLE, CivilCaseBO.SYSTEM, CivilCaseBO.CORIS_DISTRICT_DB.setSource("D"), CivilCaseBO.CORIS_JUSTICE_DB.setSource("J"), CivilCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CivilCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(titleDefNum);
		this.getPropertyList().add(titlePlaNum);
		this.getPropertyList().add(title);
		this.getPropertyList().add(divMinorChildren);
		this.getPropertyList().add(taxAcctNum);
		this.getPropertyList().add(amtInControversy);
		this.getPropertyList().add(adrEligible);
		this.getPropertyList().add(discoveryTier);
		this.getPropertyList().add(filingFeeStatus);
	}

}