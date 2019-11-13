package gov.utcourts.coriscommon.dataaccess.casewarning;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseWarningVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CaseWarningBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString warning = new TypeString("warning").setFieldDescriptor(CaseWarningBO.WARNING.clear()).setNullable().setAsPrimaryKey();
	private TypeString rptFlag = new TypeString("rpt_flag").setFieldDescriptor(CaseWarningBO.RPTFLAG.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(CaseWarningBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeDate entryDatetime = new TypeDate("entry_datetime").setFieldDescriptor(CaseWarningBO.ENTRYDATETIME.clear()).setNullable();

	public CaseWarningVO() {
		super(CaseWarningBO.TABLE, CaseWarningBO.SYSTEM, CaseWarningBO.CORIS_DISTRICT_DB.setSource("D"), CaseWarningBO.CORIS_JUSTICE_DB.setSource("J"), CaseWarningBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseWarningBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseWarningVO(String source) {
		super(CaseWarningBO.TABLE, CaseWarningBO.SYSTEM, CaseWarningBO.CORIS_DISTRICT_DB.setSource("D"), CaseWarningBO.CORIS_JUSTICE_DB.setSource("J"), CaseWarningBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseWarningBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(warning);
		this.getPropertyList().add(rptFlag);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(entryDatetime);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CaseWarningBO.ENTRYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CaseWarningBO.ENTRYDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}