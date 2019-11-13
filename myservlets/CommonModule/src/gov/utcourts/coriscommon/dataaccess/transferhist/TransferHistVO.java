package gov.utcourts.coriscommon.dataaccess.transferhist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TransferHistVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(TransferHistBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate transferDatetime = new TypeDate("transfer_datetime").setFieldDescriptor(TransferHistBO.TRANSFERDATETIME.clear()).setNullable().setAsPrimaryKey();
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(TransferHistBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(TransferHistBO.LOCNCODE.clear());
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(TransferHistBO.COURTTYPE.clear());

	public TransferHistVO() {
		super(TransferHistBO.TABLE, TransferHistBO.SYSTEM, TransferHistBO.CORIS_DISTRICT_DB.setSource("D"), TransferHistBO.CORIS_JUSTICE_DB.setSource("J"), TransferHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransferHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TransferHistVO(String source) {
		super(TransferHistBO.TABLE, TransferHistBO.SYSTEM, TransferHistBO.CORIS_DISTRICT_DB.setSource("D"), TransferHistBO.CORIS_JUSTICE_DB.setSource("J"), TransferHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransferHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(transferDatetime);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(TransferHistBO.TRANSFERDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(TransferHistBO.TRANSFERDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}