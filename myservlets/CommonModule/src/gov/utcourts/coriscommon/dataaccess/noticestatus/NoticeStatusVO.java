package gov.utcourts.coriscommon.dataaccess.noticestatus;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dataaccess.types.TypeText;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class NoticeStatusVO extends BaseVO { 

	private TypeInteger statusid = new TypeInteger("statusid").setFieldDescriptor(NoticeStatusBO.STATUSID.clear()).setAsPrimaryKey();
	private TypeString noticetype = new TypeString("noticetype").setFieldDescriptor(NoticeStatusBO.NOTICETYPE.clear());
	private TypeString attachment = new TypeString("attachment").setFieldDescriptor(NoticeStatusBO.ATTACHMENT.clear());
	private TypeString processed = new TypeString("processed").setFieldDescriptor(NoticeStatusBO.PROCESSED.clear());
	private TypeInteger requestid = new TypeInteger("requestid").setFieldDescriptor(NoticeStatusBO.REQUESTID.clear());
	private TypeInteger systemid = new TypeInteger("systemid").setFieldDescriptor(NoticeStatusBO.SYSTEMID.clear());
	private TypeDate datesent = new TypeDate("datesent").setFieldDescriptor(NoticeStatusBO.DATESENT.clear());
	private TypeString casemgmtId = new TypeString("casemgmt_id").setFieldDescriptor(NoticeStatusBO.CASEMGMTID.clear());
	private TypeString casemgmtTable = new TypeString("casemgmt_table").setFieldDescriptor(NoticeStatusBO.CASEMGMTTABLE.clear());
	private TypeString sendfrom = new TypeString("sendfrom").setFieldDescriptor(NoticeStatusBO.SENDFROM.clear());
	private TypeString subject = new TypeString("subject").setFieldDescriptor(NoticeStatusBO.SUBJECT.clear());
	private TypeString sendto = new TypeString("sendto").setFieldDescriptor(NoticeStatusBO.SENDTO.clear());
	private TypeText sendcc = new TypeText("sendcc").setFieldDescriptor(NoticeStatusBO.SENDCC.clear()).setNullable();
	private TypeText sendbcc = new TypeText("sendbcc").setFieldDescriptor(NoticeStatusBO.SENDBCC.clear()).setNullable();

	public NoticeStatusVO() {
		super(NoticeStatusBO.TABLE, NoticeStatusBO.SYSTEM, NoticeStatusBO.CORIS_DISTRICT_DB.setSource("D"), NoticeStatusBO.CORIS_JUSTICE_DB.setSource("J"), NoticeStatusBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NoticeStatusBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public NoticeStatusVO(String source) {
		super(NoticeStatusBO.TABLE, NoticeStatusBO.SYSTEM, NoticeStatusBO.CORIS_DISTRICT_DB.setSource("D"), NoticeStatusBO.CORIS_JUSTICE_DB.setSource("J"), NoticeStatusBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NoticeStatusBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(statusid);
		this.getPropertyList().add(noticetype);
		this.getPropertyList().add(attachment);
		this.getPropertyList().add(processed);
		this.getPropertyList().add(requestid);
		this.getPropertyList().add(systemid);
		this.getPropertyList().add(datesent);
		this.getPropertyList().add(casemgmtId);
		this.getPropertyList().add(casemgmtTable);
		this.getPropertyList().add(sendfrom);
		this.getPropertyList().add(subject);
		this.getPropertyList().add(sendto);
		this.getPropertyList().add(sendcc);
		this.getPropertyList().add(sendbcc);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(NoticeStatusBO.DATESENT.getPosition())).setDateFormat((DateFormat) super.getAttribute(NoticeStatusBO.DATESENT.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}