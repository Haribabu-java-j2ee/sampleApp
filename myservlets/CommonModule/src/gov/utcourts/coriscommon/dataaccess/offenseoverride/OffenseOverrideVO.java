package gov.utcourts.coriscommon.dataaccess.offenseoverride;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OffenseOverrideVO extends BaseVO { 

	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(OffenseOverrideBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(OffenseOverrideBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setAsPrimaryKey();
	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(OffenseOverrideBO.OFFSVIOLCODE.clear()).setAsPrimaryKey();
	private TypeDate beginDate = new TypeDate("begin_date").setFieldDescriptor(OffenseOverrideBO.BEGINDATE.clear()).setAsPrimaryKey();
	private TypeDate endDate = new TypeDate("end_date").setFieldDescriptor(OffenseOverrideBO.ENDDATE.clear());
	private TypeString mandAppearFlag = new TypeString("mand_appear_flag").setFieldDescriptor(OffenseOverrideBO.MANDAPPEARFLAG.clear()).setNullable();
	private TypeString defltSeverity = new TypeString("deflt_severity").setFieldDescriptor(OffenseOverrideBO.DEFLTSEVERITY.clear()).addForeignKey("severity_type","severity_code",false).setNullable();

	public OffenseOverrideVO() {
		super(OffenseOverrideBO.TABLE, OffenseOverrideBO.SYSTEM, OffenseOverrideBO.CORIS_DISTRICT_DB.setSource("D"), OffenseOverrideBO.CORIS_JUSTICE_DB.setSource("J"), OffenseOverrideBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseOverrideBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OffenseOverrideVO(String source) {
		super(OffenseOverrideBO.TABLE, OffenseOverrideBO.SYSTEM, OffenseOverrideBO.CORIS_DISTRICT_DB.setSource("D"), OffenseOverrideBO.CORIS_JUSTICE_DB.setSource("J"), OffenseOverrideBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseOverrideBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(offsViolCode);
		this.getPropertyList().add(beginDate);
		this.getPropertyList().add(endDate);
		this.getPropertyList().add(mandAppearFlag);
		this.getPropertyList().add(defltSeverity);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OffenseOverrideBO.BEGINDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffenseOverrideBO.BEGINDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OffenseOverrideBO.ENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffenseOverrideBO.ENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}