package org.example;

import java.util.ArrayList;

public class City {
    int id;
    String name;
    Double temp;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", temp=" + temp +
                '}';
    }

    public City(int id, String name, Double temp) {
        this.id = id;
        this.name = name;
        this.temp = temp;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getTemp() {
        return temp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
