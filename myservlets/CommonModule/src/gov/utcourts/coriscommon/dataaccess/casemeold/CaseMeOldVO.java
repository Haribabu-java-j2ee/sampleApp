package gov.utcourts.coriscommon.dataaccess.casemeold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseMeOldVO extends BaseVO { 

	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(CaseMeOldBO.ACTIONTYPE.clear());
	private TypeInteger actionUseridSrl = new TypeInteger("action_userid_srl").setFieldDescriptor(CaseMeOldBO.ACTIONUSERIDSRL.clear());
	private TypeDate changeDatetime = new TypeDate("change_datetime").setFieldDescriptor(CaseMeOldBO.CHANGEDATETIME.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(CaseMeOldBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CaseMeOldBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CaseMeOldBO.COURTTYPE.clear()).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(CaseMeOldBO.MEID.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CaseMeOldBO.INTCASENUM.clear()).setNullable();
	private TypeDate meDatetime = new TypeDate("me_datetime").setFieldDescriptor(CaseMeOldBO.MEDATETIME.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(CaseMeOldBO.CLERKID.clear()).setNullable();
	private TypeString printFileName = new TypeString("print_file_name").setFieldDescriptor(CaseMeOldBO.PRINTFILENAME.clear()).setNullable();
	private TypeString seq = new TypeString("seq").setFieldDescriptor(CaseMeOldBO.SEQ.clear()).setNullable();
	private TypeString meSecurity = new TypeString("me_security").setFieldDescriptor(CaseMeOldBO.MESECURITY.clear()).setNullable();

	public CaseMeOldVO() {
		super(CaseMeOldBO.TABLE, CaseMeOldBO.SYSTEM, CaseMeOldBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeOldBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseMeOldVO(String source) {
		super(CaseMeOldBO.TABLE, CaseMeOldBO.SYSTEM, CaseMeOldBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeOldBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(actionType);
		this.getPropertyList().add(actionUseridSrl);
		this.getPropertyList().add(changeDatetime);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
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
			((TypeDate) this.getPropertyList().get(CaseMeOldBO.CHANGEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CaseMeOldBO.CHANGEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CaseMeOldBO.MEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CaseMeOldBO.MEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}