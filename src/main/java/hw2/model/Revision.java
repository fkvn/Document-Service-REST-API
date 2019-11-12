package hw2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Table(name="revisions")
public class Revision implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    private int id;
    
	private String notes;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy H:mm:ss z", timezone = "PST")
    @JsonProperty("Date")
	private Date timestamp;
	
    
	@OneToOne
	private File file;
    
    @JsonIgnore
    @ManyToOne
    private Document document;

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
