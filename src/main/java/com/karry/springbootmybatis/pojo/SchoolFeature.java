package com.karry.springbootmybatis.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class SchoolFeature
{
    private String type = "Feature";
    private Geometry geometry;
    private Properties properties;

    // Constructors, getters, and setters

    public SchoolFeature(String name, double longitude, double latitude, double weight) {
        this.geometry = new Geometry("Point", new double[]{longitude, latitude});
        this.properties = new Properties(weight, name);
    }

    // Nested classes for Geometry and Properties

    public static class Geometry {
        private String type;
        private double[] coordinates;

        public Geometry(String type, double[] coordinates) {
            this.type = type;
            this.coordinates = coordinates;
        }

        // Getters and setters
    }

    public static class Properties {
        private double weight;
        private String name;

        public Properties(double weight, String name) {
            this.weight = weight;
            this.name = name; // Note: You can change the name to "点1", "点2", etc., if needed
        }

        // Getters and setters
    }
}
