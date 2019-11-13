package gov.utcourts.coriscommon.dataaccess.attyparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AttyPartyVO extends BaseVO { 

	private TypeInteger attyPartyId = new TypeInteger("atty_party_id").setFieldDescriptor(AttyPartyBO.ATTYPARTYID.clear()).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(AttyPartyBO.CREATEDATETIME.clear()).setNullable();
	private TypeDate modifyDatetime = new TypeDate("modify_datetime").setFieldDescriptor(AttyPartyBO.MODIFYDATETIME.clear()).setNullable();
	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(AttyPartyBO.BARNUM.clear()).addForeignKey("attorney","bar_num",false);
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(AttyPartyBO.BARSTATE.clear()).addForeignKey("attorney","bar_state",false);
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(AttyPartyBO.INTCASENUM.clear()).addForeignKey("party_case","int_case_num",false);
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(AttyPartyBO.PARTYNUM.clear()).addForeignKey("party_case","party_num",false);
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(AttyPartyBO.PARTYCODE.clear()).addForeignKey("party_case","party_code",false);
	private TypeString attyType = new TypeString("atty_type").setFieldDescriptor(AttyPartyBO.ATTYTYPE.clear());
	private TypeDate attachDatetime = new TypeDate("attach_datetime").setFieldDescriptor(AttyPartyBO.ATTACHDATETIME.clear()).setNullable();
	private TypeInteger attachUserid = new TypeInteger("attach_userid").setFieldDescriptor(AttyPartyBO.ATTACHUSERID.clear()).setNullable();
	private TypeDate detachDatetime = new TypeDate("detach_datetime").setFieldDescriptor(AttyPartyBO.DETACHDATETIME.clear()).setNullable();
	private TypeInteger detachUserid = new TypeInteger("detach_userid").setFieldDescriptor(AttyPartyBO.DETACHUSERID.clear()).setNullable();
	private TypeString attyPartyNote = new TypeString("atty_party_note").setFieldDescriptor(AttyPartyBO.ATTYPARTYNOTE.clear()).setNullable();

	public AttyPartyVO() {
		super(AttyPartyBO.TABLE, AttyPartyBO.SYSTEM, AttyPartyBO.CORIS_DISTRICT_DB.setSource("D"), AttyPartyBO.CORIS_JUSTICE_DB.setSource("J"), AttyPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AttyPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AttyPartyVO(String source) {
		super(AttyPartyBO.TABLE, AttyPartyBO.SYSTEM, AttyPartyBO.CORIS_DISTRICT_DB.setSource("D"), AttyPartyBO.CORIS_JUSTICE_DB.setSource("J"), AttyPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AttyPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(attyPartyId);
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
			((TypeDate) this.getPropertyList().get(AttyPartyBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AttyPartyBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AttyPartyBO.MODIFYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AttyPartyBO.MODIFYDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AttyPartyBO.ATTACHDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AttyPartyBO.ATTACHDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AttyPartyBO.DETACHDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AttyPartyBO.DETACHDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}