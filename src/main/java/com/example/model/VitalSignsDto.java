package com.example.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VitalSignsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4999671781205117900L;
	private int id;
	private String pulse;
	private String bloodPressure;
	private String temperature;
	private String respirations;
	private String bloodSugar;
	private String weight;
	private String height;
	private String spo2Sat;
	private String pt;
	private int patientId;

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

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

}
