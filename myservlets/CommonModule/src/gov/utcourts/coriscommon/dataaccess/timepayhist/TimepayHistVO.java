package gov.utcourts.coriscommon.dataaccess.timepayhist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TimepayHistVO extends BaseVO { 

	private TypeInteger timepayNum = new TypeInteger("timepay_num").setFieldDescriptor(TimepayHistBO.TIMEPAYNUM.clear()).addForeignKey("timepay","timepay_num",false).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(TimepayHistBO.CREATEDATETIME.clear()).setAsPrimaryKey();
	private TypeInteger pymtCnt = new TypeInteger("pymt_cnt").setFieldDescriptor(TimepayHistBO.PYMTCNT.clear()).setNullable();
	private TypeBigDecimal pymtAmt = new TypeBigDecimal("pymt_amt").setFieldDescriptor(TimepayHistBO.PYMTAMT.clear()).setNullable();
	private TypeDate firstPymtDate = new TypeDate("first_pymt_date").setFieldDescriptor(TimepayHistBO.FIRSTPYMTDATE.clear()).setNullable();
	private TypeDate finalPymtDate = new TypeDate("final_pymt_date").setFieldDescriptor(TimepayHistBO.FINALPYMTDATE.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(TimepayHistBO.CLERKID.clear()).setNullable();
	private TypeString frequency = new TypeString("frequency").setFieldDescriptor(TimepayHistBO.FREQUENCY.clear()).setNullable();
	private TypeInteger schedDay = new TypeInteger("sched_day").setFieldDescriptor(TimepayHistBO.SCHEDDAY.clear()).setNullable();

	public TimepayHistVO() {
		super(TimepayHistBO.TABLE, TimepayHistBO.SYSTEM, TimepayHistBO.CORIS_DISTRICT_DB.setSource("D"), TimepayHistBO.CORIS_JUSTICE_DB.setSource("J"), TimepayHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TimepayHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TimepayHistVO(String source) {
		super(TimepayHistBO.TABLE, TimepayHistBO.SYSTEM, TimepayHistBO.CORIS_DISTRICT_DB.setSource("D"), TimepayHistBO.CORIS_JUSTICE_DB.setSource("J"), TimepayHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TimepayHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(timepayNum);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(pymtCnt);
		this.getPropertyList().add(pymtAmt);
		this.getPropertyList().add(firstPymtDate);
		this.getPropertyList().add(finalPymtDate);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(frequency);
		this.getPropertyList().add(schedDay);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(TimepayHistBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(TimepayHistBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(TimepayHistBO.FIRSTPYMTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(TimepayHistBO.FIRSTPYMTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(TimepayHistBO.FINALPYMTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(TimepayHistBO.FINALPYMTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}