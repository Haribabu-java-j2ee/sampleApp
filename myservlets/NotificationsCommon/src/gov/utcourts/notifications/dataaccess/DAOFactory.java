package gov.utcourts.notifications.dataaccess;

/**
 * This is the class used to get any DAO.  This follows the factory pattern
 * as outlined by Sun.  This object also abstracts the method of obtaining 
 * a DAO to facilitate the event that DAO's might be cached, etc.  Each get*DAO
 * method returns the type specified.
 */
public class DAOFactory {
	
	/**
	 * Following the singleton pattern, this static member is
	 * used to hold on to a single instance of the DAOFactory.
	 */
	private static DAOFactory theFactory;
	
	/**
	 * Private Constructor, should not be instantiated this way, use the getFactory() method.
	 */
	private DAOFactory() {
		super();
	}
	
	/**
	 * Singleton method for returning a DAOFactory object.
	 * 
	 * @return the single instance of the DAOFactory.
	 */
	public static DAOFactory getFactory() {
		if (theFactory == null) {
			theFactory = new DAOFactory();
		}
		return theFactory;
	}
	
	public NoticeSubscribeDAO getNoticeSubscribeDAO() {
		return new NoticeSubscribeDAO();
	}
	
	public NoticeRequestDAO getNoticeRequestDAO() {
		return new NoticeRequestDAO();
	}
	
	public NoticeStatusDAO getNoticeStatusDAO() {
		return new NoticeStatusDAO();
	}
}