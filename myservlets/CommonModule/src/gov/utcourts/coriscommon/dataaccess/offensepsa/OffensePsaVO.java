package gov.utcourts.coriscommon.dataaccess.offensepsa;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OffensePsaVO extends BaseVO { 

	private TypeInteger offensePsaId = new TypeInteger("offense_psa_id").setFieldDescriptor(OffensePsaBO.OFFENSEPSAID.clear()).setAsPrimaryKey();
	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(OffensePsaBO.OFFSVIOLCODE.clear());
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(OffensePsaBO.LASTEFFECTDATE.clear());
	private TypeString violent = new TypeString("violent").setFieldDescriptor(OffensePsaBO.VIOLENT.clear());
	private TypeInteger bumpUpValue = new TypeInteger("bump_up_value").setFieldDescriptor(OffensePsaBO.BUMPUPVALUE.clear());
	private TypeDate psaEffectDate = new TypeDate("psa_effect_date").setFieldDescriptor(OffensePsaBO.PSAEFFECTDATE.clear());
	private TypeDate historicalDate = new TypeDate("historical_date").setFieldDescriptor(OffensePsaBO.HISTORICALDATE.clear()).setNullable();

	public OffensePsaVO() {
		super(OffensePsaBO.TABLE, OffensePsaBO.SYSTEM, OffensePsaBO.CORIS_DISTRICT_DB.setSource("D"), OffensePsaBO.CORIS_JUSTICE_DB.setSource("J"), OffensePsaBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffensePsaBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OffensePsaVO(String source) {
		super(OffensePsaBO.TABLE, OffensePsaBO.SYSTEM, OffensePsaBO.CORIS_DISTRICT_DB.setSource("D"), OffensePsaBO.CORIS_JUSTICE_DB.setSource("J"), OffensePsaBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffensePsaBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(offensePsaId);
		this.getPropertyList().add(offsViolCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(violent);
		this.getPropertyList().add(bumpUpValue);
		this.getPropertyList().add(psaEffectDate);
		this.getPropertyList().add(historicalDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OffensePsaBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffensePsaBO.LASTEFFECTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OffensePsaBO.PSAEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffensePsaBO.PSAEFFECTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OffensePsaBO.HISTORICALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffensePsaBO.HISTORICALDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}