package com.myhealth.healthcaresystem_restapi;


public class Person {
    private String name;
    private String contactInformation;
    private String address;
    private int id;

    // Constructor
    public Person(String name, String contactInformation, String address, int id) {
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
        this.id = id;
    }

    // Getters and Setters
    
    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

