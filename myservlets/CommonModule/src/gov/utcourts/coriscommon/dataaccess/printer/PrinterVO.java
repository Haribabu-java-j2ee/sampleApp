package gov.utcourts.coriscommon.dataaccess.printer;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PrinterVO extends BaseVO { 

	private TypeString printerName = new TypeString("printer_name").setFieldDescriptor(PrinterBO.PRINTERNAME.clear()).addForeignKey("personnel_printer","printer_name",false).setAsPrimaryKey();
	private TypeString printerType = new TypeString("printer_type").setFieldDescriptor(PrinterBO.PRINTERTYPE.clear());
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(PrinterBO.LOCNCODE.clear());
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(PrinterBO.COURTTYPE.clear());
	private TypeString printerId = new TypeString("printer_id").setFieldDescriptor(PrinterBO.PRINTERID.clear());
	private TypeString descr = new TypeString("descr").setFieldDescriptor(PrinterBO.DESCR.clear());
	private TypeString printerStatus = new TypeString("printer_status").setFieldDescriptor(PrinterBO.PRINTERSTATUS.clear());
	private TypeString osType = new TypeString("os_type").setFieldDescriptor(PrinterBO.OSTYPE.clear());

	public PrinterVO() {
		super(PrinterBO.TABLE, PrinterBO.SYSTEM, PrinterBO.CORIS_DISTRICT_DB.setSource("D"), PrinterBO.CORIS_JUSTICE_DB.setSource("J"), PrinterBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrinterBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PrinterVO(String source) {
		super(PrinterBO.TABLE, PrinterBO.SYSTEM, PrinterBO.CORIS_DISTRICT_DB.setSource("D"), PrinterBO.CORIS_JUSTICE_DB.setSource("J"), PrinterBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrinterBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(printerName);
		this.getPropertyList().add(printerType);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(printerId);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(printerStatus);
		this.getPropertyList().add(osType);
	}

}