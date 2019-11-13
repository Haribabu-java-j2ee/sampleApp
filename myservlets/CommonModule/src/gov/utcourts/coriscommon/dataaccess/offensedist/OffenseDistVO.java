package gov.utcourts.coriscommon.dataaccess.offensedist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OffenseDistVO extends BaseVO { 

	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(OffenseDistBO.OFFSVIOLCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(OffenseDistBO.LASTEFFECTDATE.clear()).setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(OffenseDistBO.DISTCODE.clear()).addForeignKey("dist_type","dist_code",false).setAsPrimaryKey();
	private TypeInteger severityLvl = new TypeInteger("severity_lvl").setFieldDescriptor(OffenseDistBO.SEVERITYLVL.clear()).setAsPrimaryKey();
	private TypeBigDecimal amt = new TypeBigDecimal("amt").setFieldDescriptor(OffenseDistBO.AMT.clear());
	private TypeString surchargeCode = new TypeString("surcharge_code").setFieldDescriptor(OffenseDistBO.SURCHARGECODE.clear()).addForeignKey("dist_type","dist_code",false).setNullable();

	public OffenseDistVO() {
		super(OffenseDistBO.TABLE, OffenseDistBO.SYSTEM, OffenseDistBO.CORIS_DISTRICT_DB.setSource("D"), OffenseDistBO.CORIS_JUSTICE_DB.setSource("J"), OffenseDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OffenseDistVO(String source) {
		super(OffenseDistBO.TABLE, OffenseDistBO.SYSTEM, OffenseDistBO.CORIS_DISTRICT_DB.setSource("D"), OffenseDistBO.CORIS_JUSTICE_DB.setSource("J"), OffenseDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(offsViolCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(severityLvl);
		this.getPropertyList().add(amt);
		this.getPropertyList().add(surchargeCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OffenseDistBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffenseDistBO.LASTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}