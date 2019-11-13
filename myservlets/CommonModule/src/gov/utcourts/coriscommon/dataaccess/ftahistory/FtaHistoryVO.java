package gov.utcourts.coriscommon.dataaccess.ftahistory;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class FtaHistoryVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(FtaHistoryBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(FtaHistoryBO.CREATEDATETIME.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(FtaHistoryBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(FtaHistoryBO.MEID.clear()).addForeignKey("case_me","me_id",false).setNullable();
	private TypeDate failToAppearDate = new TypeDate("fail_to_appear_date").setFieldDescriptor(FtaHistoryBO.FAILTOAPPEARDATE.clear()).setNullable();
	private TypeString warrantOrdered = new TypeString("warrant_ordered").setFieldDescriptor(FtaHistoryBO.WARRANTORDERED.clear()).setNullable();

	public FtaHistoryVO() {
		super(FtaHistoryBO.TABLE, FtaHistoryBO.SYSTEM, FtaHistoryBO.CORIS_DISTRICT_DB.setSource("D"), FtaHistoryBO.CORIS_JUSTICE_DB.setSource("J"), FtaHistoryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FtaHistoryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public FtaHistoryVO(String source) {
		super(FtaHistoryBO.TABLE, FtaHistoryBO.SYSTEM, FtaHistoryBO.CORIS_DISTRICT_DB.setSource("D"), FtaHistoryBO.CORIS_JUSTICE_DB.setSource("J"), FtaHistoryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FtaHistoryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(meId);
		this.getPropertyList().add(failToAppearDate);
		this.getPropertyList().add(warrantOrdered);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(FtaHistoryBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(FtaHistoryBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(FtaHistoryBO.FAILTOAPPEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(FtaHistoryBO.FAILTOAPPEARDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}