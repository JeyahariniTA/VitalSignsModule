package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.json.JSONObject;

import com.example.auditable.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "VitalSigns")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VitalSigns extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "pulse")
	private String pulse;

	@Column(name = "bloodPressure")
	private String bloodPressure;

	@Column(name = "temperature")
	private String temperature;

	@Column(name = "respirations")
	private String respirations;

	@Column(name = "bloodSugar")
	private String bloodSugar;

	@Column(name = "weight")
	private String weight;

	@Column(name = "height")
	private String height;

	@Column(name = "spo2Sat")
	private String spo2Sat;

	@Column(name = "pt")
	private String pt;

	@Column(name = "patient_id")
	private int patientId;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getRespirations() {
		return respirations;
	}

	public void setRespirations(String respirations) {
		this.respirations = respirations;
	}

	public String getBloodSugar() {
		return bloodSugar;
	}

	public void setBloodSugar(String bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSpo2Sat() {
		return spo2Sat;
	}

	public void setSpo2Sat(String spo2Sat) {
		this.spo2Sat = spo2Sat;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	@Override
	public String toString() {
		return "id: " + this.id;
	}

}
