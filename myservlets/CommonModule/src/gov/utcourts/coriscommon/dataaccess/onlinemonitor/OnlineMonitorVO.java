package gov.utcourts.coriscommon.dataaccess.onlinemonitor;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OnlineMonitorVO extends BaseVO { 

	private TypeString logname = new TypeString("logname").setFieldDescriptor(OnlineMonitorBO.LOGNAME.clear());
	private TypeString computerName = new TypeString("computer_name").setFieldDescriptor(OnlineMonitorBO.COMPUTERNAME.clear());
	private TypeDate logonTime = new TypeDate("logon_time").setFieldDescriptor(OnlineMonitorBO.LOGONTIME.clear()).setNullable();
	private TypeDate logoffTime = new TypeDate("logoff_time").setFieldDescriptor(OnlineMonitorBO.LOGOFFTIME.clear()).setNullable();

	public OnlineMonitorVO() {
		super(OnlineMonitorBO.TABLE, OnlineMonitorBO.SYSTEM, OnlineMonitorBO.CORIS_DISTRICT_DB.setSource("D"), OnlineMonitorBO.CORIS_JUSTICE_DB.setSource("J"), OnlineMonitorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OnlineMonitorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OnlineMonitorVO(String source) {
		super(OnlineMonitorBO.TABLE, OnlineMonitorBO.SYSTEM, OnlineMonitorBO.CORIS_DISTRICT_DB.setSource("D"), OnlineMonitorBO.CORIS_JUSTICE_DB.setSource("J"), OnlineMonitorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OnlineMonitorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(logname);
		this.getPropertyList().add(computerName);
		this.getPropertyList().add(logonTime);
		this.getPropertyList().add(logoffTime);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OnlineMonitorBO.LOGONTIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OnlineMonitorBO.LOGONTIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OnlineMonitorBO.LOGOFFTIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OnlineMonitorBO.LOGOFFTIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}