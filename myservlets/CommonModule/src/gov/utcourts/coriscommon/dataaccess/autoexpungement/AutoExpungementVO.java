package gov.utcourts.coriscommon.dataaccess.autoexpungement;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AutoExpungementVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(AutoExpungementBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setNullable().setAsPrimaryKey();
	private TypeInteger aexpOrderId = new TypeInteger("aexp_order_id").setFieldDescriptor(AutoExpungementBO.AEXPORDERID.clear()).addForeignKey("auto_expungement_order","aexp_order_id",false);
	private TypeString aexpTypeId = new TypeString("aexp_type_id").setFieldDescriptor(AutoExpungementBO.AEXPTYPEID.clear()).addForeignKey("auto_expungement_type","aexp_type_id",true);
	private TypeString aexpStatusId = new TypeString("aexp_status_id").setFieldDescriptor(AutoExpungementBO.AEXPSTATUSID.clear()).addForeignKey("auto_expungement_status","aexp_status_id",true);
	private TypeDate createDateTime = new TypeDate("create_date_time").setFieldDescriptor(AutoExpungementBO.CREATEDATETIME.clear());
	private TypeString otn = new TypeString("otn").setFieldDescriptor(AutoExpungementBO.OTN.clear()).setNullable();
	private TypeDate autoExpungeDate = new TypeDate("auto_expunge_date").setFieldDescriptor(AutoExpungementBO.AUTOEXPUNGEDATE.clear()).setNullable();
	private TypeDate notifyConfirmationDate = new TypeDate("notify_confirmation_date").setFieldDescriptor(AutoExpungementBO.NOTIFYCONFIRMATIONDATE.clear()).setNullable();
	private TypeDate notifyEligibleDate = new TypeDate("notify_eligible_date").setFieldDescriptor(AutoExpungementBO.NOTIFYELIGIBLEDATE.clear()).setNullable();

	public AutoExpungementVO() {
		super(AutoExpungementBO.TABLE, AutoExpungementBO.SYSTEM, AutoExpungementBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AutoExpungementVO(String source) {
		super(AutoExpungementBO.TABLE, AutoExpungementBO.SYSTEM, AutoExpungementBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(aexpOrderId);
		this.getPropertyList().add(aexpTypeId);
		this.getPropertyList().add(aexpStatusId);
		this.getPropertyList().add(createDateTime);
		this.getPropertyList().add(otn);
		this.getPropertyList().add(autoExpungeDate);
		this.getPropertyList().add(notifyConfirmationDate);
		this.getPropertyList().add(notifyEligibleDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(AutoExpungementBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AutoExpungementBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AutoExpungementBO.AUTOEXPUNGEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AutoExpungementBO.AUTOEXPUNGEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AutoExpungementBO.NOTIFYCONFIRMATIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AutoExpungementBO.NOTIFYCONFIRMATIONDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AutoExpungementBO.NOTIFYELIGIBLEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AutoExpungementBO.NOTIFYELIGIBLEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}