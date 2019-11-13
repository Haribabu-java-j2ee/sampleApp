package gov.utcourts.coriscommon.dataaccess.compdate;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CompDateVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CompDateBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString compDateCode = new TypeString("comp_date_code").setFieldDescriptor(CompDateBO.COMPDATECODE.clear()).addForeignKey("comp_date_type","comp_date_code",false).setAsPrimaryKey();
	private TypeDate compDate = new TypeDate("comp_date").setFieldDescriptor(CompDateBO.COMPDATE.clear());

	public CompDateVO() {
		super(CompDateBO.TABLE, CompDateBO.SYSTEM, CompDateBO.CORIS_DISTRICT_DB.setSource("D"), CompDateBO.CORIS_JUSTICE_DB.setSource("J"), CompDateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CompDateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CompDateVO(String source) {
		super(CompDateBO.TABLE, CompDateBO.SYSTEM, CompDateBO.CORIS_DISTRICT_DB.setSource("D"), CompDateBO.CORIS_JUSTICE_DB.setSource("J"), CompDateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CompDateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(compDateCode);
		this.getPropertyList().add(compDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CompDateBO.COMPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CompDateBO.COMPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}