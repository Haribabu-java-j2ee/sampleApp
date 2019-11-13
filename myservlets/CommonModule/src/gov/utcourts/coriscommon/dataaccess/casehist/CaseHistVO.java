package gov.utcourts.coriscommon.dataaccess.casehist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseHistVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CaseHistBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate entryDatetime = new TypeDate("entry_datetime").setFieldDescriptor(CaseHistBO.ENTRYDATETIME.clear()).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(CaseHistBO.CREATEDATETIME.clear()).setAsPrimaryKey();
	private TypeString chText = new TypeString("ch_text").setFieldDescriptor(CaseHistBO.CHTEXT.clear()).setNullable();
	private TypeString modFlag = new TypeString("mod_flag").setFieldDescriptor(CaseHistBO.MODFLAG.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(CaseHistBO.CLERKID.clear()).setNullable();
	private TypeString funcId = new TypeString("func_id").setFieldDescriptor(CaseHistBO.FUNCID.clear()).setNullable();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(CaseHistBO.REMOVEDFLAG.clear());

	public CaseHistVO() {
		super(CaseHistBO.TABLE, CaseHistBO.SYSTEM, CaseHistBO.CORIS_DISTRICT_DB.setSource("D"), CaseHistBO.CORIS_JUSTICE_DB.setSource("J"), CaseHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseHistVO(String source) {
		super(CaseHistBO.TABLE, CaseHistBO.SYSTEM, CaseHistBO.CORIS_DISTRICT_DB.setSource("D"), CaseHistBO.CORIS_JUSTICE_DB.setSource("J"), CaseHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(entryDatetime);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(chText);
		this.getPropertyList().add(modFlag);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(funcId);
		this.getPropertyList().add(removedFlag);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CaseHistBO.ENTRYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CaseHistBO.ENTRYDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CaseHistBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CaseHistBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}