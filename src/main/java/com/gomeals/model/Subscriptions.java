package com.gomeals.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscription")
public class Subscriptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sub_id;

	private int meals_remaining;

	private Date sub_date;

	private int active_status;

	private int cust_id;

	private int sup_id;

	public int getSub_id() {
		return sub_id;
	}

	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}

	public int getMeals_remaining() {
		return meals_remaining;
	}

	public void setMeals_remaining(int meals_remaining) {
		this.meals_remaining = meals_remaining;
	}

	public Date getSub_date() {
		return sub_date;
	}

	public void setSub_date(Date sub_date) {
		this.sub_date = sub_date;
	}

	public int getActive_status() {
		return active_status;
	}

	public void setActive_status(int active_status) {
		this.active_status = active_status;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public int getSup_id() {
		return sup_id;
	}

	public void setSup_id(int sup_id) {
		this.sup_id = sup_id;
	}

	public Subscriptions(int sub_id, int meals_remaining, Date sub_date, int active_status, int cust_id, int sup_id) {
		super();
		this.sub_id = sub_id;
		this.meals_remaining = meals_remaining;
		this.sub_date = sub_date;
		this.active_status = active_status;
		this.cust_id = cust_id;
		this.sup_id = sup_id;
	}

	public Subscriptions() {
		super();
	}

	@Override
	public String toString() {
		return "Subscriptions [sub_id=" + sub_id + ", meals_remaining=" + meals_remaining + ", sub_date=" + sub_date
				+ ", active_status=" + active_status + ", cust_id=" + cust_id + ", sup_id=" + sup_id + "]";
	}

}
