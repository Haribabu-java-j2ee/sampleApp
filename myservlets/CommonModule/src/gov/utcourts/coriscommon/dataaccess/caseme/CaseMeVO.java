package gov.utcourts.coriscommon.dataaccess.caseme;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseMeVO extends BaseVO { 

	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(CaseMeBO.MEID.clear()).addForeignKey("atty_present","me_id",false).addForeignKey("case_me_document","me_id",false).addForeignKey("case_me_judgment","me_id",false).addForeignKey("case_me_value","me_id",false).addForeignKey("case_me_value_text","me_id",true).addForeignKey("custody_history","me_id",true).addForeignKey("event","me_id",true).addForeignKey("fta_history","me_id",true).addForeignKey("warrant","recall_me_id",true).addForeignKey("warrant","me_id",true).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CaseMeBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeDate meDatetime = new TypeDate("me_datetime").setFieldDescriptor(CaseMeBO.MEDATETIME.clear());
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(CaseMeBO.CLERKID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString printFileName = new TypeString("print_file_name").setFieldDescriptor(CaseMeBO.PRINTFILENAME.clear()).setNullable();
	private TypeString seq = new TypeString("seq").setFieldDescriptor(CaseMeBO.SEQ.clear()).setNullable();
	private TypeString meSecurity = new TypeString("me_security").setFieldDescriptor(CaseMeBO.MESECURITY.clear()).addForeignKey("security_type","code",false);

	public CaseMeVO() {
		super(CaseMeBO.TABLE, CaseMeBO.SYSTEM, CaseMeBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseMeVO(String source) {
		super(CaseMeBO.TABLE, CaseMeBO.SYSTEM, CaseMeBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(meId);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(meDatetime);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(printFileName);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(meSecurity);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CaseMeBO.MEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CaseMeBO.MEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}