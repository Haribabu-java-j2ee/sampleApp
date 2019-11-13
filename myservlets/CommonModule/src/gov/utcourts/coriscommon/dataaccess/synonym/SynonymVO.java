package gov.utcourts.coriscommon.dataaccess.synonym;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SynonymVO extends BaseVO { 

	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(SynonymBO.GOVCODE.clear()).addForeignKey("gov_type","gov_code",false).setAsPrimaryKey();
	private TypeString violCode = new TypeString("viol_code").setFieldDescriptor(SynonymBO.VIOLCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(SynonymBO.LASTEFFECTDATE.clear()).setAsPrimaryKey();
	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(SynonymBO.OFFSVIOLCODE.clear()).setNullable();

	public SynonymVO() {
		super(SynonymBO.TABLE, SynonymBO.SYSTEM, SynonymBO.CORIS_DISTRICT_DB.setSource("D"), SynonymBO.CORIS_JUSTICE_DB.setSource("J"), SynonymBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SynonymBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SynonymVO(String source) {
		super(SynonymBO.TABLE, SynonymBO.SYSTEM, SynonymBO.CORIS_DISTRICT_DB.setSource("D"), SynonymBO.CORIS_JUSTICE_DB.setSource("J"), SynonymBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SynonymBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(govCode);
		this.getPropertyList().add(violCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(offsViolCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SynonymBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SynonymBO.LASTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}