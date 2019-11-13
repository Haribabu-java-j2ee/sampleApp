package gov.utcourts.coriscommon.dataaccess.vehicle;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class VehicleVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(VehicleBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString plateNum = new TypeString("plate_num").setFieldDescriptor(VehicleBO.PLATENUM.clear()).setNullable();
	private TypeString plateStateCode = new TypeString("plate_state_code").setFieldDescriptor(VehicleBO.PLATESTATECODE.clear()).setNullable();
	private TypeDate plateExpDate = new TypeDate("plate_exp_date").setFieldDescriptor(VehicleBO.PLATEEXPDATE.clear()).setNullable();
	private TypeString ownerName = new TypeString("owner_name").setFieldDescriptor(VehicleBO.OWNERNAME.clear()).setNullable();
	private TypeString year = new TypeString("year").setFieldDescriptor(VehicleBO.YEAR.clear()).setNullable();
	private TypeString make = new TypeString("make").setFieldDescriptor(VehicleBO.MAKE.clear()).setNullable();
	private TypeString model = new TypeString("model").setFieldDescriptor(VehicleBO.MODEL.clear()).setNullable();
	private TypeString style = new TypeString("style").setFieldDescriptor(VehicleBO.STYLE.clear()).setNullable();
	private TypeString color = new TypeString("color").setFieldDescriptor(VehicleBO.COLOR.clear()).setNullable();

	public VehicleVO() {
		super(VehicleBO.TABLE, VehicleBO.SYSTEM, VehicleBO.CORIS_DISTRICT_DB.setSource("D"), VehicleBO.CORIS_JUSTICE_DB.setSource("J"), VehicleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), VehicleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public VehicleVO(String source) {
		super(VehicleBO.TABLE, VehicleBO.SYSTEM, VehicleBO.CORIS_DISTRICT_DB.setSource("D"), VehicleBO.CORIS_JUSTICE_DB.setSource("J"), VehicleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), VehicleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(plateNum);
		this.getPropertyList().add(plateStateCode);
		this.getPropertyList().add(plateExpDate);
		this.getPropertyList().add(ownerName);
		this.getPropertyList().add(year);
		this.getPropertyList().add(make);
		this.getPropertyList().add(model);
		this.getPropertyList().add(style);
		this.getPropertyList().add(color);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(VehicleBO.PLATEEXPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(VehicleBO.PLATEEXPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}