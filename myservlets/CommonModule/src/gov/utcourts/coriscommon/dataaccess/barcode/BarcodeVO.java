package gov.utcourts.coriscommon.dataaccess.barcode;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BarcodeVO extends BaseVO { 

	private TypeString refType = new TypeString("ref_type").setFieldDescriptor(BarcodeBO.REFTYPE.clear()).setAsPrimaryKey();
	private TypeInteger refNum = new TypeInteger("ref_num").setFieldDescriptor(BarcodeBO.REFNUM.clear()).setAsPrimaryKey();
	private TypeString classType = new TypeString("class_type").setFieldDescriptor(BarcodeBO.CLASSTYPE.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(BarcodeBO.CASENUM.clear());
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(BarcodeBO.LOCNCODE.clear());
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(BarcodeBO.COURTTYPE.clear());
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(BarcodeBO.LASTNAME.clear());
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(BarcodeBO.FIRSTNAME.clear()).setNullable();
	private TypeDate docDate = new TypeDate("doc_date").setFieldDescriptor(BarcodeBO.DOCDATE.clear()).setNullable();
	private TypeString docTitle = new TypeString("doc_title").setFieldDescriptor(BarcodeBO.DOCTITLE.clear()).setNullable();
	private TypeString docCategory = new TypeString("doc_category").setFieldDescriptor(BarcodeBO.DOCCATEGORY.clear()).setNullable();
	private TypeString docType = new TypeString("doc_type").setFieldDescriptor(BarcodeBO.DOCTYPE.clear()).setNullable();
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(BarcodeBO.CASETYPE.clear());
	private TypeString ssn = new TypeString("ssn").setFieldDescriptor(BarcodeBO.SSN.clear()).setNullable();
	private TypeString citNum = new TypeString("cit_num").setFieldDescriptor(BarcodeBO.CITNUM.clear()).setNullable();
	private TypeDate violDate = new TypeDate("viol_date").setFieldDescriptor(BarcodeBO.VIOLDATE.clear()).setNullable();
	private TypeString postingParty = new TypeString("posting_party").setFieldDescriptor(BarcodeBO.POSTINGPARTY.clear()).setNullable();
	private TypeBigDecimal bondAmt = new TypeBigDecimal("bond_amt").setFieldDescriptor(BarcodeBO.BONDAMT.clear()).setNullable();
	private TypeString numPages = new TypeString("num_pages").setFieldDescriptor(BarcodeBO.NUMPAGES.clear()).setNullable();
	private TypeDate cmEntryDate = new TypeDate("cm_entry_date").setFieldDescriptor(BarcodeBO.CMENTRYDATE.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(BarcodeBO.USERIDSRL.clear()).setNullable();
	private TypeString petLastName = new TypeString("pet_last_name").setFieldDescriptor(BarcodeBO.PETLASTNAME.clear()).setNullable();
	private TypeString petFirstName = new TypeString("pet_first_name").setFieldDescriptor(BarcodeBO.PETFIRSTNAME.clear()).setNullable();
	private TypeInteger dmDocid = new TypeInteger("dm_docid").setFieldDescriptor(BarcodeBO.DMDOCID.clear()).setNullable();
	private TypeString caseSecurity = new TypeString("case_security").setFieldDescriptor(BarcodeBO.CASESECURITY.clear()).addForeignKey("security_type","code",false);
	private TypeString docSecurity = new TypeString("doc_security").setFieldDescriptor(BarcodeBO.DOCSECURITY.clear()).addForeignKey("security_type","code",false);

	public BarcodeVO() {
		super(BarcodeBO.TABLE, BarcodeBO.SYSTEM, BarcodeBO.CORIS_DISTRICT_DB.setSource("D"), BarcodeBO.CORIS_JUSTICE_DB.setSource("J"), BarcodeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BarcodeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BarcodeVO(String source) {
		super(BarcodeBO.TABLE, BarcodeBO.SYSTEM, BarcodeBO.CORIS_DISTRICT_DB.setSource("D"), BarcodeBO.CORIS_JUSTICE_DB.setSource("J"), BarcodeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BarcodeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(refType);
		this.getPropertyList().add(refNum);
		this.getPropertyList().add(classType);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(docDate);
		this.getPropertyList().add(docTitle);
		this.getPropertyList().add(docCategory);
		this.getPropertyList().add(docType);
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(ssn);
		this.getPropertyList().add(citNum);
		this.getPropertyList().add(violDate);
		this.getPropertyList().add(postingParty);
		this.getPropertyList().add(bondAmt);
		this.getPropertyList().add(numPages);
		this.getPropertyList().add(cmEntryDate);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(petLastName);
		this.getPropertyList().add(petFirstName);
		this.getPropertyList().add(dmDocid);
		this.getPropertyList().add(caseSecurity);
		this.getPropertyList().add(docSecurity);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(BarcodeBO.DOCDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(BarcodeBO.DOCDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(BarcodeBO.VIOLDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(BarcodeBO.VIOLDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(BarcodeBO.CMENTRYDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(BarcodeBO.CMENTRYDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}