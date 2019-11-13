package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.dataaccess.melineitem.MeLineItemBO;
import gov.utcourts.coriscommon.dataaccess.memaster.MeMasterBO;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.ForeignKeyDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.UnionDescriptor;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

public class MeLineItemXO {
	
	private static final Logger log = Logger.getLogger(MeLineItemXO.class);
	
	public static List<MeLineItemBO> getMeLineItem(int meId, String meTypeCode, String courtType, Connection conn) throws Exception {
//		List<MeLineItemBO> meLineItemListBO = new MeLineItemBO(courtType)
//		.setDistinct()
//		.where(MeLineItemBO.METYPECODE, meTypeCode)
//		.orderBy(MeLineItemBO.MESEQ, MeLineItemBO.JUSTIFY)
//		.toString(true)
//		.search();
		
		MeLineItemBO meLineItemBO = new MeLineItemBO(courtType).setDistinct()
		.where(
				new FindDescriptor(MeLineItemBO.ALWAYS, "Y"),
				new FindDescriptor(MeLineItemBO.METYPECODE, meTypeCode)
		).orderBy(MeLineItemBO.MESEQ, MeLineItemBO.JUSTIFY)
		.setUseConnection(conn);
		
		UnionDescriptor unionDescriptor = new UnionDescriptor(
			meLineItemBO
		).setSelect(
			MeLineItemBO.METYPECODE, 
			MeLineItemBO.MELINEITEM, 
			MeLineItemBO.MESEQ, 
			MeLineItemBO.ALWAYS, 
			MeLineItemBO.JUSTIFY, 
			MeLineItemBO.DBLSPACE, 
			MeLineItemBO.UNDERSCORE,
			MeLineItemBO.NEWPAGE,
			MeLineItemBO.GROUPHEADER
		);

		
		SearchDescriptor s1 = new SearchDescriptor(new MeLineItemBO(courtType).where(MeLineItemBO.METYPECODE, meTypeCode).setDistinct());
		SearchDescriptor s2 = new SearchDescriptor(new MeMasterBO(courtType));  															// mm
		SearchDescriptor s3 = new SearchDescriptor(new CaseMeValueBO(courtType).where(CaseMeValueBO.MEID, meId)); 						// cmv
		

		new SearchDispatcher(s1, s2, s3)
		.addForeignKey(
			new ForeignKeyDescriptor(MeMasterBO.MESCREENID, CaseMeValueBO.MESCREENID),
			new ForeignKeyDescriptor(MeMasterBO.MEFIELDID, CaseMeValueBO.MEFIELDID),
			new ForeignKeyDescriptor(MeLineItemBO.MELINEITEM, MeMasterBO.MELINEITEM)
		)
		.addUnionDescriptors(unionDescriptor)
		.search(
			MeLineItemBO.METYPECODE, 
			MeLineItemBO.MELINEITEM, 
			MeLineItemBO.MESEQ, 
			MeLineItemBO.ALWAYS, 
			MeLineItemBO.JUSTIFY, 
			MeLineItemBO.DBLSPACE, 
			MeLineItemBO.UNDERSCORE,
			MeLineItemBO.NEWPAGE,
			MeLineItemBO.GROUPHEADER
		);

		
		// ------------------------------ PRINT OUTPUT --------------------------------------------------
		
		List<MeLineItemBO> meLineItemListBO = (List<MeLineItemBO>) s1.getResults();                               // cast all results at the same time

		return meLineItemListBO;
		
	}

}
