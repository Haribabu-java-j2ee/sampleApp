package gov.utcourts.coriscommon.dataaccess.signingprocess;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SigningProcessVO extends BaseVO { 

	private TypeInteger processId = new TypeInteger("process_id").setFieldDescriptor(SigningProcessBO.PROCESSID.clear());
	private TypeDate processDatetime = new TypeDate("process_datetime").setFieldDescriptor(SigningProcessBO.PROCESSDATETIME.clear()).setNullable();
	private TypeInteger idSerial = new TypeInteger("id_serial").setFieldDescriptor(SigningProcessBO.IDSERIAL.clear()).setNullable();
	private TypeString processStatus = new TypeString("process_status").setFieldDescriptor(SigningProcessBO.PROCESSSTATUS.clear()).setNullable();

	public SigningProcessVO() {
		super(SigningProcessBO.TABLE, SigningProcessBO.SYSTEM, SigningProcessBO.CORIS_DISTRICT_DB.setSource("D"), SigningProcessBO.CORIS_JUSTICE_DB.setSource("J"), SigningProcessBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SigningProcessBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SigningProcessVO(String source) {
		super(SigningProcessBO.TABLE, SigningProcessBO.SYSTEM, SigningProcessBO.CORIS_DISTRICT_DB.setSource("D"), SigningProcessBO.CORIS_JUSTICE_DB.setSource("J"), SigningProcessBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SigningProcessBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(processId);
		this.getPropertyList().add(processDatetime);
		this.getPropertyList().add(idSerial);
		this.getPropertyList().add(processStatus);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SigningProcessBO.PROCESSDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SigningProcessBO.PROCESSDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}