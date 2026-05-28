package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Passport {
	@Id
	private int passportId;
	private String passportNumber;
	@OneToOne
	@JoinColumn(name="person_id")
 private Person person;
	public Passport() {
		// TODO Auto-generated constructor stub
	}
	public Passport(int passportId, String passportNumber) {
		super();
		this.passportId = passportId;
		this.passportNumber = passportNumber;
	}
	public int getPassportId() {
		return passportId;
	}
	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "Passport [passportId=" + passportId + ", passportNumber=" + passportNumber + ", person=" + person + "]";
	}
	

}
