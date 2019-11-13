package gov.utcourts.coriscommon.dataaccess.processtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProcessTypeVO extends BaseVO { 

	private TypeInteger processId = new TypeInteger("process_id").setFieldDescriptor(ProcessTypeBO.PROCESSID.clear()).addForeignKey("court_process","process_id",true).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(ProcessTypeBO.DESCR.clear()).setNullable();
	private TypeString tablename = new TypeString("table_name").setFieldDescriptor(ProcessTypeBO.TABLENAME.clear()).setNullable();
	private TypeString valueColName = new TypeString("value_col_name").setFieldDescriptor(ProcessTypeBO.VALUECOLNAME.clear()).setNullable();
	private TypeString displayColName = new TypeString("display_col_name").setFieldDescriptor(ProcessTypeBO.DISPLAYCOLNAME.clear()).setNullable();
	private TypeString processNote = new TypeString("process_note").setFieldDescriptor(ProcessTypeBO.PROCESSNOTE.clear()).setNullable();

	public ProcessTypeVO() {
		super(ProcessTypeBO.TABLE, ProcessTypeBO.SYSTEM, ProcessTypeBO.CORIS_DISTRICT_DB.setSource("D"), ProcessTypeBO.CORIS_JUSTICE_DB.setSource("J"), ProcessTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProcessTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ProcessTypeVO(String source) {
		super(ProcessTypeBO.TABLE, ProcessTypeBO.SYSTEM, ProcessTypeBO.CORIS_DISTRICT_DB.setSource("D"), ProcessTypeBO.CORIS_JUSTICE_DB.setSource("J"), ProcessTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProcessTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(processId);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(tablename);
		this.getPropertyList().add(valueColName);
		this.getPropertyList().add(displayColName);
		this.getPropertyList().add(processNote);
	}

}