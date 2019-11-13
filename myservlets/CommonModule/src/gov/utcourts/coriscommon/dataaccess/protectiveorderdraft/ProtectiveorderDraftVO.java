package gov.utcourts.coriscommon.dataaccess.protectiveorderdraft;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProtectiveorderDraftVO extends BaseVO { 

	private TypeInteger protectiveorderdraftid = new TypeInteger("protectiveorderdraftid").setFieldDescriptor(ProtectiveorderDraftBO.PROTECTIVEORDERDRAFTID.clear()).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(ProtectiveorderDraftBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeInteger poId = new TypeInteger("po_id").setFieldDescriptor(ProtectiveorderDraftBO.POID.clear());
	private TypeString poStatus = new TypeString("po_status").setFieldDescriptor(ProtectiveorderDraftBO.POSTATUS.clear());
	private TypeDate updatedDatetime = new TypeDate("updated_datetime").setFieldDescriptor(ProtectiveorderDraftBO.UPDATEDDATETIME.clear());
	private TypeString poOrderCode = new TypeString("po_order_code").setFieldDescriptor(ProtectiveorderDraftBO.POORDERCODE.clear());

	public ProtectiveorderDraftVO() {
		super(ProtectiveorderDraftBO.TABLE, ProtectiveorderDraftBO.SYSTEM, ProtectiveorderDraftBO.CORIS_DISTRICT_DB.setSource("D"), ProtectiveorderDraftBO.CORIS_JUSTICE_DB.setSource("J"), ProtectiveorderDraftBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProtectiveorderDraftBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ProtectiveorderDraftVO(String source) {
		super(ProtectiveorderDraftBO.TABLE, ProtectiveorderDraftBO.SYSTEM, ProtectiveorderDraftBO.CORIS_DISTRICT_DB.setSource("D"), ProtectiveorderDraftBO.CORIS_JUSTICE_DB.setSource("J"), ProtectiveorderDraftBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProtectiveorderDraftBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(protectiveorderdraftid);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(poId);
		this.getPropertyList().add(poStatus);
		this.getPropertyList().add(updatedDatetime);
		this.getPropertyList().add(poOrderCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(ProtectiveorderDraftBO.UPDATEDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(ProtectiveorderDraftBO.UPDATEDDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}