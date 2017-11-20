package com.desafio.henryfernandez.desafio.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HENRY-FERNANDEZ on 20/11/2017.
 */

public class Deal {

    String title;
    String description;
    ArrayList<Organization> organizations;
    ArrayList<Person> people;
    Double value;
    Date endDate;
    Boolean state;

    public Deal(String title, String description, ArrayList<Organization> organizations, ArrayList<Person> people,
                Double value, Date endDate, Boolean state) {
        this.title = title;
        this.description = description;
        this.organizations = organizations;
        this.people = people;
        this.value = value;
        this.endDate = endDate;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(ArrayList<Organization> organizations) {
        this.organizations = organizations;
    }

    public ArrayList<Person> getPersons() {
        return people;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.people = persons;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
