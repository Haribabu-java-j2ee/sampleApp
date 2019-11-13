package gov.utcourts.coriscommon.dataaccess.sattyparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SAttyPartyVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SAttyPartyBO.SINTCASENUM.clear()).setNullable();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SAttyPartyBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SAttyPartyBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SAttyPartyBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SAttyPartyBO.SOPERATION.clear()).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(SAttyPartyBO.CREATEDATETIME.clear()).setNullable();
	private TypeDate modifyDatetime = new TypeDate("modify_datetime").setFieldDescriptor(SAttyPartyBO.MODIFYDATETIME.clear()).setNullable();
	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(SAttyPartyBO.BARNUM.clear()).setNullable();
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(SAttyPartyBO.BARSTATE.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SAttyPartyBO.INTCASENUM.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SAttyPartyBO.PARTYNUM.clear()).setNullable();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(SAttyPartyBO.PARTYCODE.clear()).setNullable();
	private TypeString attyType = new TypeString("atty_type").setFieldDescriptor(SAttyPartyBO.ATTYTYPE.clear()).setNullable();
	private TypeDate attachDatetime = new TypeDate("attach_datetime").setFieldDescriptor(SAttyPartyBO.ATTACHDATETIME.clear()).setNullable();
	private TypeInteger attachUserid = new TypeInteger("attach_userid").setFieldDescriptor(SAttyPartyBO.ATTACHUSERID.clear()).setNullable();
	private TypeDate detachDatetime = new TypeDate("detach_datetime").setFieldDescriptor(SAttyPartyBO.DETACHDATETIME.clear()).setNullable();
	private TypeInteger detachUserid = new TypeInteger("detach_userid").setFieldDescriptor(SAttyPartyBO.DETACHUSERID.clear()).setNullable();
	private TypeString attyPartyNote = new TypeString("atty_party_note").setFieldDescriptor(SAttyPartyBO.ATTYPARTYNOTE.clear()).setNullable();

	public SAttyPartyVO() {
		super(SAttyPartyBO.TABLE, SAttyPartyBO.SYSTEM, SAttyPartyBO.CORIS_DISTRICT_DB.setSource("D"), SAttyPartyBO.CORIS_JUSTICE_DB.setSource("J"), SAttyPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAttyPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SAttyPartyVO(String source) {
		super(SAttyPartyBO.TABLE, SAttyPartyBO.SYSTEM, SAttyPartyBO.CORIS_DISTRICT_DB.setSource("D"), SAttyPartyBO.CORIS_JUSTICE_DB.setSource("J"), SAttyPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAttyPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(modifyDatetime);
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(barState);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(attyType);
		this.getPropertyList().add(attachDatetime);
		this.getPropertyList().add(attachUserid);
		this.getPropertyList().add(detachDatetime);
		this.getPropertyList().add(detachUserid);
		this.getPropertyList().add(attyPartyNote);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SAttyPartyBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAttyPartyBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAttyPartyBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAttyPartyBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAttyPartyBO.MODIFYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAttyPartyBO.MODIFYDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAttyPartyBO.ATTACHDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAttyPartyBO.ATTACHDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAttyPartyBO.DETACHDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAttyPartyBO.DETACHDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}