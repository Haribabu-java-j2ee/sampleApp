package gov.utcourts.coriscommon.dataaccess.postjdmtintcomp;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PostJdmtIntCompVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(PostJdmtIntCompBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate compDatetime = new TypeDate("comp_datetime").setFieldDescriptor(PostJdmtIntCompBO.COMPDATETIME.clear()).setAsPrimaryKey();
	private TypeInteger numDays = new TypeInteger("num_days").setFieldDescriptor(PostJdmtIntCompBO.NUMDAYS.clear());
	private TypeBigDecimal restitutionPrinc = new TypeBigDecimal("restitution_princ").setFieldDescriptor(PostJdmtIntCompBO.RESTITUTIONPRINC.clear());
	private TypeBigDecimal revenuePrinc = new TypeBigDecimal("revenue_princ").setFieldDescriptor(PostJdmtIntCompBO.REVENUEPRINC.clear());

	public PostJdmtIntCompVO() {
		super(PostJdmtIntCompBO.TABLE, PostJdmtIntCompBO.SYSTEM, PostJdmtIntCompBO.CORIS_DISTRICT_DB.setSource("D"), PostJdmtIntCompBO.CORIS_JUSTICE_DB.setSource("J"), PostJdmtIntCompBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PostJdmtIntCompBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PostJdmtIntCompVO(String source) {
		super(PostJdmtIntCompBO.TABLE, PostJdmtIntCompBO.SYSTEM, PostJdmtIntCompBO.CORIS_DISTRICT_DB.setSource("D"), PostJdmtIntCompBO.CORIS_JUSTICE_DB.setSource("J"), PostJdmtIntCompBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PostJdmtIntCompBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(compDatetime);
		this.getPropertyList().add(numDays);
		this.getPropertyList().add(restitutionPrinc);
		this.getPropertyList().add(revenuePrinc);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PostJdmtIntCompBO.COMPDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(PostJdmtIntCompBO.COMPDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}