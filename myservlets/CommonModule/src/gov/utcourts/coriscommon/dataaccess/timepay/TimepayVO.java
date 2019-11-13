package gov.utcourts.coriscommon.dataaccess.timepay;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TimepayVO extends BaseVO { 

	private TypeInteger timepayNum = new TypeInteger("timepay_num").setFieldDescriptor(TimepayBO.TIMEPAYNUM.clear()).addForeignKey("account","timepay_num",true).addForeignKey("dc_account","timepay_num",true).addForeignKey("timepay_hist","timepay_num",false).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(TimepayBO.CREATEDATETIME.clear());
	private TypeInteger pymtCnt = new TypeInteger("pymt_cnt").setFieldDescriptor(TimepayBO.PYMTCNT.clear());
	private TypeBigDecimal pymtAmt = new TypeBigDecimal("pymt_amt").setFieldDescriptor(TimepayBO.PYMTAMT.clear());
	private TypeDate firstPymtDate = new TypeDate("first_pymt_date").setFieldDescriptor(TimepayBO.FIRSTPYMTDATE.clear());
	private TypeDate finalPymtDate = new TypeDate("final_pymt_date").setFieldDescriptor(TimepayBO.FINALPYMTDATE.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(TimepayBO.CLERKID.clear()).setNullable();
	private TypeString frequency = new TypeString("frequency").setFieldDescriptor(TimepayBO.FREQUENCY.clear()).setNullable();
	private TypeInteger schedDay = new TypeInteger("sched_day").setFieldDescriptor(TimepayBO.SCHEDDAY.clear()).setNullable();

	public TimepayVO() {
		super(TimepayBO.TABLE, TimepayBO.SYSTEM, TimepayBO.CORIS_DISTRICT_DB.setSource("D"), TimepayBO.CORIS_JUSTICE_DB.setSource("J"), TimepayBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TimepayBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TimepayVO(String source) {
		super(TimepayBO.TABLE, TimepayBO.SYSTEM, TimepayBO.CORIS_DISTRICT_DB.setSource("D"), TimepayBO.CORIS_JUSTICE_DB.setSource("J"), TimepayBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TimepayBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
			((TypeDate) this.getPropertyList().get(TimepayBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(TimepayBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(TimepayBO.FIRSTPYMTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(TimepayBO.FIRSTPYMTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(TimepayBO.FINALPYMTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(TimepayBO.FINALPYMTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}