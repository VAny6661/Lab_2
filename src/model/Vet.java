package model;

public class Vet extends Doctor {
	private String petName;
	private String birthDate;
	private String lastVisitDate;
	private String diagnosis;

	public Vet() {
	}

	public Vet(Doctor doctor, String petName, String birthDate, String lastVisitDate, String diagnosis) {
		super(doctor);

		this.petName = petName;
		this.birthDate = birthDate;
		this.lastVisitDate = lastVisitDate;
		this.diagnosis = diagnosis;
	}

	public String getPetName() {
		return this.petName;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public String getLastVisitDate() {
		return this.lastVisitDate;
	}

	public String getDiagnosis() {
		return this.diagnosis;
	}
}
