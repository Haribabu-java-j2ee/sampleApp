package gov.utcourts.coriscommon.dataaccess.sevidencelist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SEvidenceListVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SEvidenceListBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SEvidenceListBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SEvidenceListBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SEvidenceListBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SEvidenceListBO.SOPERATION.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SEvidenceListBO.INTCASENUM.clear()).setNullable();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(SEvidenceListBO.SEQ.clear()).setNullable().setAsPrimaryKey();
	private TypeString exhibitParty = new TypeString("exhibit_party").setFieldDescriptor(SEvidenceListBO.EXHIBITPARTY.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SEvidenceListBO.PARTYNUM.clear()).setNullable();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(SEvidenceListBO.DESCR.clear()).setNullable();
	private TypeDate recvdDate = new TypeDate("recvd_date").setFieldDescriptor(SEvidenceListBO.RECVDDATE.clear()).setNullable();
	private TypeString offeredFlag = new TypeString("offered_flag").setFieldDescriptor(SEvidenceListBO.OFFEREDFLAG.clear()).setNullable();
	private TypeString adviseFlag = new TypeString("advise_flag").setFieldDescriptor(SEvidenceListBO.ADVISEFLAG.clear()).setNullable();
	private TypeString refusedFlag = new TypeString("refused_flag").setFieldDescriptor(SEvidenceListBO.REFUSEDFLAG.clear()).setNullable();
	private TypeString wdrawFlag = new TypeString("wdraw_flag").setFieldDescriptor(SEvidenceListBO.WDRAWFLAG.clear()).setNullable();
	private TypeString origFlag = new TypeString("orig_flag").setFieldDescriptor(SEvidenceListBO.ORIGFLAG.clear()).setNullable();
	private TypeString status = new TypeString("status").setFieldDescriptor(SEvidenceListBO.STATUS.clear()).setNullable();
	private TypeString itemId = new TypeString("item_id").setFieldDescriptor(SEvidenceListBO.ITEMID.clear()).setNullable();

	public SEvidenceListVO() {
		super(SEvidenceListBO.TABLE, SEvidenceListBO.SYSTEM, SEvidenceListBO.CORIS_DISTRICT_DB.setSource("D"), SEvidenceListBO.CORIS_JUSTICE_DB.setSource("J"), SEvidenceListBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SEvidenceListBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SEvidenceListVO(String source) {
		super(SEvidenceListBO.TABLE, SEvidenceListBO.SYSTEM, SEvidenceListBO.CORIS_DISTRICT_DB.setSource("D"), SEvidenceListBO.CORIS_JUSTICE_DB.setSource("J"), SEvidenceListBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SEvidenceListBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(exhibitParty);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(recvdDate);
		this.getPropertyList().add(offeredFlag);
		this.getPropertyList().add(adviseFlag);
		this.getPropertyList().add(refusedFlag);
		this.getPropertyList().add(wdrawFlag);
		this.getPropertyList().add(origFlag);
		this.getPropertyList().add(status);
		this.getPropertyList().add(itemId);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SEvidenceListBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SEvidenceListBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SEvidenceListBO.RECVDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SEvidenceListBO.RECVDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}