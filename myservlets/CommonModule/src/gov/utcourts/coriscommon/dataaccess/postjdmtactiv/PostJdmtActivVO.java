package gov.utcourts.coriscommon.dataaccess.postjdmtactiv;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PostJdmtActivVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(PostJdmtActivBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setNullable().setAsPrimaryKey();
	private TypeDate filingDate = new TypeDate("filing_date").setFieldDescriptor(PostJdmtActivBO.FILINGDATE.clear()).setNullable().setAsPrimaryKey();
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(PostJdmtActivBO.DISPDATE.clear()).setNullable();
	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(PostJdmtActivBO.DISPCODE.clear()).setNullable();
	private TypeInteger dispId = new TypeInteger("disp_id").setFieldDescriptor(PostJdmtActivBO.DISPID.clear()).setNullable();

	public PostJdmtActivVO() {
		super(PostJdmtActivBO.TABLE, PostJdmtActivBO.SYSTEM, PostJdmtActivBO.CORIS_DISTRICT_DB.setSource("D"), PostJdmtActivBO.CORIS_JUSTICE_DB.setSource("J"), PostJdmtActivBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PostJdmtActivBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PostJdmtActivVO(String source) {
		super(PostJdmtActivBO.TABLE, PostJdmtActivBO.SYSTEM, PostJdmtActivBO.CORIS_DISTRICT_DB.setSource("D"), PostJdmtActivBO.CORIS_JUSTICE_DB.setSource("J"), PostJdmtActivBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PostJdmtActivBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(filingDate);
		this.getPropertyList().add(dispDate);
		this.getPropertyList().add(dispCode);
		this.getPropertyList().add(dispId);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PostJdmtActivBO.FILINGDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PostJdmtActivBO.FILINGDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(PostJdmtActivBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PostJdmtActivBO.DISPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}