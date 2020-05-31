package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadData {
	private final static String DATA_XML = "vets.xml";

	public static void loadVets() {
		File xmlFile = new File(DATA_XML);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("sportsman");
			List<Vet> vets = new ArrayList<Vet>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				vets.add(getVet(nodeList.item(i)));
			}

			DataHandler.setVetsList(vets);
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private static Vet getVet(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			Doctor doctor = new Doctor(getTagValue("Фамилия_врача", element), getTagValue("Имя_врача", element),
					getTagValue("Отчество_врача", element));
			Vet vet = new Vet(doctor, getTagValue("Имя_питомца", element), getTagValue("Дата_рождения", element),
					getTagValue("Дата_последнего_приёма", element),	getTagValue("Диагноз", element));
			return vet;
		}

		return null;
	}

	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
}