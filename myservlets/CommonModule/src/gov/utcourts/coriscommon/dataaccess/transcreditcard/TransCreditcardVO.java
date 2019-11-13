package gov.utcourts.coriscommon.dataaccess.transcreditcard;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TransCreditcardVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(TransCreditcardBO.INTJOURNALNUM.clear()).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(TransCreditcardBO.TRANSNUM.clear()).setAsPrimaryKey();
	private TypeString cardNum = new TypeString("card_num").setFieldDescriptor(TransCreditcardBO.CARDNUM.clear());
	private TypeString expDate = new TypeString("exp_date").setFieldDescriptor(TransCreditcardBO.EXPDATE.clear());
	private TypeString authCode = new TypeString("auth_code").setFieldDescriptor(TransCreditcardBO.AUTHCODE.clear());
	private TypeDate reconDate = new TypeDate("recon_date").setFieldDescriptor(TransCreditcardBO.RECONDATE.clear()).setNullable();
	private TypeInteger reconUserid = new TypeInteger("recon_userid").setFieldDescriptor(TransCreditcardBO.RECONUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger reconStatus = new TypeInteger("recon_status").setFieldDescriptor(TransCreditcardBO.RECONSTATUS.clear()).setNullable();

	public TransCreditcardVO() {
		super(TransCreditcardBO.TABLE, TransCreditcardBO.SYSTEM, TransCreditcardBO.CORIS_DISTRICT_DB.setSource("D"), TransCreditcardBO.CORIS_JUSTICE_DB.setSource("J"), TransCreditcardBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransCreditcardBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TransCreditcardVO(String source) {
		super(TransCreditcardBO.TABLE, TransCreditcardBO.SYSTEM, TransCreditcardBO.CORIS_DISTRICT_DB.setSource("D"), TransCreditcardBO.CORIS_JUSTICE_DB.setSource("J"), TransCreditcardBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransCreditcardBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(cardNum);
		this.getPropertyList().add(expDate);
		this.getPropertyList().add(authCode);
		this.getPropertyList().add(reconDate);
		this.getPropertyList().add(reconUserid);
		this.getPropertyList().add(reconStatus);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(TransCreditcardBO.RECONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(TransCreditcardBO.RECONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}