package gov.utcourts.coriscommon.dataaccess.crimcase;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CrimCaseVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CrimCaseBO.INTCASENUM.clear()).addForeignKey("bci","int_case_num",false).addForeignKey("bci_rejections","int_case_num",false).addForeignKey("bci_suspense","int_case_num",false).addForeignKey("corrections_data","int_case_num",false).addForeignKey("custody_history","int_case_num",false).addForeignKey("dopl_data","int_case_num",false).addForeignKey("dui_info","int_case_num",false).addForeignKey("fta_history","int_case_num",true).addForeignKey("sub_abuse_treatmt","int_case_num",false).addForeignKey("warrant","int_case_num",false).addForeignKey("warrant_retransmission","int_case_num",true).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString citNum = new TypeString("cit_num").setFieldDescriptor(CrimCaseBO.CITNUM.clear()).setNullable();
	private TypeString lea = new TypeString("lea").setFieldDescriptor(CrimCaseBO.LEA.clear()).setNullable();
	private TypeString leaCaseNum = new TypeString("lea_case_num").setFieldDescriptor(CrimCaseBO.LEACASENUM.clear()).setNullable();
	private TypeString prosecAgency = new TypeString("prosec_agency").setFieldDescriptor(CrimCaseBO.PROSECAGENCY.clear()).addForeignKey("gov_type","gov_code",false).setNullable();
	private TypeString prosecAgencyNum = new TypeString("prosec_agency_num").setFieldDescriptor(CrimCaseBO.PROSECAGENCYNUM.clear()).setNullable();
	private TypeInteger officerSrl = new TypeInteger("officer_srl").setFieldDescriptor(CrimCaseBO.OFFICERSRL.clear()).addForeignKey("officer","officer_srl",false).setNullable();
	private TypeString commVehicle = new TypeString("comm_vehicle").setFieldDescriptor(CrimCaseBO.COMMVEHICLE.clear()).setNullable();
	private TypeString hazMaterial = new TypeString("haz_material").setFieldDescriptor(CrimCaseBO.HAZMATERIAL.clear()).setNullable();
	private TypeString ftaStatus = new TypeString("fta_status").setFieldDescriptor(CrimCaseBO.FTASTATUS.clear()).setNullable();
	private TypeDate ftaOrderDate = new TypeDate("fta_order_date").setFieldDescriptor(CrimCaseBO.FTAORDERDATE.clear()).setNullable();
	private TypeDate ftaIssueDate = new TypeDate("fta_issue_date").setFieldDescriptor(CrimCaseBO.FTAISSUEDATE.clear()).setNullable();
	private TypeDate ftaClearDate = new TypeDate("fta_clear_date").setFieldDescriptor(CrimCaseBO.FTACLEARDATE.clear()).setNullable();
	private TypeDate delinqNoticeDate = new TypeDate("delinq_notice_date").setFieldDescriptor(CrimCaseBO.DELINQNOTICEDATE.clear()).setNullable();
	private TypeDate iiBeginDate = new TypeDate("ii_begin_date").setFieldDescriptor(CrimCaseBO.IIBEGINDATE.clear()).setNullable();
	private TypeDate iiEndDate = new TypeDate("ii_end_date").setFieldDescriptor(CrimCaseBO.IIENDDATE.clear()).setNullable();
	private TypeString sheriffNum = new TypeString("sheriff_num").setFieldDescriptor(CrimCaseBO.SHERIFFNUM.clear()).setNullable();
	private TypeString information = new TypeString("information").setFieldDescriptor(CrimCaseBO.INFORMATION.clear()).setNullable();
	private TypeString ftaClearFlag = new TypeString("fta_clear_flag").setFieldDescriptor(CrimCaseBO.FTACLEARFLAG.clear()).setNullable();
	private TypeString trialdenovo = new TypeString("trialdenovo").setFieldDescriptor(CrimCaseBO.TRIALDENOVO.clear()).setNullable();
	private TypeDate dlrvBeginDate = new TypeDate("dlrv_begin_date").setFieldDescriptor(CrimCaseBO.DLRVBEGINDATE.clear()).setNullable();
	private TypeDate dlrvEndDate = new TypeDate("dlrv_end_date").setFieldDescriptor(CrimCaseBO.DLRVENDDATE.clear()).setNullable();
	private TypeString occupants16up = new TypeString("occupants_16up").setFieldDescriptor(CrimCaseBO.OCCUPANTS16UP.clear()).setNullable();

	public CrimCaseVO() {
		super(CrimCaseBO.TABLE, CrimCaseBO.SYSTEM, CrimCaseBO.CORIS_DISTRICT_DB.setSource("D"), CrimCaseBO.CORIS_JUSTICE_DB.setSource("J"), CrimCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CrimCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CrimCaseVO(String source) {
		super(CrimCaseBO.TABLE, CrimCaseBO.SYSTEM, CrimCaseBO.CORIS_DISTRICT_DB.setSource("D"), CrimCaseBO.CORIS_JUSTICE_DB.setSource("J"), CrimCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CrimCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
			((TypeDate) this.getPropertyList().get(CrimCaseBO.FTAORDERDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseBO.FTAORDERDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseBO.FTAISSUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseBO.FTAISSUEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseBO.FTACLEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseBO.FTACLEARDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseBO.DELINQNOTICEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseBO.DELINQNOTICEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseBO.IIBEGINDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseBO.IIBEGINDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseBO.IIENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseBO.IIENDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseBO.DLRVBEGINDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseBO.DLRVBEGINDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CrimCaseBO.DLRVENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CrimCaseBO.DLRVENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}