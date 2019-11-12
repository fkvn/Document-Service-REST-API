package hw2.model.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hw2.model.Document;
import hw2.model.File;
import hw2.model.Revision;
import hw2.model.dao.DocumentDao;

@Repository
public class DocumentDaoImpl implements DocumentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Document getDocument(int id) {
		return entityManager.find(Document.class, id);
	}

	@Override
	public List<Document> getDocuments() {
		return entityManager.createQuery("from Document", Document.class).getResultList();
	}

	@Override
	public Revision getRevision(int doc_id, int id) {
		return entityManager.createQuery("from Revision where document.id = :dept_id and id = :id",
				Revision.class).setParameter("dept_id", doc_id).setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Revision> getRevisions(int doc_id) {
		return entityManager.createQuery("from Revision where document.id = :dept_id", Revision.class).
				setParameter("dept_id", doc_id).getResultList();
	}

	@Override
	@Transactional
	public Document saveDocument(Document document) {
		return entityManager.merge(document);
	}

	@Override
	@Transactional
	public Revision saveRevision(Revision revision) {
		return entityManager.merge(revision);
	}

	@Override
	@Transactional
	public File uploadFile(MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if(!fileName.equals("") && !fileName.equals(".."))
			{
				File newFile = new File();
				newFile.setName(fileName);
				newFile.setTimestamp(new Date());

				newFile.setFileData(file.getBytes());

				newFile.setType(file.getContentType());
				
				newFile = entityManager.merge(newFile);

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/downloadFile/")
						.path(String.valueOf(newFile.getId()))
						.toUriString();

				newFile.setUrl(fileDownloadUri);

				newFile = entityManager.merge(newFile);

				return newFile;

			}
		} catch (Exception ex) {}
		
		return null;
	}

	@Override
	public File getFile(int file_id) {
		return entityManager.find(File.class, file_id);

	}

}
