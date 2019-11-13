package gov.utcourts.coriscommon.dataaccess.custodyhistory;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CustodyHistoryVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CustodyHistoryBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false);
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(CustodyHistoryBO.CREATEDATETIME.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(CustodyHistoryBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(CustodyHistoryBO.MEID.clear()).addForeignKey("case_me","me_id",false).setNullable();
	private TypeString beginCustodyCode = new TypeString("begin_custody_code").setFieldDescriptor(CustodyHistoryBO.BEGINCUSTODYCODE.clear()).addForeignKey("custody_type","custody_code",false).setNullable();
	private TypeString beginCustodyLocn = new TypeString("begin_custody_locn").setFieldDescriptor(CustodyHistoryBO.BEGINCUSTODYLOCN.clear()).setNullable();
	private TypeString endCustodyCode = new TypeString("end_custody_code").setFieldDescriptor(CustodyHistoryBO.ENDCUSTODYCODE.clear()).setNullable();
	private TypeString endCustodyLocn = new TypeString("end_custody_locn").setFieldDescriptor(CustodyHistoryBO.ENDCUSTODYLOCN.clear()).setNullable();
	private TypeString custodyNote = new TypeString("custody_note").setFieldDescriptor(CustodyHistoryBO.CUSTODYNOTE.clear()).setNullable();
	private TypeString custodyCond = new TypeString("custody_cond").setFieldDescriptor(CustodyHistoryBO.CUSTODYCOND.clear()).setNullable();

	public CustodyHistoryVO() {
		super(CustodyHistoryBO.TABLE, CustodyHistoryBO.SYSTEM, CustodyHistoryBO.CORIS_DISTRICT_DB.setSource("D"), CustodyHistoryBO.CORIS_JUSTICE_DB.setSource("J"), CustodyHistoryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CustodyHistoryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CustodyHistoryVO(String source) {
		super(CustodyHistoryBO.TABLE, CustodyHistoryBO.SYSTEM, CustodyHistoryBO.CORIS_DISTRICT_DB.setSource("D"), CustodyHistoryBO.CORIS_JUSTICE_DB.setSource("J"), CustodyHistoryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CustodyHistoryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(meId);
		this.getPropertyList().add(beginCustodyCode);
		this.getPropertyList().add(beginCustodyLocn);
		this.getPropertyList().add(endCustodyCode);
		this.getPropertyList().add(endCustodyLocn);
		this.getPropertyList().add(custodyNote);
		this.getPropertyList().add(custodyCond);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CustodyHistoryBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CustodyHistoryBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}