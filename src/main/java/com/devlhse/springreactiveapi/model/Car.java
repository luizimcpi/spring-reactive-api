package com.devlhse.springreactiveapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Car {

    public Car() {
    }

    public Car(String id, String model) {
        this.id = id;
        this.model = model;
    }

    @Id
    private String id;
    private String model;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
