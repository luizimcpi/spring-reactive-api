package com.devlhse.springreactiveapi.dto.request;

import javax.validation.constraints.NotNull;

public class OwnerRequest {

    @NotNull
    private String name;
    @NotNull
    private String documentNumber;

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
}
