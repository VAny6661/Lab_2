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

			NodeList nodeList = doc.getElementsByTagName("Vet");
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
			Doctor doctor = new Doctor(getTagValue("Ôàìèëèÿ_âðà÷à", element), getTagValue("Èìÿ_âðà÷à", element),
					getTagValue("Îò÷åñòâî_âðà÷à", element));
			Vet vet = new Vet(doctor, getTagValue("Èìÿ_ïèòîìöà", element), getTagValue("Äàòà_ðîæäåíèÿ", element),
					getTagValue("Äàòà_ïîñëåäíåãî_ïðè¸ìà", element),	getTagValue("Äèàãíîç", element));
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
