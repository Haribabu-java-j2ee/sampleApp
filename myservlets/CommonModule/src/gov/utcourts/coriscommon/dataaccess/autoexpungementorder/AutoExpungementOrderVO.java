package gov.utcourts.coriscommon.dataaccess.autoexpungementorder;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AutoExpungementOrderVO extends BaseVO { 

	private TypeInteger aexpOrderId = new TypeInteger("aexp_order_id").setFieldDescriptor(AutoExpungementOrderBO.AEXPORDERID.clear()).addForeignKey("auto_expungement","aexp_order_id",false).setAsPrimaryKey();
	private TypeInteger presidingJudgeId = new TypeInteger("presiding_judge_id").setFieldDescriptor(AutoExpungementOrderBO.PRESIDINGJUDGEID.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeInteger dmDocid = new TypeInteger("dm_docid").setFieldDescriptor(AutoExpungementOrderBO.DMDOCID.clear());
	private TypeDate effectiveDate = new TypeDate("effective_date").setFieldDescriptor(AutoExpungementOrderBO.EFFECTIVEDATE.clear());
	private TypeDate expirationDate = new TypeDate("expiration_date").setFieldDescriptor(AutoExpungementOrderBO.EXPIRATIONDATE.clear()).setNullable();

	public AutoExpungementOrderVO() {
		super(AutoExpungementOrderBO.TABLE, AutoExpungementOrderBO.SYSTEM, AutoExpungementOrderBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementOrderBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementOrderBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementOrderBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AutoExpungementOrderVO(String source) {
		super(AutoExpungementOrderBO.TABLE, AutoExpungementOrderBO.SYSTEM, AutoExpungementOrderBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementOrderBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementOrderBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementOrderBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(aexpOrderId);
		this.getPropertyList().add(presidingJudgeId);
		this.getPropertyList().add(dmDocid);
		this.getPropertyList().add(effectiveDate);
		this.getPropertyList().add(expirationDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(AutoExpungementOrderBO.EFFECTIVEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AutoExpungementOrderBO.EFFECTIVEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AutoExpungementOrderBO.EXPIRATIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AutoExpungementOrderBO.EXPIRATIONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}