package gov.utcourts.coriscommon.dataaccess.failedecitation;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class FailedEcitationVO extends BaseVO { 

	private TypeInteger ecitationSrl = new TypeInteger("ecitation_srl").setFieldDescriptor(FailedEcitationBO.ECITATIONSRL.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(FailedEcitationBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false);
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(FailedEcitationBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false);
	private TypeDate fileDate = new TypeDate("file_date").setFieldDescriptor(FailedEcitationBO.FILEDATE.clear());
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(FailedEcitationBO.SEQ.clear());
	private TypeString citNum = new TypeString("cit_num").setFieldDescriptor(FailedEcitationBO.CITNUM.clear());
	private TypeString oriNum = new TypeString("ori_num").setFieldDescriptor(FailedEcitationBO.ORINUM.clear());
	private TypeDate violDatetime = new TypeDate("viol_datetime").setFieldDescriptor(FailedEcitationBO.VIOLDATETIME.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(FailedEcitationBO.INTCASENUM.clear()).setNullable();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(FailedEcitationBO.LASTNAME.clear()).setNullable();
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(FailedEcitationBO.FIRSTNAME.clear()).setNullable();
	private TypeString offrLastName = new TypeString("offr_last_name").setFieldDescriptor(FailedEcitationBO.OFFRLASTNAME.clear()).setNullable();
	private TypeString offrFirstName = new TypeString("offr_first_name").setFieldDescriptor(FailedEcitationBO.OFFRFIRSTNAME.clear()).setNullable();
	private TypeString reason = new TypeString("reason").setFieldDescriptor(FailedEcitationBO.REASON.clear()).setNullable();
	private TypeString note = new TypeString("note").setFieldDescriptor(FailedEcitationBO.NOTE.clear()).setNullable();

	public FailedEcitationVO() {
		super(FailedEcitationBO.TABLE, FailedEcitationBO.SYSTEM, FailedEcitationBO.CORIS_DISTRICT_DB.setSource("D"), FailedEcitationBO.CORIS_JUSTICE_DB.setSource("J"), FailedEcitationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FailedEcitationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public FailedEcitationVO(String source) {
		super(FailedEcitationBO.TABLE, FailedEcitationBO.SYSTEM, FailedEcitationBO.CORIS_DISTRICT_DB.setSource("D"), FailedEcitationBO.CORIS_JUSTICE_DB.setSource("J"), FailedEcitationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FailedEcitationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(ecitationSrl);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(fileDate);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(citNum);
		this.getPropertyList().add(oriNum);
		this.getPropertyList().add(violDatetime);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(offrLastName);
		this.getPropertyList().add(offrFirstName);
		this.getPropertyList().add(reason);
		this.getPropertyList().add(note);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(FailedEcitationBO.FILEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(FailedEcitationBO.FILEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(FailedEcitationBO.VIOLDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(FailedEcitationBO.VIOLDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}