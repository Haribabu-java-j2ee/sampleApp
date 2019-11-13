package gov.utcourts.coriscommon.dataaccess.checkbatch;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CheckBatchVO extends BaseVO { 

	private TypeInteger batchNum = new TypeInteger("batch_num").setFieldDescriptor(CheckBatchBO.BATCHNUM.clear()).addForeignKey("check_batch_detl","batch_num",false).addForeignKey("check_detl","batch_num",false).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CheckBatchBO.LOCNCODE.clear()).addForeignKey("check_batch_detl","locn_code",false).addForeignKey("check_detl","locn_code",false).addForeignKey("profile","locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CheckBatchBO.COURTTYPE.clear()).addForeignKey("check_batch_detl","court_type",false).addForeignKey("check_detl","court_type",false).addForeignKey("profile","court_type",false).setAsPrimaryKey();
	private TypeString fileName = new TypeString("file_name").setFieldDescriptor(CheckBatchBO.FILENAME.clear());
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(CheckBatchBO.CREATEDATETIME.clear());
	private TypeDate confirmDatetime = new TypeDate("confirm_datetime").setFieldDescriptor(CheckBatchBO.CONFIRMDATETIME.clear()).setNullable();
	private TypeDate cancelDatetime = new TypeDate("cancel_datetime").setFieldDescriptor(CheckBatchBO.CANCELDATETIME.clear()).setNullable();
	private TypeInteger cancelUserid = new TypeInteger("cancel_userid").setFieldDescriptor(CheckBatchBO.CANCELUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger createUserid = new TypeInteger("create_userid").setFieldDescriptor(CheckBatchBO.CREATEUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();

	public CheckBatchVO() {
		super(CheckBatchBO.TABLE, CheckBatchBO.SYSTEM, CheckBatchBO.CORIS_DISTRICT_DB.setSource("D"), CheckBatchBO.CORIS_JUSTICE_DB.setSource("J"), CheckBatchBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CheckBatchBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CheckBatchVO(String source) {
		super(CheckBatchBO.TABLE, CheckBatchBO.SYSTEM, CheckBatchBO.CORIS_DISTRICT_DB.setSource("D"), CheckBatchBO.CORIS_JUSTICE_DB.setSource("J"), CheckBatchBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CheckBatchBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(batchNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(fileName);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(confirmDatetime);
		this.getPropertyList().add(cancelDatetime);
		this.getPropertyList().add(cancelUserid);
		this.getPropertyList().add(createUserid);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CheckBatchBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CheckBatchBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CheckBatchBO.CONFIRMDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CheckBatchBO.CONFIRMDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CheckBatchBO.CANCELDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CheckBatchBO.CANCELDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}