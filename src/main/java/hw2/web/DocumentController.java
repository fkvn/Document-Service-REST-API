package hw2.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import hw2.model.Document;
import hw2.model.File;
import hw2.model.Revision;
import hw2.model.Views;
import hw2.model.dao.DocumentDao;

@RestController
@RequestMapping("/")
public class DocumentController {

	@Autowired
	private DocumentDao documentDao;


	@GetMapping("documents")
	@JsonView(Views.Simple.class)
	public List<Document> listDocs(){		
		return documentDao.getDocuments();
	}

	@PostMapping("documents")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer addDoc(@RequestParam Map<String, Object> params,
			@RequestParam("file") MultipartFile file) {
		Document document = new Document();
		document.setName((String)params.get("name"));

		Revision revision = new Revision();
		if (params.get("notes") != null)
			revision.setNotes((String)params.get("name"));

		File newFile = documentDao.uploadFile(file);

		if (newFile != null)
		{
			document = documentDao.saveDocument(document);

			revision.setFile(newFile);
			revision.setDocument(document);
			revision.setTimestamp(new Date());
			revision = documentDao.saveRevision(revision);
			return document.getId();
		}
		else
			return -1;
	}
	
	@GetMapping("documents/{id}")
	public Document getDoc(@PathVariable Integer id){		
		return documentDao.getDocument(id);
	}

	@GetMapping("documents/{doc_id}/revisions")
	public List<Revision> getRevisions(@PathVariable Integer doc_id){		
		return documentDao.getRevisions(doc_id);
	}

	@PostMapping("documents/{doc_id}/revisions")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer addRevision(@PathVariable Integer doc_id, @RequestParam(required=false) String notes, 
			@RequestParam("file") MultipartFile file){	

		Revision revision = new Revision();

		revision.setNotes(notes);

		File newFile = documentDao.uploadFile(file);

		Document doc = documentDao.getDocument(doc_id);

		revision.setFile(newFile);
		revision.setDocument(doc);
		revision.setTimestamp(new Date());
		revision = documentDao.saveRevision(revision);

		return revision.getId();
	}
	
	@GetMapping ("documents/{doc_id}/revisions/{rev_id}")
	public Revision getRevision(@PathVariable Integer doc_id, @PathVariable Integer rev_id) {
		return documentDao.getRevision(doc_id, rev_id);
	}

	@PatchMapping("documents/{doc_id}/revisions/{rev_id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateRevision(@PathVariable Integer doc_id, @PathVariable Integer rev_id,
			@RequestBody Map<String, Object> update) {
		Revision revision = documentDao.getRevision(doc_id, rev_id);
		for (String key : update.keySet()) {
			switch (key) {
			case "notes":
				revision.setNotes((String) update.get(key));
				revision.setTimestamp(new Date());
				break;
			default:
			}
		}
		documentDao.saveRevision(revision);
	}

	@GetMapping("files/{file_id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Resource> downloadFile(@PathVariable Integer file_id) {
        // Load file from database
        File file = documentDao.getFile(file_id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getFileData()));
    } 

}
