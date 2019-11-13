package gov.utcourts.coriscommon.dataaccess.printctl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PrintCtlVO extends BaseVO { 

	private TypeString printFileName = new TypeString("print_file_name").setFieldDescriptor(PrintCtlBO.PRINTFILENAME.clear()).addForeignKey("cron_report","print_file_name",true).addForeignKey("print_idx","print_file_name",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(PrintCtlBO.SEQ.clear());
	private TypeString scratchFlag = new TypeString("scratch_flag").setFieldDescriptor(PrintCtlBO.SCRATCHFLAG.clear());
	private TypeString alignFileName = new TypeString("align_file_name").setFieldDescriptor(PrintCtlBO.ALIGNFILENAME.clear()).setNullable();
	private TypeString group = new TypeString("group").setFieldDescriptor(PrintCtlBO.GROUP.clear());
	private TypeString printerType = new TypeString("printer_type").setFieldDescriptor(PrintCtlBO.PRINTERTYPE.clear());
	private TypeString orientation = new TypeString("orientation").setFieldDescriptor(PrintCtlBO.ORIENTATION.clear());
	private TypeString charPitch = new TypeString("char_pitch").setFieldDescriptor(PrintCtlBO.CHARPITCH.clear());
	private TypeString lpi = new TypeString("lpi").setFieldDescriptor(PrintCtlBO.LPI.clear());
	private TypeString defltTitle = new TypeString("deflt_title").setFieldDescriptor(PrintCtlBO.DEFLTTITLE.clear());
	private TypeString delayedReport = new TypeString("delayed_report").setFieldDescriptor(PrintCtlBO.DELAYEDREPORT.clear()).setNullable();

	public PrintCtlVO() {
		super(PrintCtlBO.TABLE, PrintCtlBO.SYSTEM, PrintCtlBO.CORIS_DISTRICT_DB.setSource("D"), PrintCtlBO.CORIS_JUSTICE_DB.setSource("J"), PrintCtlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrintCtlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PrintCtlVO(String source) {
		super(PrintCtlBO.TABLE, PrintCtlBO.SYSTEM, PrintCtlBO.CORIS_DISTRICT_DB.setSource("D"), PrintCtlBO.CORIS_JUSTICE_DB.setSource("J"), PrintCtlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrintCtlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(printFileName);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(scratchFlag);
		this.getPropertyList().add(alignFileName);
		this.getPropertyList().add(group);
		this.getPropertyList().add(printerType);
		this.getPropertyList().add(orientation);
		this.getPropertyList().add(charPitch);
		this.getPropertyList().add(lpi);
		this.getPropertyList().add(defltTitle);
		this.getPropertyList().add(delayedReport);
	}

}