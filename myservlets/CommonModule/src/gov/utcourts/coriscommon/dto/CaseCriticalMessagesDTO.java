package gov.utcourts.coriscommon.dto;

public class CaseCriticalMessagesDTO {
	
	public CaseCriticalMessagesDTO (int status, String title){
		this.status = status;
		this.title = title;
	}
	
	int 	status;
	String 	title;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
