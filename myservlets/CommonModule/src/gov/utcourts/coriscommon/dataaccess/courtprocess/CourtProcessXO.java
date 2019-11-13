package gov.utcourts.coriscommon.dataaccess.courtprocess;


public class CourtProcessXO {
	
	public static String getValueByLocationAndProcessID(String courtType, String locationCode, int processId){
		String returnValue = null;
		try {
			CourtProcessBO vo = new CourtProcessBO(courtType)
			.where(CourtProcessBO.LOCNCODE, locationCode)
			.where(CourtProcessBO.PROCESSID, processId)
			.find();
			returnValue = vo.getValue();
		} catch (Exception e) {
			
		}
		return returnValue;
	}

}
