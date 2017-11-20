package com.desafio.henryfernandez.desafio.Models;

/**
 * Created by HENRY-FERNANDEZ on 20/11/2017.
 */

public class Organization {

    String Name;
    String Address;
    String Phone;

    public Organization(String name, String address, String phone) {
        Name = name;
        Address = address;
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
