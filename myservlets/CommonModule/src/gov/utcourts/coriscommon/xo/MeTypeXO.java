package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.dataaccess.metype.MeTypeBO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereSelectDescriptor;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

public class MeTypeXO {
	
	private static final Logger log = Logger.getLogger(MeTypeXO.class);
	
	
	public static List<MeTypeBO> getMeType(int meId, String courtType, Connection conn) throws Exception {
		
		List<MeTypeBO> meTypeListBO = new MeTypeBO(courtType).
		addWhereDescriptors(
			new WhereSelectDescriptor(
				new TableAndFieldDescriptor(CaseMeValueBO.METYPESEQ),
				new CaseMeValueBO(courtType).where(CaseMeValueBO.MEID, meId)
			)
		).
		orderBy(MeTypeBO.METYPESEQ).
		setDistinct().
		setUseConnection(conn).
		search();
		return meTypeListBO;
	}
	
	
	
}
