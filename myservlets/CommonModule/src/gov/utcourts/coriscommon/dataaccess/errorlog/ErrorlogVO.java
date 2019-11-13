package gov.utcourts.coriscommon.dataaccess.errorlog;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ErrorlogVO extends BaseVO { 

	private TypeInteger errlogSrl = new TypeInteger("errlog_srl").setFieldDescriptor(ErrorlogBO.ERRLOGSRL.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(ErrorlogBO.USERIDSRL.clear()).setNullable();
	private TypeString errlogInfo = new TypeString("errlog_info").setFieldDescriptor(ErrorlogBO.ERRLOGINFO.clear()).setNullable();
	private TypeDate errlogDatetime = new TypeDate("errlog_datetime").setFieldDescriptor(ErrorlogBO.ERRLOGDATETIME.clear()).setNullable();
	private TypeInteger sqlCode = new TypeInteger("sql_code").setFieldDescriptor(ErrorlogBO.SQLCODE.clear()).setNullable();
	private TypeInteger isamCode = new TypeInteger("isam_code").setFieldDescriptor(ErrorlogBO.ISAMCODE.clear()).setNullable();
	private TypeString errorValue = new TypeString("error_value").setFieldDescriptor(ErrorlogBO.ERRORVALUE.clear()).setNullable();

	public ErrorlogVO() {
		super(ErrorlogBO.TABLE, ErrorlogBO.SYSTEM, ErrorlogBO.CORIS_DISTRICT_DB.setSource("D"), ErrorlogBO.CORIS_JUSTICE_DB.setSource("J"), ErrorlogBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ErrorlogBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ErrorlogVO(String source) {
		super(ErrorlogBO.TABLE, ErrorlogBO.SYSTEM, ErrorlogBO.CORIS_DISTRICT_DB.setSource("D"), ErrorlogBO.CORIS_JUSTICE_DB.setSource("J"), ErrorlogBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ErrorlogBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(errlogSrl);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(errlogInfo);
		this.getPropertyList().add(errlogDatetime);
		this.getPropertyList().add(sqlCode);
		this.getPropertyList().add(isamCode);
		this.getPropertyList().add(errorValue);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(ErrorlogBO.ERRLOGDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(ErrorlogBO.ERRLOGDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}