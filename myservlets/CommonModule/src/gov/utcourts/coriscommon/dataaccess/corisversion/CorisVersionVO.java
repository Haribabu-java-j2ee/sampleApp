package gov.utcourts.coriscommon.dataaccess.corisversion;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CorisVersionVO extends BaseVO { 

	private TypeDate versionDate = new TypeDate("version_date").setFieldDescriptor(CorisVersionBO.VERSIONDATE.clear()).setNullable();

	public CorisVersionVO() {
		super(CorisVersionBO.TABLE, CorisVersionBO.SYSTEM, CorisVersionBO.CORIS_DISTRICT_DB.setSource("D"), CorisVersionBO.CORIS_JUSTICE_DB.setSource("J"), CorisVersionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CorisVersionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CorisVersionVO(String source) {
		super(CorisVersionBO.TABLE, CorisVersionBO.SYSTEM, CorisVersionBO.CORIS_DISTRICT_DB.setSource("D"), CorisVersionBO.CORIS_JUSTICE_DB.setSource("J"), CorisVersionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CorisVersionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(versionDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CorisVersionBO.VERSIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CorisVersionBO.VERSIONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}