package gov.utcourts.coriscommon.dataaccess.otherdoctitle;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OtherDocTitleVO extends BaseVO { 

	private TypeInteger docTitleSrl = new TypeInteger("doc_title_srl").setFieldDescriptor(OtherDocTitleBO.DOCTITLESRL.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(OtherDocTitleBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false);
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(OtherDocTitleBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false);
	private TypeString title = new TypeString("title").setFieldDescriptor(OtherDocTitleBO.TITLE.clear()).setNullable();

	public OtherDocTitleVO() {
		super(OtherDocTitleBO.TABLE, OtherDocTitleBO.SYSTEM, OtherDocTitleBO.CORIS_DISTRICT_DB.setSource("D"), OtherDocTitleBO.CORIS_JUSTICE_DB.setSource("J"), OtherDocTitleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OtherDocTitleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OtherDocTitleVO(String source) {
		super(OtherDocTitleBO.TABLE, OtherDocTitleBO.SYSTEM, OtherDocTitleBO.CORIS_DISTRICT_DB.setSource("D"), OtherDocTitleBO.CORIS_JUSTICE_DB.setSource("J"), OtherDocTitleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OtherDocTitleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(docTitleSrl);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(title);
	}

}