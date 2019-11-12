package hw2.model.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import hw2.model.Document;
import hw2.model.File;
import hw2.model.Revision;


public interface DocumentDao {
	
	Document getDocument(int id);
	
	List<Document> getDocuments();
	
	Revision getRevision(int doc_id, int id);
	
	List<Revision> getRevisions(int doc_id);
	
    Document saveDocument(Document document);
    
    Revision saveRevision(Revision revision);
    
    File uploadFile(MultipartFile file);
    
    File getFile(int file_id);
}
