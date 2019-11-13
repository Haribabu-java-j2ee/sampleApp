package gov.utcourts.coriscommon.enumeration;

public enum PageMode {
	DEFAULT				(""),
	GETLOCATIONSLIST 	("getlocationslist"),
	GETCASESLIST		("getcaseslist"),
	GETEVENTS			("getevents"),				
	GETPDF				("getpdf"),				
	GETSINGLEPDF		("getsinglepdf"),
	GETMESSAGES			("getmessages"),
	DISPLAYHEADER		("displayheader"),
	DISPLAYSEARCHFORM	("displaysearchform"),
	DISPLAYRESULTS		("displayresults"),
	DISPLAYDETAILS		("displaydetails"),
	UPDATEREWARD		("updatereward"),
	UPDATESANCTION		("updatesanction"),
	UPDATETITLE			("updatetitle"),
	ADDPHASE			("addphase"),
	ADDEDITPHASE		("addeditphase"),
	ADDEDITREWARD		("addeditreward"),
	ADDEDITSANCTION		("addeditsanction"),
	INSERTREWARD		("insertreward"),
	INSERTSANCTION		("insertsanction"),
	DELETEREWARD		("deletereward"),
	DELETESANCTION		("deletesanction"),
	ADDEDITGENERAL		("addeditgeneral"),
	UPDATEGENERAL		("updategeneral"),
	DELETEMINUTE		("deleteminute"),
	OPENPDF				("openpdf"),
	EMAILPDF			("emailpdf"),
	EMAIL				("email"),
	ADD					("add"),
	EDIT				("edit"),
	SAVE				("save"),
	UPDATE				("update"),
	DELETE				("delete"),
	DISABLE				("disable"),
	PRINT				("print"),
	ENABLE				("enable"),
	PUBLISH				("publish"),
	COMPARE				("compare"),
	OPEN				("open"),
	FIND				("find");
	
	PageMode (String operation) {
		this.operation = operation;
	}
	
	public String operation;
	
	/**
	 * Resolve the enumeration from a string value
	 * @param requestAttributeString String
	 * @return PageMode
	 */
	public static PageMode resolveEnumFromString(String requestAttributeString) {
		if (requestAttributeString != null) {
			if (requestAttributeString.toLowerCase().contains(GETLOCATIONSLIST.operation)) 
				return GETLOCATIONSLIST;
			else if (requestAttributeString.toLowerCase().contains(GETCASESLIST.operation))
				return GETCASESLIST;
			else if (requestAttributeString.toLowerCase().contains(GETEVENTS.operation))
				return GETEVENTS;
			else if (requestAttributeString.toLowerCase().contains(GETPDF.operation))
				return GETPDF;
			else if (requestAttributeString.toLowerCase().contains(GETSINGLEPDF.operation))
				return GETSINGLEPDF;
			else if (requestAttributeString.toLowerCase().contains(GETMESSAGES.operation))
				return GETMESSAGES;
			else if (requestAttributeString.toLowerCase().contains(DISPLAYHEADER.operation))
				return DISPLAYHEADER;
			else if (requestAttributeString.toLowerCase().contains(DISPLAYSEARCHFORM.operation))
				return DISPLAYSEARCHFORM;
			else if (requestAttributeString.toLowerCase().contains(DISPLAYRESULTS.operation))
				return DISPLAYRESULTS;
			else if (requestAttributeString.toLowerCase().contains(DISPLAYDETAILS.operation))
				return DISPLAYDETAILS;
			else if (requestAttributeString.toLowerCase().contains(UPDATEREWARD.operation))
				return UPDATEREWARD;
			else if (requestAttributeString.toLowerCase().contains(UPDATESANCTION.operation))
				return UPDATESANCTION;
			else if (requestAttributeString.toLowerCase().contains(ADDEDITREWARD.operation))
				return ADDEDITREWARD;
			else if (requestAttributeString.toLowerCase().contains(ADDEDITSANCTION.operation))
				return ADDEDITSANCTION;
			else if (requestAttributeString.toLowerCase().contains(ADDPHASE.operation))
				return ADDPHASE;
			else if (requestAttributeString.toLowerCase().contains(ADDEDITPHASE.operation))
				return ADDEDITPHASE;
			else if (requestAttributeString.toLowerCase().contains(INSERTREWARD.operation))
				return INSERTREWARD;
			else if (requestAttributeString.toLowerCase().contains(INSERTSANCTION.operation))
				return INSERTSANCTION;
			else if (requestAttributeString.toLowerCase().contains(DELETEREWARD.operation))
				return DELETEREWARD;
			else if (requestAttributeString.toLowerCase().contains(DELETESANCTION.operation))
				return DELETESANCTION;
			else if (requestAttributeString.toLowerCase().contains(UPDATEGENERAL.operation))
				return UPDATEGENERAL;
			else if (requestAttributeString.toLowerCase().contains(ADDEDITGENERAL.operation))
				return ADDEDITGENERAL;
			else if (requestAttributeString.toLowerCase().contains(UPDATETITLE.operation))
				return UPDATETITLE;
			else if (requestAttributeString.toLowerCase().contains(OPENPDF.operation))
				return OPENPDF;
			else if (requestAttributeString.toLowerCase().contains(EMAILPDF.operation))
				return EMAILPDF;
			else if (requestAttributeString.toLowerCase().contains(EMAIL.operation))
				return EMAIL;
			else if (requestAttributeString.toLowerCase().contains(ADD.operation))
				return ADD;
			else if (requestAttributeString.toLowerCase().contains(EDIT.operation))
				return EDIT;
			else if (requestAttributeString.toLowerCase().contains(SAVE.operation))
				return SAVE;
			else if (requestAttributeString.toLowerCase().contains(UPDATE.operation))
				return UPDATE;
			else if (requestAttributeString.toLowerCase().contains(DELETEMINUTE.operation))
				return DELETEMINUTE;
			else if (requestAttributeString.toLowerCase().contains(DELETE.operation))
				return DELETE;
			else if (requestAttributeString.toLowerCase().contains(DISABLE.operation))
				return DISABLE;
			else if (requestAttributeString.toLowerCase().contains(ENABLE.operation))
				return ENABLE;
			else if (requestAttributeString.toLowerCase().contains(PUBLISH.operation))
				return PUBLISH;
			else if (requestAttributeString.toLowerCase().contains(PRINT.operation))
				return PRINT;
			else if (requestAttributeString.toLowerCase().contains(COMPARE.operation))
				return COMPARE;
			else if (requestAttributeString.toLowerCase().contains(FIND.operation))
				return FIND;
			else if (requestAttributeString.toLowerCase().contains(OPEN.operation))
				return OPEN;
		} 
		return DEFAULT;
	}
	
}

