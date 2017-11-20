package com.desafio.henryfernandez.desafio.Models;

/**
 * Created by HENRY-FERNANDEZ on 20/11/2017.
 */

public class Person {

    String Name;
    String Phone;
    String Email;

    public Person(String name, String phone, String email) {
        Name = name;
        Phone = phone;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
