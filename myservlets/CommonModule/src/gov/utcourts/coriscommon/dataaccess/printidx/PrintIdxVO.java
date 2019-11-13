package gov.utcourts.coriscommon.dataaccess.printidx;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PrintIdxVO extends BaseVO { 

	private TypeString printFileName = new TypeString("print_file_name").setFieldDescriptor(PrintIdxBO.PRINTFILENAME.clear()).addForeignKey("print_ctl","print_file_name",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(PrintIdxBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(PrintIdxBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false);
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(PrintIdxBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false);
	private TypeString title = new TypeString("title").setFieldDescriptor(PrintIdxBO.TITLE.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PrintIdxBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeDate startDatetime = new TypeDate("start_datetime").setFieldDescriptor(PrintIdxBO.STARTDATETIME.clear()).setNullable();
	private TypeDate endDatetime = new TypeDate("end_datetime").setFieldDescriptor(PrintIdxBO.ENDDATETIME.clear()).setNullable();
	private TypeString printStatus = new TypeString("print_status").setFieldDescriptor(PrintIdxBO.PRINTSTATUS.clear());
	private TypeInteger procId = new TypeInteger("proc_id").setFieldDescriptor(PrintIdxBO.PROCID.clear()).setNullable();
	private TypeInteger errlogSrl = new TypeInteger("errlog_srl").setFieldDescriptor(PrintIdxBO.ERRLOGSRL.clear()).setNullable();
	private TypeInteger printErrlogSrl = new TypeInteger("print_errlog_srl").setFieldDescriptor(PrintIdxBO.PRINTERRLOGSRL.clear()).setNullable();
	private TypeString fileType = new TypeString("file_type").setFieldDescriptor(PrintIdxBO.FILETYPE.clear());

	public PrintIdxVO() {
		super(PrintIdxBO.TABLE, PrintIdxBO.SYSTEM, PrintIdxBO.CORIS_DISTRICT_DB.setSource("D"), PrintIdxBO.CORIS_JUSTICE_DB.setSource("J"), PrintIdxBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrintIdxBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PrintIdxVO(String source) {
		super(PrintIdxBO.TABLE, PrintIdxBO.SYSTEM, PrintIdxBO.CORIS_DISTRICT_DB.setSource("D"), PrintIdxBO.CORIS_JUSTICE_DB.setSource("J"), PrintIdxBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrintIdxBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(printFileName);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(title);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(startDatetime);
		this.getPropertyList().add(endDatetime);
		this.getPropertyList().add(printStatus);
		this.getPropertyList().add(procId);
		this.getPropertyList().add(errlogSrl);
		this.getPropertyList().add(printErrlogSrl);
		this.getPropertyList().add(fileType);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PrintIdxBO.STARTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(PrintIdxBO.STARTDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(PrintIdxBO.ENDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(PrintIdxBO.ENDDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}