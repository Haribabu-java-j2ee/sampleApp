package gov.utcourts.coriscommon.dataaccess.printercap;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PrinterCapVO extends BaseVO { 

	private TypeString printerId = new TypeString("printer_id").setFieldDescriptor(PrinterCapBO.PRINTERID.clear()).setAsPrimaryKey();
	private TypeString capType = new TypeString("cap_type").setFieldDescriptor(PrinterCapBO.CAPTYPE.clear()).setAsPrimaryKey();
	private TypeString setupCode = new TypeString("setup_code").setFieldDescriptor(PrinterCapBO.SETUPCODE.clear());

	public PrinterCapVO() {
		super(PrinterCapBO.TABLE, PrinterCapBO.SYSTEM, PrinterCapBO.CORIS_DISTRICT_DB.setSource("D"), PrinterCapBO.CORIS_JUSTICE_DB.setSource("J"), PrinterCapBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrinterCapBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PrinterCapVO(String source) {
		super(PrinterCapBO.TABLE, PrinterCapBO.SYSTEM, PrinterCapBO.CORIS_DISTRICT_DB.setSource("D"), PrinterCapBO.CORIS_JUSTICE_DB.setSource("J"), PrinterCapBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrinterCapBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(printerId);
		this.getPropertyList().add(capType);
		this.getPropertyList().add(setupCode);
	}

}