package gov.utcourts.coriscommon.dataaccess.evidencelist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EvidenceListVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EvidenceListBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(EvidenceListBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString exhibitParty = new TypeString("exhibit_party").setFieldDescriptor(EvidenceListBO.EXHIBITPARTY.clear());
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(EvidenceListBO.PARTYNUM.clear()).setNullable();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(EvidenceListBO.DESCR.clear());
	private TypeDate recvdDate = new TypeDate("recvd_date").setFieldDescriptor(EvidenceListBO.RECVDDATE.clear()).setNullable();
	private TypeString offeredFlag = new TypeString("offered_flag").setFieldDescriptor(EvidenceListBO.OFFEREDFLAG.clear()).setNullable();
	private TypeString adviseFlag = new TypeString("advise_flag").setFieldDescriptor(EvidenceListBO.ADVISEFLAG.clear()).setNullable();
	private TypeString refusedFlag = new TypeString("refused_flag").setFieldDescriptor(EvidenceListBO.REFUSEDFLAG.clear()).setNullable();
	private TypeString wdrawFlag = new TypeString("wdraw_flag").setFieldDescriptor(EvidenceListBO.WDRAWFLAG.clear()).setNullable();
	private TypeString origFlag = new TypeString("orig_flag").setFieldDescriptor(EvidenceListBO.ORIGFLAG.clear()).setNullable();
	private TypeString status = new TypeString("status").setFieldDescriptor(EvidenceListBO.STATUS.clear()).setNullable();
	private TypeDate offeredDate = new TypeDate("offered_date").setFieldDescriptor(EvidenceListBO.OFFEREDDATE.clear()).setNullable();
	private TypeString receivedFlag = new TypeString("received_flag").setFieldDescriptor(EvidenceListBO.RECEIVEDFLAG.clear()).setNullable();
	private TypeString itemId = new TypeString("item_id").setFieldDescriptor(EvidenceListBO.ITEMID.clear()).setNullable();
	private TypeString releasedFlag = new TypeString("released_flag").setFieldDescriptor(EvidenceListBO.RELEASEDFLAG.clear()).setNullable();

	public EvidenceListVO() {
		super(EvidenceListBO.TABLE, EvidenceListBO.SYSTEM, EvidenceListBO.CORIS_DISTRICT_DB.setSource("D"), EvidenceListBO.CORIS_JUSTICE_DB.setSource("J"), EvidenceListBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EvidenceListBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EvidenceListVO(String source) {
		super(EvidenceListBO.TABLE, EvidenceListBO.SYSTEM, EvidenceListBO.CORIS_DISTRICT_DB.setSource("D"), EvidenceListBO.CORIS_JUSTICE_DB.setSource("J"), EvidenceListBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EvidenceListBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
		this.getPropertyList().add(offeredDate);
		this.getPropertyList().add(receivedFlag);
		this.getPropertyList().add(itemId);
		this.getPropertyList().add(releasedFlag);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(EvidenceListBO.RECVDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(EvidenceListBO.RECVDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(EvidenceListBO.OFFEREDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(EvidenceListBO.OFFEREDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}