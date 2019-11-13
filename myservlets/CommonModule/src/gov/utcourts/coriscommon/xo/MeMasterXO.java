package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.dataaccess.memaster.MeMasterBO;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.ForeignKeyDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.UnionDescriptor;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

public class MeMasterXO {
	
	private static final Logger log = Logger.getLogger(MeMasterXO.class);
	
	public static List<MeMasterBO> getMeMaster(int meId, String meLineItem, String courtType, Connection conn) throws Exception {
		
		MeMasterBO meMasterBO = new MeMasterBO(courtType).setDistinct()
		.where(
				new FindDescriptor(MeMasterBO.ALWAYS, "Y"),
				new FindDescriptor(MeMasterBO.MELINEITEM, meLineItem)
		)
		.orderBy(MeMasterBO.MESEGMENT)
		.setUseConnection(conn);
		
		UnionDescriptor unionDescriptor = new UnionDescriptor(meMasterBO).setSelect(
			MeMasterBO.MELINEITEM, 
			MeMasterBO.MESEGMENT, 
			MeMasterBO.METEXT, 
			MeMasterBO.MESCREENID, 
			MeMasterBO.MEFIELDID, 
			MeMasterBO.ALIGN, 
			MeMasterBO.NEWLINE,
			MeMasterBO.ALWAYS,
			MeMasterBO.MULTIVALUETEXT,
			MeMasterBO.TEXTONLY
		);
		
		SearchDescriptor s1 = new SearchDescriptor(new MeMasterBO(courtType).where(MeMasterBO.MELINEITEM, meLineItem).setDistinct());
		SearchDescriptor s2 = new SearchDescriptor(new CaseMeValueBO(courtType).where(CaseMeValueBO.MEID, meId)); 						// cmv
		

		new SearchDispatcher(s1, s2)
		.addForeignKey(
			new ForeignKeyDescriptor(MeMasterBO.MESCREENID, CaseMeValueBO.MESCREENID),
			new ForeignKeyDescriptor(MeMasterBO.MEFIELDID, CaseMeValueBO.MEFIELDID)
		)
		.addUnionDescriptors(unionDescriptor)
		.search(
				MeMasterBO.MELINEITEM, 
				MeMasterBO.MESEGMENT, 
				MeMasterBO.METEXT, 
				MeMasterBO.MESCREENID, 
				MeMasterBO.MEFIELDID, 
				MeMasterBO.ALIGN, 
				MeMasterBO.NEWLINE,
				MeMasterBO.ALWAYS,
				MeMasterBO.MULTIVALUETEXT,
				MeMasterBO.TEXTONLY
		);

		
		List<MeMasterBO> meMasterListBO = (List<MeMasterBO>) s1.getResults();                               // cast all results at the same time
		return meMasterListBO;
	}
}
