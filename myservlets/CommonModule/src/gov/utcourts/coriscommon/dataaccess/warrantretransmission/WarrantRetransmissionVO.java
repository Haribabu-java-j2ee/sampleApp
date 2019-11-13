package gov.utcourts.coriscommon.dataaccess.warrantretransmission;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class WarrantRetransmissionVO extends BaseVO { 

	private TypeString action = new TypeString("action").setFieldDescriptor(WarrantRetransmissionBO.ACTION.clear()).setNullable();
	private TypeDate actionDatetime = new TypeDate("action_datetime").setFieldDescriptor(WarrantRetransmissionBO.ACTIONDATETIME.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(WarrantRetransmissionBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false).addForeignKey("warrant","int_case_num",false).setNullable();
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(WarrantRetransmissionBO.CASENUM.clear()).setNullable();
	private TypeInteger warrNum = new TypeInteger("warr_num").setFieldDescriptor(WarrantRetransmissionBO.WARRNUM.clear()).addForeignKey("warrant","warr_num",false).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(WarrantRetransmissionBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(WarrantRetransmissionBO.COURTTYPE.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(WarrantRetransmissionBO.PARTYNUM.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(WarrantRetransmissionBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString logname = new TypeString("logname").setFieldDescriptor(WarrantRetransmissionBO.LOGNAME.clear()).setNullable();
	private TypeDate recallDatetime = new TypeDate("recall_datetime").setFieldDescriptor(WarrantRetransmissionBO.RECALLDATETIME.clear()).setNullable();
	private TypeInteger warrantTypeValue = new TypeInteger("warrant_type_value").setFieldDescriptor(WarrantRetransmissionBO.WARRANTTYPEVALUE.clear()).setNullable();

	public WarrantRetransmissionVO() {
		super(WarrantRetransmissionBO.TABLE, WarrantRetransmissionBO.SYSTEM, WarrantRetransmissionBO.CORIS_DISTRICT_DB.setSource("D"), WarrantRetransmissionBO.CORIS_JUSTICE_DB.setSource("J"), WarrantRetransmissionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarrantRetransmissionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public WarrantRetransmissionVO(String source) {
		super(WarrantRetransmissionBO.TABLE, WarrantRetransmissionBO.SYSTEM, WarrantRetransmissionBO.CORIS_DISTRICT_DB.setSource("D"), WarrantRetransmissionBO.CORIS_JUSTICE_DB.setSource("J"), WarrantRetransmissionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarrantRetransmissionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(action);
		this.getPropertyList().add(actionDatetime);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(warrNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(logname);
		this.getPropertyList().add(recallDatetime);
		this.getPropertyList().add(warrantTypeValue);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(WarrantRetransmissionBO.ACTIONDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(WarrantRetransmissionBO.ACTIONDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(WarrantRetransmissionBO.RECALLDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(WarrantRetransmissionBO.RECALLDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}