package gov.utcourts.coriscommon.dataaccess.dopldata;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DoplDataVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DoplDataBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false).setAsPrimaryKey();
	private TypeDate doplRptDate = new TypeDate("dopl_rpt_date").setFieldDescriptor(DoplDataBO.DOPLRPTDATE.clear()).setNullable();
	private TypeString doplRptStatus = new TypeString("dopl_rpt_status").setFieldDescriptor(DoplDataBO.DOPLRPTSTATUS.clear()).setNullable();

	public DoplDataVO() {
		super(DoplDataBO.TABLE, DoplDataBO.SYSTEM, DoplDataBO.CORIS_DISTRICT_DB.setSource("D"), DoplDataBO.CORIS_JUSTICE_DB.setSource("J"), DoplDataBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DoplDataBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DoplDataVO(String source) {
		super(DoplDataBO.TABLE, DoplDataBO.SYSTEM, DoplDataBO.CORIS_DISTRICT_DB.setSource("D"), DoplDataBO.CORIS_JUSTICE_DB.setSource("J"), DoplDataBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DoplDataBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(doplRptDate);
		this.getPropertyList().add(doplRptStatus);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DoplDataBO.DOPLRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DoplDataBO.DOPLRPTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}