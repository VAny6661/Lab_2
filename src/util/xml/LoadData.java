package util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.widgets.MessageBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.view.DialogContainer;

import model.DataHandler;
import model.Doctor;
import model.Vet;
import view.form.Pagenation;
import view.table.Tables;

public class LoadData {
	private final static String DATA_XML = "vets.xml";

	public static void loadVets() {
		try {
		DialogContainer container = new DialogContainer(Tables.getDisplay());
		String path = container.open();
		File xmlFile = new File(path);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("vet");
			List<Vet> vets = new ArrayList<Vet>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				vets.add(getVet(nodeList.item(i)));
			}

			DataHandler.setVetsList(vets);
			Tables.tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), Tables.getTable());
		} catch (Exception exc) {
			MessageBox errorMessege = new MessageBox(Tables.getTable().getShell());
			errorMessege.setText("��������");
			errorMessege.setMessage("������� ������ ����!");		
			errorMessege.open();
		}

	}

	private static Vet getVet(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			Doctor doctor = new Doctor(getTagValue("�������_�����", element), getTagValue("���_�����", element),
					getTagValue("��������_�����", element));
			Vet vet = new Vet(doctor, getTagValue("���_�������", element), getTagValue("����_��������", element),
					getTagValue("����_����������_�����", element),	getTagValue("�������", element));
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