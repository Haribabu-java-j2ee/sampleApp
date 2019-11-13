package gov.utcourts.coriscommon.dataaccess.sysblderrorlog;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeLong;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dataaccess.types.TypeText;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SysblderrorlogVO extends BaseVO { 

	private TypeLong order = new TypeLong("order").setFieldDescriptor(SysblderrorlogBO.ORDER.clear());
	private TypeString bldId = new TypeString("bld_id").setFieldDescriptor(SysblderrorlogBO.BLDID.clear()).setNullable();
	private TypeString errOperation = new TypeString("err_operation").setFieldDescriptor(SysblderrorlogBO.ERROPERATION.clear()).setNullable();
	private TypeString errExpected = new TypeString("err_expected").setFieldDescriptor(SysblderrorlogBO.ERREXPECTED.clear()).setNullable();
	private TypeText errSqlStmt = new TypeText("err_sql_stmt").setFieldDescriptor(SysblderrorlogBO.ERRSQLSTMT.clear()).setNullable();
	private TypeText errSqlState = new TypeText("err_sql_state").setFieldDescriptor(SysblderrorlogBO.ERRSQLSTATE.clear()).setNullable();

	public SysblderrorlogVO() {
		super(SysblderrorlogBO.TABLE, SysblderrorlogBO.SYSTEM, SysblderrorlogBO.CORIS_DISTRICT_DB.setSource("D"), SysblderrorlogBO.CORIS_JUSTICE_DB.setSource("J"), SysblderrorlogBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysblderrorlogBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SysblderrorlogVO(String source) {
		super(SysblderrorlogBO.TABLE, SysblderrorlogBO.SYSTEM, SysblderrorlogBO.CORIS_DISTRICT_DB.setSource("D"), SysblderrorlogBO.CORIS_JUSTICE_DB.setSource("J"), SysblderrorlogBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysblderrorlogBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(order);
		this.getPropertyList().add(bldId);
		this.getPropertyList().add(errOperation);
		this.getPropertyList().add(errExpected);
		this.getPropertyList().add(errSqlStmt);
		this.getPropertyList().add(errSqlState);
	}

}