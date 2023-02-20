package com.gomeals.model;

import jakarta.persistence.*;

@Entity
public class Addons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addon_id")
    private int addonId;

    private String item;

    private float price;

    private int sup_id;

    public int getAddonId() {
        return this.addonId;
    }

    public void setAddonId(int addonId) {
        this.addonId = addonId;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSup_id() {
        return this.sup_id;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public Addons(int addon_id, String item, float price, int sup_id) {
        this.addonId = addon_id;
        this.item = item;
        this.price = price;
        this.sup_id = sup_id;
    }

    public Addons() {
    }

    @Override
    public String toString() {
        return "{" +
                " addon_id='" + getAddonId() + "'" +
                ", item='" + getItem() + "'" +
                ", price='" + getPrice() + "'" +
                ", sup_id='" + getSup_id() + "'" +
                "}";
    }

}