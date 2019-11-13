package gov.utcourts.coriscommon.dataaccess.offenseregistrycode;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OffenseRegistryCodeVO extends BaseVO { 

	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(OffenseRegistryCodeBO.OFFSVIOLCODE.clear()).addForeignKey("offense","offs_viol_code",true).setAsPrimaryKey();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(OffenseRegistryCodeBO.LASTEFFECTDATE.clear()).addForeignKey("offense","last_effect_date",false).setAsPrimaryKey();
	private TypeString orcType = new TypeString("orc_type").setFieldDescriptor(OffenseRegistryCodeBO.ORCTYPE.clear()).setAsPrimaryKey();

	public OffenseRegistryCodeVO() {
		super(OffenseRegistryCodeBO.TABLE, OffenseRegistryCodeBO.SYSTEM, OffenseRegistryCodeBO.CORIS_DISTRICT_DB.setSource("D"), OffenseRegistryCodeBO.CORIS_JUSTICE_DB.setSource("J"), OffenseRegistryCodeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseRegistryCodeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OffenseRegistryCodeVO(String source) {
		super(OffenseRegistryCodeBO.TABLE, OffenseRegistryCodeBO.SYSTEM, OffenseRegistryCodeBO.CORIS_DISTRICT_DB.setSource("D"), OffenseRegistryCodeBO.CORIS_JUSTICE_DB.setSource("J"), OffenseRegistryCodeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseRegistryCodeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(offsViolCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(orcType);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OffenseRegistryCodeBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffenseRegistryCodeBO.LASTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}