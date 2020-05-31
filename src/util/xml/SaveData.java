package util.xml;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.widgets.MessageBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.view.DialogContainer;

import model.DataHandler;
import model.Vet;
import view.table.Tables;

public class SaveData {
	private final static String DATA_XML = "vets.xml";
	private static Document doc;

	public static void saveVets() {
		List<Vet> vets = DataHandler.getVets();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element rootElement = doc.createElementNS("", "Vets");
			doc.appendChild(rootElement);

			for (int i = 0; i < vets.size(); i++) {
				rootElement.appendChild(getVet(vets.get(i)));
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			
			DialogContainer container = new DialogContainer(Tables.getDisplay());
			String path = container.open() + "\\" + DATA_XML;
			
			StreamResult file = new StreamResult(path);
			transformer.transform(source, file);

			MessageBox errorMessege = new MessageBox(Tables.getTable().getShell());
			errorMessege.setText("Сохранение");
			errorMessege.setMessage("Сохранение базы данных в " + path + " завершено успешно!");		
			errorMessege.open();
		} catch (Exception e) {
			MessageBox errorMessege = new MessageBox(Tables.getTable().getShell());
			errorMessege.setText("Сохранение");
			errorMessege.setMessage("Неверно выбран путь сохранения!");		
			errorMessege.open();
		}
	}

	private static Node getVet(Vet vet) {
		Element vets = doc.createElement("vet");

		vets.appendChild(getElements(vets, "Фамилия_врача", vet.getSurname()));
		vets.appendChild(getElements(vets, "Имя_врача", vet.getName()));
		vets.appendChild(getElements(vets, "Отчество_врача", vet.getMiddleName()));
		vets.appendChild(getElements(vets, "Имя_питомца", vet.getPetName()));
		vets.appendChild(getElements(vets, "Дата_рождения", vet.getBirthDate()));
		vets.appendChild(getElements(vets, "Дата_последнего_приёма", vet.getLastVisitDate()));
		vets.appendChild(getElements(vets, "Диагноз", vet.getDiagnosis()));
		return vets;
	}

	private static Node getElements(Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

}
