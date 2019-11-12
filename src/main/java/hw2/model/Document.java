package hw2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "documents")
@JsonPropertyOrder({"id", "name", "revisions", "updated", "revisionList"})
public class Document implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    @JsonView(Views.Simple.class)
    private int id;
    
    @JsonView(Views.Simple.class)
	private String name;
	
	@OneToMany(mappedBy="document")
	@OrderBy("timestamp")
	private List<Revision> revisionList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Revision> getRevisionList() {
		return revisionList;
	}

	public void setRevisionList(List<Revision> revisionList) {
		this.revisionList = revisionList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@JsonView(Views.Simple.class)
	public Integer revisions() {
		return getRevisionList().size();
	}
	
	@JsonView(Views.Simple.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy H:mm:ss z", timezone = "PST")
	public Date updated() {
		return getRevisionList().get(getRevisionList().size() - 1).getTimestamp();
	}

}

