package gov.utcourts.coriscommon.dataaccess.oldpasswords;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OldPasswordsVO extends BaseVO { 

	private TypeString oldPassword = new TypeString("old_password").setFieldDescriptor(OldPasswordsBO.OLDPASSWORD.clear()).setNullable();
	private TypeString logname = new TypeString("logname").setFieldDescriptor(OldPasswordsBO.LOGNAME.clear()).setNullable();
	private TypeString changedBy = new TypeString("changed_by").setFieldDescriptor(OldPasswordsBO.CHANGEDBY.clear()).setNullable();
	private TypeDate changedDate = new TypeDate("changed_date").setFieldDescriptor(OldPasswordsBO.CHANGEDDATE.clear()).setNullable();

	public OldPasswordsVO() {
		super(OldPasswordsBO.TABLE, OldPasswordsBO.SYSTEM, OldPasswordsBO.CORIS_DISTRICT_DB.setSource("D"), OldPasswordsBO.CORIS_JUSTICE_DB.setSource("J"), OldPasswordsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OldPasswordsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OldPasswordsVO(String source) {
		super(OldPasswordsBO.TABLE, OldPasswordsBO.SYSTEM, OldPasswordsBO.CORIS_DISTRICT_DB.setSource("D"), OldPasswordsBO.CORIS_JUSTICE_DB.setSource("J"), OldPasswordsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OldPasswordsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(oldPassword);
		this.getPropertyList().add(logname);
		this.getPropertyList().add(changedBy);
		this.getPropertyList().add(changedDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OldPasswordsBO.CHANGEDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OldPasswordsBO.CHANGEDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}