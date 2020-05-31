package model;

import java.util.ArrayList;
import java.util.List;

import util.xml.VetGenerator;

public class DataHandler {
	private static List<Vet> vetsList;
	private static Integer vetCount = 50;
	static {
		vetsList = VetGenerator.generateRecords(vetCount);
	}

	public static List<Vet> getVets() {
		return vetsList;
	}

	public static void setVetsList(List<Vet> vets) {
		vetsList = vets;
	}

	public static List<Vet> configPage(Integer page, Integer pageCount, Integer pageSize) {
		vetsList = DataHandler.getVets();
		if (vetsList.isEmpty() == true)
			return null;

		List<List<Vet>> vetsPage = new ArrayList<>();
		for (int i = 0; i < pageCount; i++) {
			vetsPage.add(new ArrayList<Vet>());
			for (int j = 0; j < pageSize; j++) {
				if (i * pageSize + j < vetsList.size())
					vetsPage.get(i).add(vetsList.get(i * pageSize + j));
			}
		}
		if (vetsPage.get(page).isEmpty())
			return null;
		return vetsPage.get(page);
	}

	public static Integer getVetCount() {
		return DataHandler.vetCount;
	}

	public static Integer deleteVets(Integer mod, String parameter1, String parameter2) {
		Integer deleteCount = 0;
		if (mod == 0) {
			for (int i = vetsList.size() - 1; i >= 0; i--) {
				if (vetsList.get(i).getPetName().equals(parameter1)
					& vetsList.get(i).getBirthDate().equals(parameter2)) {
					vetsList.remove(i);
					deleteCount++;
				}
			}
		} else if (mod == 1) {
			for (int i = vetsList.size() - 1; i >= 0; i--) {
				if (vetsList.get(i).getFullName().equals(parameter1)
						& vetsList.get(i).getLastVisitDate().equals(parameter2)) {
						vetsList.remove(i);
						deleteCount++;
					}
				}
			} 
		else if (mod == 2) {
			for (int i = vetsList.size() - 1; i >= 0; i--) {
				if (vetsList.get(i).getDiagnosis().contains(parameter1)) {
					vetsList.remove(i);
					deleteCount++;
				}
			}
		}
		vetCount = vetsList.size();
		return deleteCount;
	}

	public static List<Vet> searchVet(Integer mod, String parameter1, String parameter2) {
		List<Vet> chosenVets = new ArrayList<>();
		if (mod == 0) {
			for (int i = 0; i < vetsList.size(); i++) {
				if (vetsList.get(i).getPetName().equals(parameter1)
					& vetsList.get(i).getBirthDate().equals(parameter2)) {
					chosenVets.add(vetsList.get(i));
				}
			}
		} else if (mod == 1) {
			for (int i = 0; i < vetsList.size(); i++) {
					if (vetsList.get(i).getFullName().equals(parameter1)
							& vetsList.get(i).getLastVisitDate().equals(parameter2)) {
						chosenVets.add(vetsList.get(i));
					}
				}
			} 
		else if (mod == 2) {
			for (int i = 0; i < vetsList.size(); i++) {
				if (vetsList.get(i).getDiagnosis().contains(parameter1)) {
					chosenVets.add(vetsList.get(i));
				}
			}
		}
		return chosenVets;
	}

	public static Boolean addVet(Vet doctor) {
		Boolean check = true;
		for (int i = 0; i < vetsList.size(); i++) {
			if (vetsList.get(i).getFullName().equals(doctor.getFullName()))
				check = false;
		}
		if (check == true) {
			vetsList.add(doctor);
			vetCount++;
		}
		return check;
	}
}
