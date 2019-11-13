package gov.utcourts.coriscommon.dto;

public class AttyPartyDTO extends gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO {
	private String court;
	
	public AttyPartyDTO(String courtType) {
		super(courtType);

		this.court = courtType;
	}
	private String firstName;
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	
}
