package gov.utcourts.coriscommon.dataaccess.orsregistry;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OrsRegistryVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(OrsRegistryBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate reportDate = new TypeDate("report_date").setFieldDescriptor(OrsRegistryBO.REPORTDATE.clear()).setNullable();
	private TypeString status = new TypeString("status").setFieldDescriptor(OrsRegistryBO.STATUS.clear()).setNullable();

	public OrsRegistryVO() {
		super(OrsRegistryBO.TABLE, OrsRegistryBO.SYSTEM, OrsRegistryBO.CORIS_DISTRICT_DB.setSource("D"), OrsRegistryBO.CORIS_JUSTICE_DB.setSource("J"), OrsRegistryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OrsRegistryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OrsRegistryVO(String source) {
		super(OrsRegistryBO.TABLE, OrsRegistryBO.SYSTEM, OrsRegistryBO.CORIS_DISTRICT_DB.setSource("D"), OrsRegistryBO.CORIS_JUSTICE_DB.setSource("J"), OrsRegistryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OrsRegistryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(reportDate);
		this.getPropertyList().add(status);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OrsRegistryBO.REPORTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OrsRegistryBO.REPORTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}