package br.furb.model;

public class Student {

	private String name;
	private Link link;

	public Student(String name, Link link) {
		this.name = name;
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public Link getLink() {
		return link;
	}

}
