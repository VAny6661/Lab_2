package view.form;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import controller.DeleteController;
import controller.SearchController;
import model.DataHandler;
import model.Vet;
import view.table.Tables;

public class SearchForm {
	private boolean mod;
	private List<Button> buttons;
	private Button startButton;
	private Text text1;
	private MessageBox errorMessege;
	private Text text2;
	private DateTime date;
	private Table table;

	public Table getTable() {
		if (this.mod == false)
			return this.table;
		else return null;
	}
	
	public String getText2() {
			return this.date.getDay() +"."+ (this.date.getMonth()+1) +"."+ this.date.getYear();
	}

	public MessageBox getErrorMessege() {
		return this.errorMessege;
	}

	public String getText1() {
		return this.text1.getText();
	}

	public Button getStartButton() {
		return this.startButton;
	}

	public List<Button> getButtons() {
		return this.buttons;
	}

	public void searchForm(boolean startMod) {
		this.mod = startMod;
		Shell shell = new Shell(Tables.getDisplay());

		this.errorMessege = new MessageBox(shell);
		this.errorMessege.setText("Informatoins");

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		shell.setLayout(rowLayout);

		Group groupe = new Group(shell, SWT.NONE);
		groupe.setLayout(new RowLayout(SWT.VERTICAL));
		List<Group> groups = new ArrayList<>();
		for(int i=0;i<4;i++) {
			groups.add(new Group(groupe, SWT.NONE));
			groups.get(i).setLayout(new RowLayout(SWT.VERTICAL));
		}
	
		this.buttons = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Button button1 = new Button(groups.get(0), SWT.RADIO);
			buttons.add(button1);
		}
		this.buttons.get(0).setText("По имени питомца и дате рождения");
		this.buttons.get(1).setText("По ФИО ветеринара и дате последнего приёма");
		this.buttons.get(2).setText("По фразе из диагноза");
		this.startButton = new Button(groupe, SWT.PUSH);
		
		Label label1 = new Label(groups.get(1), SWT.NONE);
		label1.setText("Критерий №1");
		this.text1 = new Text(groups.get(1), SWT.BORDER);
		RowData layoutData = new RowData();
		layoutData.width = 150;
		this.text1.setLayoutData(layoutData);
		this.text1.setText("");
		this.text1.pack();
		


		Label label2 = new Label(groups.get(2), SWT.NONE);
		label2.setText("Критерий №2");
		
		if (this.mod == true) {
			shell.setText("Delete");
			shell.setSize(350, 600);
			this.startButton.setText("Delete");

			this.date = new DateTime(groups.get(2), SWT.CALENDAR | SWT.BORDER);
			this.date.setLayoutData(layoutData);
			this.date.pack();
			
			new DeleteController(this);
		}
		else if (this.mod == false) {
			shell.setText("Search");
			shell.setSize(800, 800);
			this.startButton.setText("Search");

			this.date = new DateTime(groups.get(2), SWT.CALENDAR | SWT.BORDER);
			this.date.setLayoutData(layoutData);
			this.date.pack();
			
			this.table = new Table(groupe.getShell(), SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
			this.table.setHeaderVisible(true);
			this.table.setVisible(false);
			new SearchController(this);
		}
		this.startButton.pack();
		shell.layout();
		shell.open();
	}

}
