package com.ms.assignment.service4.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "Person")
public class Person {
	
	@JsonView(PersonJsonView.IDs.class)
	private Long id;
	@JsonView(PersonJsonView.IDs.class)
	private Long parentId;
	
	@JsonView(PersonJsonView.Normal.class)
	private String name;

	@JsonView(PersonJsonView.Normal.class)
	private String color;

	public Person() {
		super();
	}
	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "color", nullable = false)
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", parentId=" + parentId + ", name=" + name + ", color=" + color + "]";
	}
}
