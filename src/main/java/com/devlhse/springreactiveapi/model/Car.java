package com.devlhse.springreactiveapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Document
public class Car {

    public Car() {
    }

    public Car(String id, String model, Map<String, Object> data) {
        this.id = id;
        this.model = model;
        this.data = data;
    }

    @Id
    private String id = UUID.randomUUID().toString();
    private String model;
    private Map<String, Object> data;

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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
