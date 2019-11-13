package gov.utcourts.coriscommon.dataaccess.casemevalueold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseMeValueOldVO extends BaseVO { 

	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(CaseMeValueOldBO.ACTIONTYPE.clear());
	private TypeInteger actionUseridSrl = new TypeInteger("action_userid_srl").setFieldDescriptor(CaseMeValueOldBO.ACTIONUSERIDSRL.clear());
	private TypeDate changeDatetime = new TypeDate("change_datetime").setFieldDescriptor(CaseMeValueOldBO.CHANGEDATETIME.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(CaseMeValueOldBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CaseMeValueOldBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CaseMeValueOldBO.COURTTYPE.clear()).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(CaseMeValueOldBO.MEID.clear()).setNullable();
	private TypeInteger meTypeSeq = new TypeInteger("me_type_seq").setFieldDescriptor(CaseMeValueOldBO.METYPESEQ.clear()).setNullable();
	private TypeString meScreenId = new TypeString("me_screen_id").setFieldDescriptor(CaseMeValueOldBO.MESCREENID.clear()).setNullable();
	private TypeString meFieldId = new TypeString("me_field_id").setFieldDescriptor(CaseMeValueOldBO.MEFIELDID.clear()).setNullable();
	private TypeInteger meFieldSeq = new TypeInteger("me_field_seq").setFieldDescriptor(CaseMeValueOldBO.MEFIELDSEQ.clear()).setNullable();
	private TypeString meFieldValue = new TypeString("me_field_value").setFieldDescriptor(CaseMeValueOldBO.MEFIELDVALUE.clear()).setNullable();

	public CaseMeValueOldVO() {
		super(CaseMeValueOldBO.TABLE, CaseMeValueOldBO.SYSTEM, CaseMeValueOldBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeValueOldBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeValueOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeValueOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseMeValueOldVO(String source) {
		super(CaseMeValueOldBO.TABLE, CaseMeValueOldBO.SYSTEM, CaseMeValueOldBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeValueOldBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeValueOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeValueOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
		this.getPropertyList().add(meTypeSeq);
		this.getPropertyList().add(meScreenId);
		this.getPropertyList().add(meFieldId);
		this.getPropertyList().add(meFieldSeq);
		this.getPropertyList().add(meFieldValue);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CaseMeValueOldBO.CHANGEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CaseMeValueOldBO.CHANGEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}