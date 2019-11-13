package gov.utcourts.coriscommon.dto;

 

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author subba.kondabala
 *
 */ 

@XmlRootElement(name="AutoExpungementResponseFile" ) 
public class AutoExpungementResponseDTO {
	
	private List<ExpungementResponseDTO> expungementResponseList;
	
	public List<ExpungementResponseDTO> getExpungementResponseList() {
		return expungementResponseList;
	}

	@XmlElement(name="ExpungementResponse" )
	public void setExpungementResponseList(List<ExpungementResponseDTO> expungementResponseList) {
		this.expungementResponseList = expungementResponseList;
	}	 
	
}
