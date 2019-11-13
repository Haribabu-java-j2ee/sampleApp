package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;

public class KaseDTO extends KaseBO {

	private String court;
	private String caseTypeCategory;
    private String defendantFirstName;
    private String defendantLastName;
    private int defendantCount;
    private String plaintiffFirstName;
    private String plaintiffLastName;
    private int plaintiffCount;
    private String caseTitle;
    private int titleDefNum;
    private int titlePlaNum;
    private String caseTypeDescr;
    
	public KaseDTO(String court) {
		super(court);
		this.court = court;
	}

	public String getDefendantFirstName() {
		return defendantFirstName;
	}

	public void setDefendantFirstName(String defendantFirstName) {
		this.defendantFirstName = defendantFirstName;
	}

	public String getDefendantLastName() {
		return defendantLastName;
	}

	public void setDefendantLastName(String defendantLastName) {
		this.defendantLastName = defendantLastName;
	}

	public String getPlaintiffFirstName() {
		return plaintiffFirstName;
	}

	public void setPlaintiffFirstName(String plaintiffFirstName) {
		this.plaintiffFirstName = plaintiffFirstName;
	}

	public String getPlaintiffLastName() {
		return plaintiffLastName;
	}

	public void setPlaintiffLastName(String plaintiffLastName) {
		this.plaintiffLastName = plaintiffLastName;
	}

	public int getTitleDefNum() {
		return titleDefNum;
	}

	public void setTitleDefNum(int titleDefNum) {
		this.titleDefNum = titleDefNum;
	}

	public int getTitlePlaNum() {
		return titlePlaNum;
	}

	public void setTitlePlaNum(int titlePlaNum) {
		this.titlePlaNum = titlePlaNum;
	}

	public String getCaseTypeCategory() {
		return caseTypeCategory;
	}

	public void setCaseTypeCategory(String caseTypeCategory) {
		this.caseTypeCategory = caseTypeCategory;
	}

	public int getDefendantCount() {
		return defendantCount;
	}

	public void setDefendantCount(int defendantCount) {
		this.defendantCount = defendantCount;
	}

	public int getPlaintiffCount() {
		return plaintiffCount;
	}

	public void setPlaintiffCount(int plaintiffCount) {
		this.plaintiffCount = plaintiffCount;
	}

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getCaseTypeDescr() {
		return caseTypeDescr;
	}

	public void setCaseTypeDescr(String caseTypeDescr) {
		this.caseTypeDescr = caseTypeDescr;
	}
	
	
	
}
