package com.desafio.henryfernandez.desafio.Models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HENRY-FERNANDEZ on 20/11/2017.
 */

public class Activity {

    String description;
    String type;
    Organization organization;
    Person person;
    Deal deal;
    Date date;
    Time hour;

    public Activity(String description, String type, Organization organization, Person person, Deal deal, Date date, Time hour) {
        this.description = description;
        this.type = type;
        this.organization = organization;
        this.person = person;
        this.deal = deal;
        this.date = date;
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }
}
