package gov.utcourts.coriscommon.dataaccess.sentfulfilltype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SentFulfillTypeVO extends BaseVO { 

	private TypeString sentFulfillCode = new TypeString("sent_fulfill_code").setFieldDescriptor(SentFulfillTypeBO.SENTFULFILLCODE.clear()).addForeignKey("sent_fulfill","sent_fulfill_code",false).setAsPrimaryKey();
	private TypeInteger displaySeq = new TypeInteger("display_seq").setFieldDescriptor(SentFulfillTypeBO.DISPLAYSEQ.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(SentFulfillTypeBO.REMOVEDFLAG.clear());
	private TypeString descr = new TypeString("descr").setFieldDescriptor(SentFulfillTypeBO.DESCR.clear()).setNullable();

	public SentFulfillTypeVO() {
		super(SentFulfillTypeBO.TABLE, SentFulfillTypeBO.SYSTEM, SentFulfillTypeBO.CORIS_DISTRICT_DB.setSource("D"), SentFulfillTypeBO.CORIS_JUSTICE_DB.setSource("J"), SentFulfillTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SentFulfillTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SentFulfillTypeVO(String source) {
		super(SentFulfillTypeBO.TABLE, SentFulfillTypeBO.SYSTEM, SentFulfillTypeBO.CORIS_DISTRICT_DB.setSource("D"), SentFulfillTypeBO.CORIS_JUSTICE_DB.setSource("J"), SentFulfillTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SentFulfillTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sentFulfillCode);
		this.getPropertyList().add(displaySeq);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(descr);
	}

}