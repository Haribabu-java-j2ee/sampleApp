package gov.utcourts.coriscommon.dataaccess.personnelprinter;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PersonnelPrinterVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PersonnelPrinterBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeString printerName = new TypeString("printer_name").setFieldDescriptor(PersonnelPrinterBO.PRINTERNAME.clear()).addForeignKey("printer","printer_name",false);
	private TypeString printerType = new TypeString("printer_type").setFieldDescriptor(PersonnelPrinterBO.PRINTERTYPE.clear()).setAsPrimaryKey();

	public PersonnelPrinterVO() {
		super(PersonnelPrinterBO.TABLE, PersonnelPrinterBO.SYSTEM, PersonnelPrinterBO.CORIS_DISTRICT_DB.setSource("D"), PersonnelPrinterBO.CORIS_JUSTICE_DB.setSource("J"), PersonnelPrinterBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PersonnelPrinterBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PersonnelPrinterVO(String source) {
		super(PersonnelPrinterBO.TABLE, PersonnelPrinterBO.SYSTEM, PersonnelPrinterBO.CORIS_DISTRICT_DB.setSource("D"), PersonnelPrinterBO.CORIS_JUSTICE_DB.setSource("J"), PersonnelPrinterBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PersonnelPrinterBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(printerName);
		this.getPropertyList().add(printerType);
	}

}