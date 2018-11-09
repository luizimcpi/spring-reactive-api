package com.devlhse.springreactiveapi.model;

import static com.devlhse.springreactiveapi.model.Car.CarBuilder.aCar;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Car {

    public Car() {
    }

    public Car(String id, String ownerId, String model, Map<String, Object> data) {
        this.id = id;
        this.ownerId = ownerId;
        this.model = model;
        this.data = data;
    }

    @Id
    private String id = UUID.randomUUID().toString();
    private String ownerId;
    private String model;
    private Map<String, Object> data;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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

	public static Car.CarBuilder builder(){
        return aCar();
    }

    @Override
	public String toString() {
		return "Car [id=" + id + ", ownerId=" + ownerId + ", model=" + model + ", data=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		return true;
	}

	public static final class CarBuilder {
        private Car car;

        private CarBuilder() {
            car = new Car();
        }

        public static CarBuilder aCar() {
            return new CarBuilder();
        }

        public CarBuilder id(String id) {
        	car.setId(id);
            return this;
        }
        
        public CarBuilder ownerId(String ownerId) {
        	car.setOwnerId(ownerId);
            return this;
        }
        
        public CarBuilder model(String model) {
        	car.setModel(model);
            return this;
        }
        
        public CarBuilder data(Map<String, Object> data) {
        	car.setData(data);
            return this;
        }
        
        public Car build() {
            return car;
        }
    }
}
