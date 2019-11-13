package gov.utcourts.coriscommon.dataaccess.systememails;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeText;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SystemEmailsVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(SystemEmailsBO.USERIDSRL.clear()).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(SystemEmailsBO.CREATEDATETIME.clear()).setNullable();
	private TypeText command = new TypeText("command").setFieldDescriptor(SystemEmailsBO.COMMAND.clear()).setNullable();

	public SystemEmailsVO() {
		super(SystemEmailsBO.TABLE, SystemEmailsBO.SYSTEM, SystemEmailsBO.CORIS_DISTRICT_DB.setSource("D"), SystemEmailsBO.CORIS_JUSTICE_DB.setSource("J"), SystemEmailsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SystemEmailsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SystemEmailsVO(String source) {
		super(SystemEmailsBO.TABLE, SystemEmailsBO.SYSTEM, SystemEmailsBO.CORIS_DISTRICT_DB.setSource("D"), SystemEmailsBO.CORIS_JUSTICE_DB.setSource("J"), SystemEmailsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SystemEmailsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(command);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SystemEmailsBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SystemEmailsBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}