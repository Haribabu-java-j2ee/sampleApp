package gov.utcourts.coriscommon.dataaccess.offenseretransmission;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OffenseRetransmissionVO extends BaseVO { 

	private TypeString action = new TypeString("action").setFieldDescriptor(OffenseRetransmissionBO.ACTION.clear()).setNullable();
	private TypeDate actionDatetime = new TypeDate("action_datetime").setFieldDescriptor(OffenseRetransmissionBO.ACTIONDATETIME.clear()).setNullable();
	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(OffenseRetransmissionBO.OFFSVIOLCODE.clear()).setNullable();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(OffenseRetransmissionBO.LASTEFFECTDATE.clear()).setNullable();
	private TypeDate repealDate = new TypeDate("repeal_date").setFieldDescriptor(OffenseRetransmissionBO.REPEALDATE.clear()).setNullable();
	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(OffenseRetransmissionBO.GOVCODE.clear()).setNullable();
	private TypeString violCode = new TypeString("viol_code").setFieldDescriptor(OffenseRetransmissionBO.VIOLCODE.clear()).setNullable();
	private TypeString lastModifiedReason = new TypeString("last_modified_reason").setFieldDescriptor(OffenseRetransmissionBO.LASTMODIFIEDREASON.clear()).setNullable();

	public OffenseRetransmissionVO() {
		super(OffenseRetransmissionBO.TABLE, OffenseRetransmissionBO.SYSTEM, OffenseRetransmissionBO.CORIS_DISTRICT_DB.setSource("D"), OffenseRetransmissionBO.CORIS_JUSTICE_DB.setSource("J"), OffenseRetransmissionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseRetransmissionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OffenseRetransmissionVO(String source) {
		super(OffenseRetransmissionBO.TABLE, OffenseRetransmissionBO.SYSTEM, OffenseRetransmissionBO.CORIS_DISTRICT_DB.setSource("D"), OffenseRetransmissionBO.CORIS_JUSTICE_DB.setSource("J"), OffenseRetransmissionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseRetransmissionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(action);
		this.getPropertyList().add(actionDatetime);
		this.getPropertyList().add(offsViolCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(repealDate);
		this.getPropertyList().add(govCode);
		this.getPropertyList().add(violCode);
		this.getPropertyList().add(lastModifiedReason);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OffenseRetransmissionBO.ACTIONDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffenseRetransmissionBO.ACTIONDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OffenseRetransmissionBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffenseRetransmissionBO.LASTEFFECTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OffenseRetransmissionBO.REPEALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffenseRetransmissionBO.REPEALDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}