package gov.utcourts.coriscommon.dataaccess.listnotifiedemail;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ListNotifiedEmailVO extends BaseVO { 

	private TypeInteger notification = new TypeInteger("notification").setFieldDescriptor(ListNotifiedEmailBO.NOTIFICATION.clear());
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(ListNotifiedEmailBO.EMAILADDRESS.clear()).setNullable();
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(ListNotifiedEmailBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(ListNotifiedEmailBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(ListNotifiedEmailBO.COURTTYPE.clear()).setNullable();
	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(ListNotifiedEmailBO.DOCUMENTNUM.clear()).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(ListNotifiedEmailBO.MEID.clear()).setNullable();
	private TypeString title = new TypeString("title").setFieldDescriptor(ListNotifiedEmailBO.TITLE.clear()).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(ListNotifiedEmailBO.CREATEDATETIME.clear()).setNullable();

	public ListNotifiedEmailVO() {
		super(ListNotifiedEmailBO.TABLE, ListNotifiedEmailBO.SYSTEM, ListNotifiedEmailBO.CORIS_DISTRICT_DB.setSource("D"), ListNotifiedEmailBO.CORIS_JUSTICE_DB.setSource("J"), ListNotifiedEmailBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ListNotifiedEmailBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ListNotifiedEmailVO(String source) {
		super(ListNotifiedEmailBO.TABLE, ListNotifiedEmailBO.SYSTEM, ListNotifiedEmailBO.CORIS_DISTRICT_DB.setSource("D"), ListNotifiedEmailBO.CORIS_JUSTICE_DB.setSource("J"), ListNotifiedEmailBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ListNotifiedEmailBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(notification);
		this.getPropertyList().add(emailAddress);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(meId);
		this.getPropertyList().add(title);
		this.getPropertyList().add(createDatetime);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(ListNotifiedEmailBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(ListNotifiedEmailBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}