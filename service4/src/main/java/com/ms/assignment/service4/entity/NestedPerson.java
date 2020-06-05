package com.ms.assignment.service4.entity;

import java.util.List;

public class NestedPerson {
	private String name;
	private List<Person> subClasses;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Person> getSubClasses() {
		return subClasses;
	}
	public void setSubClasses(List<Person> subClasses) {
		this.subClasses = subClasses;
	}
	@Override
	public String toString() {
		return "NestedPerson [name=" + name + ", subClasses=" + subClasses + "]";
	}
}
