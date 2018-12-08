package com.devlhse.springreactiveapi.dto.response;

import java.util.Date;

public class OwnerResponse {

    private String id;
    private String name;
    private String documentNumber;
    private Date createdAt;
    private Date updatedAt;

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public static final class OwnerResponseBuilder {
        private OwnerResponse ownerResponse;

        private OwnerResponseBuilder() {
            ownerResponse = new OwnerResponse();
        }

        public static OwnerResponseBuilder anOwnerResponse() {
            return new OwnerResponseBuilder();
        }

        public OwnerResponseBuilder id(String id) {
            ownerResponse.setId(id);
            return this;
        }

        public OwnerResponseBuilder name(String name) {
            ownerResponse.setName(name);
            return this;
        }

        public OwnerResponseBuilder documentNumber(String documentNumber) {
            ownerResponse.setDocumentNumber(documentNumber);
            return this;
        }

        public OwnerResponseBuilder createdAt(Date createdAt) {
            ownerResponse.setCreatedAt(createdAt);
            return this;
        }

        public OwnerResponseBuilder updatedAt(Date updatedAt) {
            ownerResponse.setUpdatedAt(updatedAt);
            return this;
        }

        public OwnerResponse build() {
            return ownerResponse;
        }
    }
}
