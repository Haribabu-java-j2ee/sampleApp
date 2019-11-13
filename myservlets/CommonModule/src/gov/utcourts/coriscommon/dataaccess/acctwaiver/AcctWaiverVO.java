package gov.utcourts.coriscommon.dataaccess.acctwaiver;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AcctWaiverVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AcctWaiverBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false).setNullable().setAsPrimaryKey();
	private TypeDate createDate = new TypeDate("create_date").setFieldDescriptor(AcctWaiverBO.CREATEDATE.clear());
	private TypeString waiverStatus = new TypeString("waiver_status").setFieldDescriptor(AcctWaiverBO.WAIVERSTATUS.clear());
	private TypeDate statusDate = new TypeDate("status_date").setFieldDescriptor(AcctWaiverBO.STATUSDATE.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(AcctWaiverBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);

	public AcctWaiverVO() {
		super(AcctWaiverBO.TABLE, AcctWaiverBO.SYSTEM, AcctWaiverBO.CORIS_DISTRICT_DB.setSource("D"), AcctWaiverBO.CORIS_JUSTICE_DB.setSource("J"), AcctWaiverBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctWaiverBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AcctWaiverVO(String source) {
		super(AcctWaiverBO.TABLE, AcctWaiverBO.SYSTEM, AcctWaiverBO.CORIS_DISTRICT_DB.setSource("D"), AcctWaiverBO.CORIS_JUSTICE_DB.setSource("J"), AcctWaiverBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctWaiverBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(createDate);
		this.getPropertyList().add(waiverStatus);
		this.getPropertyList().add(statusDate);
		this.getPropertyList().add(useridSrl);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(AcctWaiverBO.CREATEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctWaiverBO.CREATEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AcctWaiverBO.STATUSDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctWaiverBO.STATUSDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}