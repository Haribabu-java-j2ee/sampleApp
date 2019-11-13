package gov.utcourts.coriscommon.dataaccess.ddodraft;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DdoDraftVO extends BaseVO { 

	private TypeInteger ddodId = new TypeInteger("ddod_id").setFieldDescriptor(DdoDraftBO.DDODID.clear()).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DdoDraftBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeInteger ddoId = new TypeInteger("ddo_id").setFieldDescriptor(DdoDraftBO.DDOID.clear());
	private TypeString ddoType = new TypeString("ddo_type").setFieldDescriptor(DdoDraftBO.DDOTYPE.clear());
	private TypeString ddoStatus = new TypeString("ddo_status").setFieldDescriptor(DdoDraftBO.DDOSTATUS.clear());
	private TypeDate updatedDatetime = new TypeDate("updated_datetime").setFieldDescriptor(DdoDraftBO.UPDATEDDATETIME.clear());

	public DdoDraftVO() {
		super(DdoDraftBO.TABLE, DdoDraftBO.SYSTEM, DdoDraftBO.CORIS_DISTRICT_DB.setSource("D"), DdoDraftBO.CORIS_JUSTICE_DB.setSource("J"), DdoDraftBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DdoDraftBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DdoDraftVO(String source) {
		super(DdoDraftBO.TABLE, DdoDraftBO.SYSTEM, DdoDraftBO.CORIS_DISTRICT_DB.setSource("D"), DdoDraftBO.CORIS_JUSTICE_DB.setSource("J"), DdoDraftBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DdoDraftBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(ddodId);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(ddoId);
		this.getPropertyList().add(ddoType);
		this.getPropertyList().add(ddoStatus);
		this.getPropertyList().add(updatedDatetime);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DdoDraftBO.UPDATEDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(DdoDraftBO.UPDATEDDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}