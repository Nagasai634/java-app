package com.example.demo;

public class Person {
    private String name;
    private int age;
    private String description;

    public Person() {}

    public Person(String name, int age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return name + "," + age + "," + description;
    }

    public static Person fromString(String data) {
        String[] parts = data.split(",", 3);
        if (parts.length == 3) {
            return new Person(parts[0], Integer.parseInt(parts[1]), parts[2]);
        }
        return null;
    }
}