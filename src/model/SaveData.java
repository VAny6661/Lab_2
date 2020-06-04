package model;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SaveData {
	private final static String DATA_XML = "vets.xml";
	private static Document doc;

	public static void saveSportsmans() {
		List<Vet> vets = DataHandler.getVets();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element rootElement = doc.createElementNS("", "vets");
			doc.appendChild(rootElement);

			for (int i = 0; i < vets.size(); i++) {
				rootElement.appendChild(getVet(vets.get(i)));
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			StreamResult file = new StreamResult(new File(DATA_XML));
			transformer.transform(source, file);

			System.out.println("Ñîçäàíèå XML ôàéëà çàêîí÷åíî");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Node getVet(Vet vet) {
		Element pet = doc.createElement("vet");

		pet.appendChild(getElements(pet, "Ôàìèëèÿ âðà÷à", vet.getSurname()));
		pet.appendChild(getElements(pet, "Èìÿ âðà÷à", vet.getName()));
		pet.appendChild(getElements(pet, "Îò÷åñòâî âðà÷à", vet.getMiddleName()));
		pet.appendChild(getElements(pet, "Èìÿ ïèòîìöà", vet.getPetName()));
		pet.appendChild(getElements(pet, "Äàòà ðîæäåíèÿ", vet.getBirthDate()));
		pet.appendChild(getElements(pet, "Äàòà ïîñëåäíåãî ïðè¸ìà", vet.getLastVisitDate()));
		pet.appendChild(getElements(pet, "Äèàãíîç", vet.getDiagnosis()));
		return pet;
	}

	private static Node getElements(Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

}
