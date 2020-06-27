package util.xml;

import model.Vet;
import model.Doctor;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VetGenerator {
	public static final Integer ZERO = 0;

	public static List<String> names;
	public static List<String> surnames;
	public static List<String> middleNames;
	public static List<String> petNames;
	public static List<String> birthDates;
	public static List<String> lastVisitDates;
	public static List<String> diagnosis;


	static {
		names = new ArrayList<String>(){{
		add("Гвидо");
		add("Влас");
		add("Алоиз");
		add("Аквилий");
		add("Азат");
		add("Азим");
		add("Василий");
		add("Илья");
		add("Вадим");
		add("Акоп");
		add("Агния");
		add("Инна");
		add("Клара");
		add("Белла");
		add("Вероника");
		add("Галина");
		add("Марфа");
		add("Лина");
		add("Дарья");
		add("Елена");
		add("Полина");
		add("Ноа");
		add("Альфред");
		add("Гавриил");
		add("Вахтанг");
		add("Герхард");
		add("Гней");
		add("Арефа");
	}};
	}

	static {
		surnames = new ArrayList<String>(){{
		add("Ганди");
		add("Богомолов(-а)");
		add("Максимов(-а)");
		add("Сергеев(-а)");
		add("Фадеев(-а)");
		add("Иванов(-а)");
		add("Алексеев(-а)");
		add("Белов(-а)");
		add("Любимов(-а)");
		add("Бабкин(-а)");
		add("Лайтман");
		add("Бёрк");
		add("Вольф");
		add("Смит");
		add("Рэй");
		}};
	}

	static {
		middleNames = new ArrayList<String>(){{
		add("Ааронович(-на)");
		add("Андреевич(-на)");
		add("Вадимович(-на)");
		add("Даниилович(-на)");
		add("Дмитриевич(-на)");
		add("Егорович(-на)");
		add("Тимурович(-на)");
		add("Устинович(-на)");
		add("Михайлович(-на)");
		add("Валерьянович(-на)");
		}};
	}

	static {
		petNames = new ArrayList<String>(){{
		add("Варяг");
		add("Або");
		add("Бэмби");
		add("Арчи");
		add("Атаман");
		add("Анджей");
		add("Гарди");
		add("Заир");
		add("Ешка");
		add("Жадар");
		add("Илларий");
		add("Заир");
		add("Кэм");
		add("Леший");
		add("Мут");
		add("Тагир");
		add("Ада");
		add("Елка");
		add("Брига");
		add("Енка");
		add("Егоза");
		add("Рада");
		add("Рэсси");
		add("Таис");
		add("Фаза");
		add("Фрейда");
		add("Чана");
		add("Юси");
		add("Церро");
		add("Чудета");
		}};
	}

	static {
		birthDates = new ArrayList<String>(){{
			add("11.8.1999");
			add("23.8.2011");
			add("12.11.2009");
			add("15.2.1998");
			add("20.7.2000");
			add("14.5.2005");
			add("1.1.1994");
			add("20.5.2005");
			add("20.2.1998");
			add("15.2.1979");
			add("15.4.1999");
			add("17.4.2016");
			add("10.6.2009");
			add("30.8.2005");
			add("24.9.2001");
			add("19.4.2011");
			add("13.2.2015");
			add("22.4.2015");
			add("10.9.1945");
			add("5.4.1999");
			add("7.8.2014");
			add("10.10.2010");
		}};
	}
	static {
		lastVisitDates = new ArrayList<String>(){{
			add("11.8.2018");
			add("23.8.2019");
			add("12.11.2019");
			add("15.2.2018");
			add("20.7.2019");
			add("14.5.2018");
			add("1.1.2017");
			add("20.5.2016");
			add("20.2.2018");
			add("15.2.2017");
			add("15.4.2018");
			add("17.4.2019");
			add("10.6.2019");
			add("30.8.2018");
			add("24.9.2017");
			add("19.4.2018");
			add("13.2.2018");
			add("22.4.2019");
			add("10.9.2020");
			add("5.4.2017");
			add("7.8.2018");
			add("10.10.2019");
		}};
	}
	
	static {
		diagnosis = new ArrayList<String>(){{
			add("Бешенство");
			add("Миксоматоз");
			add("Сап");
			add("Туберкулёз");
			add("Лептоспироз");
			add("Брадзот");
			add("Энтерит");
			add("Чумка");
			add("Грипп");
			add("Сибирская язва");
			add("Хитридиомикоз");
			add("Хламидиоз");
			add("Лицевая чума");
			add("Ушиб ноги");
			add("Энцефалит");
		}};
	}

	public static <T> T getRandomData(List<T> elements) {
		return elements.get(ThreadLocalRandom.current().nextInt(0, elements.size()));
	}

	public static Doctor generatePerson() {
		return new Doctor(getRandomData(surnames), getRandomData(names), getRandomData(middleNames));
	}

	public static Vet generateVet() {

		return new Vet(generatePerson(), getRandomData(petNames), getRandomData(birthDates),getRandomData(lastVisitDates), getRandomData(diagnosis));
	}


	public static List<Vet> generateRecords(int amount) {
		List<Vet> vet = new ArrayList<>();
		while (amount != 0) {
			amount--;
			vet.add(generateVet());
		}
		return vet;
	}
}
