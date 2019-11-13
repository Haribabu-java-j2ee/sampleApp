package gov.utcourts.coriscommon.dataaccess.printspecial;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PrintSpecialVO extends BaseVO { 

	private TypeString printFileName = new TypeString("print_file_name").setFieldDescriptor(PrintSpecialBO.PRINTFILENAME.clear());

	public PrintSpecialVO() {
		super(PrintSpecialBO.TABLE, PrintSpecialBO.SYSTEM, PrintSpecialBO.CORIS_DISTRICT_DB.setSource("D"), PrintSpecialBO.CORIS_JUSTICE_DB.setSource("J"), PrintSpecialBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrintSpecialBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PrintSpecialVO(String source) {
		super(PrintSpecialBO.TABLE, PrintSpecialBO.SYSTEM, PrintSpecialBO.CORIS_DISTRICT_DB.setSource("D"), PrintSpecialBO.CORIS_JUSTICE_DB.setSource("J"), PrintSpecialBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PrintSpecialBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(printFileName);
	}

}