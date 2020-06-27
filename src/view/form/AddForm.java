package view.form;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import model.DataHandler;
import model.Doctor;
import model.Vet;
import view.table.Tables;

public class AddForm {
	public static void addPets() {
		Shell shell = new Shell(Tables.getDisplay());
		shell.setText("Add");
		shell.setSize(400, 800);

		MessageBox errorMessege = new MessageBox(shell);
		errorMessege.setText("Informatoins");

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		shell.setLayout(rowLayout);

		Group groupe = new Group(shell, SWT.NONE);
		groupe.setLayout(new RowLayout(SWT.VERTICAL));

		Button saveButton = new Button(groupe, SWT.PUSH);
		saveButton.setText("Save");
		saveButton.pack();

		List<Text> texts = new ArrayList<>();
		List<Label> labels = new ArrayList<>();
		List<DateTime> dates = new ArrayList<>(); 
			
			
			for(int i = 0;i<5;i++) {
			Group groupe1 = new Group(groupe, SWT.NONE);
			groupe1.setLayout(new RowLayout(SWT.HORIZONTAL));
			Text text1 = new Text(groupe1, SWT.BORDER);
			RowData layoutData1 = new RowData();
			layoutData1.width = 100;
			text1.setLayoutData(layoutData1);
			text1.setText("");		
			texts.add(text1);
			text1.pack();
			
			Label label1 = new Label(groupe1, SWT.NONE);
			labels.add(label1);}
			
			for(int i =0;i<2;i++) {
			Group groupe1 = new Group(groupe, SWT.NONE);
			groupe1.setLayout(new RowLayout(SWT.HORIZONTAL));
			
			DateTime date = new DateTime(groupe1, SWT.CALENDAR | SWT.BORDER);
			
			date.pack();
			dates.add(date);
			Label label1 = new Label(groupe1, SWT.NONE);
			labels.add(label1);
			date.pack();}
		
		labels.get(0).setText("Имя питомца");
		labels.get(1).setText("Фамилия врача");
		labels.get(2).setText("Имя врача");
		labels.get(3).setText("Отчество врача");
		labels.get(4).setText("Диагноз");
		labels.get(5).setText("Дата рождения");
		labels.get(6).setText("Дата последнего приёма");


		saveButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {
				Boolean check = true;
				for (int i = 0; i < 5; i++) {
					if (texts.get(i).getText().equals("")) {
					check = false;
					errorMessege.setMessage("Неверный ввод");
					errorMessege.open();
					break;
					}
				}
				if(dates.get(0).getYear() > dates.get(1).getYear() 
					|| (dates.get(0).getYear() == dates.get(1).getYear() && dates.get(0).getMonth() > dates.get(1).getMonth())
					||(dates.get(0).getYear() == dates.get(1).getYear() && dates.get(0).getMonth() == dates.get(1).getMonth() 
					&& dates.get(0).getDay() > dates.get(1).getDay())){
					check = false;
					errorMessege.setMessage("Неверный ввод даты");
					errorMessege.open();
				}
				if (check == true) {
					Doctor doctor = new Doctor(texts.get(1).getText(), texts.get(2).getText(), texts.get(3).getText());
					Vet vet = new Vet(doctor, texts.get(0).getText(), (dates.get(0).getYear() +"."+dates.get(0).getMonth() +"."+dates.get(0).getDay()),
							 (dates.get(1).getYear() +"."+dates.get(1).getMonth() +"."+dates.get(1).getDay()), texts.get(4).getText());
					if (DataHandler.addVet(vet) == false) {
						errorMessege.setMessage("Такая запись уже существует");
						errorMessege.open();
					}
					Tables.tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), Tables.getTable());
				}
			}
		});
		shell.open();
	}
}
