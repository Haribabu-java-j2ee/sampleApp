package gov.utcourts.coriscommon.dataaccess.cronreport;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CronReportVO extends BaseVO { 

	private TypeString execStatement = new TypeString("exec_statement").setFieldDescriptor(CronReportBO.EXECSTATEMENT.clear());
	private TypeString printFileName = new TypeString("print_file_name").setFieldDescriptor(CronReportBO.PRINTFILENAME.clear()).addForeignKey("print_ctl","print_file_name",false).setNullable();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(CronReportBO.SEQ.clear()).setNullable();

	public CronReportVO() {
		super(CronReportBO.TABLE, CronReportBO.SYSTEM, CronReportBO.CORIS_DISTRICT_DB.setSource("D"), CronReportBO.CORIS_JUSTICE_DB.setSource("J"), CronReportBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CronReportBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CronReportVO(String source) {
		super(CronReportBO.TABLE, CronReportBO.SYSTEM, CronReportBO.CORIS_DISTRICT_DB.setSource("D"), CronReportBO.CORIS_JUSTICE_DB.setSource("J"), CronReportBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CronReportBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(execStatement);
		this.getPropertyList().add(printFileName);
		this.getPropertyList().add(seq);
	}

}