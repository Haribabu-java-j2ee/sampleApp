package gov.utcourts.coriscommon.dataaccess.massrepeal;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MassRepealVO extends BaseVO { 

	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(MassRepealBO.OFFSVIOLCODE.clear()).setNullable();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(MassRepealBO.LASTEFFECTDATE.clear()).setNullable();
	private TypeDate repealDate = new TypeDate("repeal_date").setFieldDescriptor(MassRepealBO.REPEALDATE.clear()).setNullable();
	private TypeString processed = new TypeString("processed").setFieldDescriptor(MassRepealBO.PROCESSED.clear()).setNullable();
	private TypeString defltSeverity = new TypeString("deflt_severity").setFieldDescriptor(MassRepealBO.DEFLTSEVERITY.clear()).setNullable();

	public MassRepealVO() {
		super(MassRepealBO.TABLE, MassRepealBO.SYSTEM, MassRepealBO.CORIS_DISTRICT_DB.setSource("D"), MassRepealBO.CORIS_JUSTICE_DB.setSource("J"), MassRepealBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MassRepealBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MassRepealVO(String source) {
		super(MassRepealBO.TABLE, MassRepealBO.SYSTEM, MassRepealBO.CORIS_DISTRICT_DB.setSource("D"), MassRepealBO.CORIS_JUSTICE_DB.setSource("J"), MassRepealBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MassRepealBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(offsViolCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(repealDate);
		this.getPropertyList().add(processed);
		this.getPropertyList().add(defltSeverity);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(MassRepealBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(MassRepealBO.LASTEFFECTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(MassRepealBO.REPEALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(MassRepealBO.REPEALDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}