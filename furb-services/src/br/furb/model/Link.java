package br.furb.model;

public class Link {

	private int id;
	private String description;

	public Link(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}
