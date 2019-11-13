package gov.utcourts.coriscommon.dataaccess.civilcaseold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CivilCaseOldVO extends BaseVO { 

	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(CivilCaseOldBO.ACTIONTYPE.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(CivilCaseOldBO.USERIDSRL.clear());
	private TypeDate changeDatetime = new TypeDate("change_datetime").setFieldDescriptor(CivilCaseOldBO.CHANGEDATETIME.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(CivilCaseOldBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CivilCaseOldBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CivilCaseOldBO.COURTTYPE.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CivilCaseOldBO.INTCASENUM.clear());
	private TypeInteger titleDefNum = new TypeInteger("title_def_num").setFieldDescriptor(CivilCaseOldBO.TITLEDEFNUM.clear()).setNullable();
	private TypeInteger titlePlaNum = new TypeInteger("title_pla_num").setFieldDescriptor(CivilCaseOldBO.TITLEPLANUM.clear()).setNullable();
	private TypeString title = new TypeString("title").setFieldDescriptor(CivilCaseOldBO.TITLE.clear()).setNullable();
	private TypeString divMinorChildren = new TypeString("div_minor_children").setFieldDescriptor(CivilCaseOldBO.DIVMINORCHILDREN.clear());
	private TypeString taxAcctNum = new TypeString("tax_acct_num").setFieldDescriptor(CivilCaseOldBO.TAXACCTNUM.clear()).setNullable();
	private TypeBigDecimal amtInControversy = new TypeBigDecimal("amt_in_controversy").setFieldDescriptor(CivilCaseOldBO.AMTINCONTROVERSY.clear()).setNullable();
	private TypeString adrEligible = new TypeString("adr_eligible").setFieldDescriptor(CivilCaseOldBO.ADRELIGIBLE.clear()).setNullable();
	private TypeString discoveryTier = new TypeString("discovery_tier").setFieldDescriptor(CivilCaseOldBO.DISCOVERYTIER.clear()).setNullable();

	public CivilCaseOldVO() {
		super(CivilCaseOldBO.TABLE, CivilCaseOldBO.SYSTEM, CivilCaseOldBO.CORIS_DISTRICT_DB.setSource("D"), CivilCaseOldBO.CORIS_JUSTICE_DB.setSource("J"), CivilCaseOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CivilCaseOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CivilCaseOldVO(String source) {
		super(CivilCaseOldBO.TABLE, CivilCaseOldBO.SYSTEM, CivilCaseOldBO.CORIS_DISTRICT_DB.setSource("D"), CivilCaseOldBO.CORIS_JUSTICE_DB.setSource("J"), CivilCaseOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CivilCaseOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(actionType);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(changeDatetime);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(titleDefNum);
		this.getPropertyList().add(titlePlaNum);
		this.getPropertyList().add(title);
		this.getPropertyList().add(divMinorChildren);
		this.getPropertyList().add(taxAcctNum);
		this.getPropertyList().add(amtInControversy);
		this.getPropertyList().add(adrEligible);
		this.getPropertyList().add(discoveryTier);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CivilCaseOldBO.CHANGEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CivilCaseOldBO.CHANGEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}