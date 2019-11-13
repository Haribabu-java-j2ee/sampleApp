package gov.utcourts.coriscommon.dataaccess.mediation;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MediationVO extends BaseVO { 

	private TypeInteger mediationNum = new TypeInteger("mediation_num").setFieldDescriptor(MediationBO.MEDIATIONNUM.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(MediationBO.INTCASENUM.clear());
	private TypeDate entryDatetime = new TypeDate("entry_datetime").setFieldDescriptor(MediationBO.ENTRYDATETIME.clear()).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(MediationBO.CREATEDATETIME.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(MediationBO.CLERKID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(MediationBO.MEID.clear()).setNullable();
	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(MediationBO.DOCUMENTNUM.clear()).setNullable();
	private TypeDate referralDate = new TypeDate("referral_date").setFieldDescriptor(MediationBO.REFERRALDATE.clear()).setNullable();
	private TypeInteger judgeCommId = new TypeInteger("judge_comm_id").setFieldDescriptor(MediationBO.JUDGECOMMID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeDate completionDate = new TypeDate("completion_date").setFieldDescriptor(MediationBO.COMPLETIONDATE.clear()).setNullable();
	private TypeInteger mediatorId = new TypeInteger("mediator_id").setFieldDescriptor(MediationBO.MEDIATORID.clear()).addForeignKey("mediator","mediator_srl",false).setNullable();
	private TypeString mediationOutcome = new TypeString("mediation_outcome").setFieldDescriptor(MediationBO.MEDIATIONOUTCOME.clear()).addForeignKey("mediation_outcome","type",false).setNullable();

	public MediationVO() {
		super(MediationBO.TABLE, MediationBO.SYSTEM, MediationBO.CORIS_DISTRICT_DB.setSource("D"), MediationBO.CORIS_JUSTICE_DB.setSource("J"), MediationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MediationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MediationVO(String source) {
		super(MediationBO.TABLE, MediationBO.SYSTEM, MediationBO.CORIS_DISTRICT_DB.setSource("D"), MediationBO.CORIS_JUSTICE_DB.setSource("J"), MediationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MediationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(mediationNum);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(entryDatetime);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(meId);
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(referralDate);
		this.getPropertyList().add(judgeCommId);
		this.getPropertyList().add(completionDate);
		this.getPropertyList().add(mediatorId);
		this.getPropertyList().add(mediationOutcome);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(MediationBO.ENTRYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(MediationBO.ENTRYDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(MediationBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(MediationBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(MediationBO.REFERRALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(MediationBO.REFERRALDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(MediationBO.COMPLETIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(MediationBO.COMPLETIONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}