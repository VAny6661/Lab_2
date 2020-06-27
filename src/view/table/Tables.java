package view.table;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import model.Vet;
import util.xml.MenuBars;
import model.DataHandler;
import view.form.Pagenation;

public class Tables {
	private static Display display;
	private static Shell shell;
	private static Table table;
	
	public static Table getTable() {
		return table;
	}

	public void configTable() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("Lab_2");
		shell.setSize(800, 600);

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		


		shell.setLayout(rowLayout);
		
		MenuBars.getInstance(shell);
		Pagenation.processingPanel(shell);
		Tables.table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);	
		tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), table);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}
		display.dispose();
	}

	public static void tableConfig(List<Vet> vets, Table table) {
		if(Tables.getTable().equals(table))Pagenation.setPageCount();
		table.removeAll();

		String[] titles = { "Имя питомцы", "Дата рождения", "Дата последнего приёма", "ФИО ветеринара", "Диагноз" };
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setAlignment(SWT.CENTER);
			column.setText(titles[loopIndex]);
		}

		if (vets != null) {
			for (int loopIndex = 0; loopIndex < vets.size(); loopIndex++) {
				TableItem item = new TableItem(table, SWT.NULL);
				item.setText(0, vets.get(loopIndex).getPetName());
				item.setText(1, vets.get(loopIndex).getBirthDate());
				item.setText(2, vets.get(loopIndex).getLastVisitDate());
				item.setText(3, vets.get(loopIndex).getFullName());
				item.setText(4, vets.get(loopIndex).getDiagnosis());
			}
		}
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++)
			table.getColumn(loopIndex).pack();
		table.getShell().layout();
		Pagenation.getPageInfo();
	}

	public static Display getDisplay() {
		return display;
	}
}
