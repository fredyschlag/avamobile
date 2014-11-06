package br.furb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Link {
	
	private String id;
	private String name;
	private String course;
	private String description;
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}
