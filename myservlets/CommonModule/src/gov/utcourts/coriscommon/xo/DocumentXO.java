package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.docissue.DocIssueBO;
import gov.utcourts.coriscommon.dataaccess.document.DocumentBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseStoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class DocumentXO implements BaseConstants{
	private static final Logger log = Logger.getLogger(DocumentXO.class);
	public static int PRINT_SQL = RUN;
	
	public static int postDocToCoris(String courtType, DocumentBO doc, int judgeCommId, Connection conn) throws Exception{
		int documentNum = 0;
		StoredProcedureDescriptor ins = new StoredProcedureDescriptor(
				"ins_document", 
				null, 
				new InputParameters().addParameters(
						new TypeInteger().setValue(doc.getClerkId()), 
						new TypeInteger().setValue(doc.getIntCaseNum()), 
						new TypeDate().setValue(new Date()), 
						new TypeString().setValue(doc.getCategory()),
						new TypeString().setValue(doc.getDetailCode()), 
						new TypeString().setValue(doc.getTitle()), 
						new TypeString().setValue(doc.getDocSecurity())
				),
				new OutputContainer()
					.addResultTypes(new TypeInteger(), new TypeInteger())).setUseConnection(conn);

		BaseStoredProcedureDispatcher storedProcedureDispatcher = new StoredProcedureDispatcher(ins).toString(PRINT_SQL).executeQuery();
		
		List<OutputContainer> list = storedProcedureDispatcher.getResults();
		
		if (list != null && list.size() > 0){
			OutputContainer ret = list.get(0);
			if ((Integer) ret.getColumn(0) == 0){
				documentNum = (Integer) ret.getColumn(1);
			}
		}
		
		//Summary Event
		if (documentNum > 0){
			
			//It has a trigger that will insert a record to summary_event
			new DocIssueBO(courtType).setUseConnection(conn)
				.setDocumentNum(documentNum)
				.setJudgeCommId(judgeCommId)
				.toString(PRINT_SQL)
				.insert();
			
			//Do not change!!!!!!!!!!!!
			//new SummaryEventBO(courtType).setUseConnection(conn)
			//	.setIntCaseNum(doc.getIntCaseNum())
			//	.setDescr("Issued: " + doc.getTitle()).setFuncId("DOCUMENT")
			//	.setRemovedFlag("N")
			//	.setDisplayFlag("Y")
			//	.setKey1("" + documentNum)
			//	.setCreateDatetime(new Date())
			//	.setEventDatetime(new Date())
			//	.insert();	
		}
		
		return documentNum;
	}
	
	public static void updateDmDocId(Connection connection, int documentNum, int dmDocId, String courtType) throws Exception {
		try {		
			new DocumentBO(courtType).setUseConnection(connection)
				.setDmDocid(dmDocId)
				.where(DocumentBO.DOCUMENTNUM, documentNum)
				.toString(PRINT_SQL)
				.update();

		} catch (Exception e) {
			log.error("Exception in updateDmDocId: Document Number = "
					.concat(Integer.toString(documentNum)), e);
			throw e;
		}
	}
}
