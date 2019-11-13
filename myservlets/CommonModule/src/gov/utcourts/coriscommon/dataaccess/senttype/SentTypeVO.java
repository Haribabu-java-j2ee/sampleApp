package gov.utcourts.coriscommon.dataaccess.senttype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SentTypeVO extends BaseVO { 

	private TypeString sentCode = new TypeString("sent_code").setFieldDescriptor(SentTypeBO.SENTCODE.clear()).addForeignKey("sentence","sent_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(SentTypeBO.DESCR.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(SentTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(SentTypeBO.REMOVEDFLAG.clear());

	public SentTypeVO() {
		super(SentTypeBO.TABLE, SentTypeBO.SYSTEM, SentTypeBO.CORIS_DISTRICT_DB.setSource("D"), SentTypeBO.CORIS_JUSTICE_DB.setSource("J"), SentTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SentTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SentTypeVO(String source) {
		super(SentTypeBO.TABLE, SentTypeBO.SYSTEM, SentTypeBO.CORIS_DISTRICT_DB.setSource("D"), SentTypeBO.CORIS_JUSTICE_DB.setSource("J"), SentTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SentTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sentCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
	}

}