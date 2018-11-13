package com.devlhse.springreactiveapi.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "owners")
public class Owner {

	@Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String documentNumber;
	@NotNull
	private Date createdAt = new Date();
	
    public Owner() {
	}
    
    public Owner(String id, String name, String documentNumber) {
		super();
		this.id = id;
		this.name = name;
		this.documentNumber = documentNumber;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Owner{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", documentNumber='" + documentNumber + '\'' +
				", createdAt=" + createdAt +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Owner owner = (Owner) o;
		return Objects.equals(id, owner.id) &&
				Objects.equals(name, owner.name) &&
				Objects.equals(documentNumber, owner.documentNumber) &&
				Objects.equals(createdAt, owner.createdAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, documentNumber, createdAt);
	}
}
