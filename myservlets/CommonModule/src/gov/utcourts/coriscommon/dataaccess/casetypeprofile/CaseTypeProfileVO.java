package gov.utcourts.coriscommon.dataaccess.casetypeprofile;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseTypeProfileVO extends BaseVO { 

	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(CaseTypeProfileBO.CASETYPE.clear()).addForeignKey("case_type","case_type",false).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CaseTypeProfileBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CaseTypeProfileBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setAsPrimaryKey();
	private TypeString caseNumCode = new TypeString("case_num_code").setFieldDescriptor(CaseTypeProfileBO.CASENUMCODE.clear());
	private TypeString judgeAssn = new TypeString("judge_assn").setFieldDescriptor(CaseTypeProfileBO.JUDGEASSN.clear());
	private TypeString commAssn = new TypeString("comm_assn").setFieldDescriptor(CaseTypeProfileBO.COMMASSN.clear());
	private TypeString lbl = new TypeString("lbl").setFieldDescriptor(CaseTypeProfileBO.LBL.clear());
	private TypeString conv = new TypeString("conv").setFieldDescriptor(CaseTypeProfileBO.CONV.clear());
	private TypeString cbn = new TypeString("cbn").setFieldDescriptor(CaseTypeProfileBO.CBN.clear());
	private TypeString mandLbl = new TypeString("mand_lbl").setFieldDescriptor(CaseTypeProfileBO.MANDLBL.clear());
	private TypeString mandConv = new TypeString("mand_conv").setFieldDescriptor(CaseTypeProfileBO.MANDCONV.clear());
	private TypeString mandCbn = new TypeString("mand_cbn").setFieldDescriptor(CaseTypeProfileBO.MANDCBN.clear());

	public CaseTypeProfileVO() {
		super(CaseTypeProfileBO.TABLE, CaseTypeProfileBO.SYSTEM, CaseTypeProfileBO.CORIS_DISTRICT_DB.setSource("D"), CaseTypeProfileBO.CORIS_JUSTICE_DB.setSource("J"), CaseTypeProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseTypeProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseTypeProfileVO(String source) {
		super(CaseTypeProfileBO.TABLE, CaseTypeProfileBO.SYSTEM, CaseTypeProfileBO.CORIS_DISTRICT_DB.setSource("D"), CaseTypeProfileBO.CORIS_JUSTICE_DB.setSource("J"), CaseTypeProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseTypeProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(caseNumCode);
		this.getPropertyList().add(judgeAssn);
		this.getPropertyList().add(commAssn);
		this.getPropertyList().add(lbl);
		this.getPropertyList().add(conv);
		this.getPropertyList().add(cbn);
		this.getPropertyList().add(mandLbl);
		this.getPropertyList().add(mandConv);
		this.getPropertyList().add(mandCbn);
	}

}