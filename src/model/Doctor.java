package model;

public class Doctor {
	private String surname;
	private String name;
	private String middleName;

	public Doctor (String surname, String name, String middleName) {
		this.surname = surname;
		this.name = name;
		this.middleName = middleName;
	}
	public Doctor() {
	}
	public Doctor(Doctor doctor) {
		this.surname = doctor.getSurname();
		this.name = doctor.getName();
		this.middleName = doctor.getMiddleName();
	}

	public String getSurname() {
		return this.surname;
	}

	public String getName() {
		return this.name;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getFullName() {
		return surname + " " + name + " " + middleName;
	}

}
