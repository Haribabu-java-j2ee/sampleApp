package gov.utcourts.coriscommon.dataaccess.crimcaseold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CrimCaseOldVO extends BaseVO { 

	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(CrimCaseOldBO.ACTIONTYPE.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(CrimCaseOldBO.USERIDSRL.clear());
	private TypeDate changeDatetime = new TypeDate("change_datetime").setFieldDescriptor(CrimCaseOldBO.CHANGEDATETIME.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(CrimCaseOldBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CrimCaseOldBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CrimCaseOldBO.COURTTYPE.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CrimCaseOldBO.INTCASENUM.clear());
	private TypeString citNum = new TypeString("cit_num").setFieldDescriptor(CrimCaseOldBO.CITNUM.clear()).setNullable();
	private TypeString lea = new TypeString("lea").setFieldDescriptor(CrimCaseOldBO.LEA.clear()).setNullable();
	private TypeString leaCaseNum = new TypeString("lea_case_num").setFieldDescriptor(CrimCaseOldBO.LEACASENUM.clear()).setNullable();
	private TypeString prosecAgency = new TypeString("prosec_agency").setFieldDescriptor(CrimCaseOldBO.PROSECAGENCY.clear()).setNullable();
	private TypeString prosecAgencyNum = new TypeString("prosec_agency_num").setFieldDescriptor(CrimCaseOldBO.PROSECAGENCYNUM.clear()).setNullable();
	private TypeInteger officerSrl = new TypeInteger("officer_srl").setFieldDescriptor(CrimCaseOldBO.OFFICERSRL.clear()).setNullable();
	private TypeString commVehicle = new TypeString("comm_vehicle").setFieldDescriptor(CrimCaseOldBO.COMMVEHICLE.clear()).setNullable();
	private TypeString hazMaterial = new TypeString("haz_material").setFieldDescriptor(CrimCaseOldBO.HAZMATERIAL.clear()).setNullable();
	private TypeString ftaStatus = new TypeString("fta_status").setFieldDescriptor(CrimCaseOldBO.FTASTATUS.clear()).setNullable();
	private TypeDate ftaOrderDate = new TypeDate("fta_order_date").setFieldDescriptor(CrimCaseOldBO.FTAORDERDATE.clear()).setNullable();
	private TypeDate ftaIssueDate = new TypeDate("fta_issue_date").setFieldDescriptor(CrimCaseOldBO.FTAISSUEDATE.clear()).setNullable();
	private TypeDate ftaClearDate = new TypeDate("fta_clear_date").setFieldDescriptor(CrimCaseOldBO.FTACLEARDATE.clear()).setNullable();
	private TypeDate delinqNoticeDate = new TypeDate("delinq_notice_date").setFieldDescriptor(CrimCaseOldBO.DELINQNOTICEDATE.clear()).setNullable();
	private TypeDate iiBeginDate = new TypeDate("ii_begin_date").setFieldDescriptor(CrimCaseOldBO.IIBEGINDATE.clear()).setNullable();
	private TypeDate iiEndDate = new TypeDate("ii_end_date").setFieldDescriptor(CrimCaseOldBO.IIENDDATE.clear()).setNullable();
	private TypeString sheriffNum = new TypeString("sheriff_num").setFieldDescriptor(CrimCaseOldBO.SHERIFFNUM.clear()).setNullable();
	private TypeString information = new TypeString("information").setFieldDescriptor(CrimCaseOldBO.INFORMATION.clear()).setNullable();
	private TypeString ftaClearFlag = new TypeString("fta_clear_flag").setFieldDescriptor(CrimCaseOldBO.FTACLEARFLAG.clear()).setNullable();
	private TypeString trialdenovo = new TypeString("trialdenovo").setFieldDescriptor(CrimCaseOldBO.TRIALDENOVO.clear()).setNullable();
	private TypeDate dlrvBeginDate = new TypeDate("dlrv_begin_date").setFieldDescriptor(CrimCaseOldBO.DLRVBEGINDATE.clear()).setNullable();
	private TypeDate dlrvEndDate = new TypeDate("dlrv_end_date").setFieldDescriptor(CrimCaseOldBO.DLRVENDDATE.clear()).setNullable();
	private TypeString occupants16up = new TypeString("occupants_16up").setFieldDescriptor(CrimCaseOldBO.OCCUPANTS16UP.clear()).setNullable();

	public CrimCaseOldVO() {
		super(CrimCaseOldBO.TABLE, CrimCaseOldBO.SYSTEM, CrimCaseOldBO.CORIS_DISTRICT_DB.setSource("D"), CrimCaseOldBO.CORIS_JUSTICE_DB.setSource("J"), CrimCaseOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CrimCaseOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CrimCaseOldVO(String source) {
		super(CrimCaseOldBO.TABLE, CrimCaseOldBO.SYSTEM, CrimCaseOldBO.CORIS_DISTRICT_DB.setSource("D"), CrimCaseOldBO.CORIS_JUSTICE_DB.setSource("J"), CrimCaseOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CrimCaseOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
		this.getPropertyList().add(citNum);
		this.getPropertyList().add(lea);
		this.getPropertyList().add(leaCaseNum);
		this.getPropertyList().add(prosecAgency);
		this.getPropertyList().add(prosecAgencyNum);
		this.getPropertyList().add(officerSrl);
		this.getPropertyList().add(commVehicle);
		this.getPropertyList().add(hazMaterial);
		this.getPropertyList().add(ftaStatus);
		this.getPropertyList().add(ftaOrderDate);
		this.getPropertyList().add(ftaIssueDate);
		this.getPropertyList().add(ftaClearDate);
		this.getPropertyList().add(delinqNoticeDate);
		this.getPropertyList().add(iiBeginDate);
		this.getPropertyList().add(iiEndDate);
		this.getPropertyList().add(sheriffNum);
		this.getPropertyList().add(information);
		this.getPropertyList().add(ftaClearFlag);
		this.getPropertyList().add(trialdenovo);
		this.getPropertyList().add(dlrvBeginDate);
		this.getPropertyList().add(dlrvEndDate);
		this.getPropertyList().add(occupants16up);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.CHANGEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.CHANGEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.FTAORDERDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.FTAORDERDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.FTAISSUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.FTAISSUEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.FTACLEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.FTACLEARDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.DELINQNOTICEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.DELINQNOTICEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.IIBEGINDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.IIBEGINDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.IIENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.IIENDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.DLRVBEGINDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.DLRVBEGINDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseOldBO.DLRVENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseOldBO.DLRVENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}