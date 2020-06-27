package view.form;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import model.DataHandler;
import view.table.Tables;

public class Pagenation {
	private static Integer vetsCount;
	private static Integer pageSize = 10;
	private static Integer countPage;
	private static Integer page = 0;
	private static List<Label> labels;

	public static void processingPanel(Shell shell) {
		setVetsCount();

		MessageBox errorMessege = new MessageBox(shell);
		errorMessege.setText("Некорректный ввод");
		errorMessege.setMessage("Введите число от 1 до 99");

		Composite group = new Composite(shell, SWT.BORDER);
		group.setLayout(new RowLayout(SWT.HORIZONTAL));

		List<Composite> groups = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			Composite group1 = new Composite(group, SWT.BORDER);
			group1.setLayout(new RowLayout(SWT.VERTICAL));
			groups.add(group1);
		}

		Button beginButton = new Button(groups.get(0), SWT.PUSH);
		beginButton.setText("on first page");
		beginButton.pack();
		beginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Pagenation.page = 0;
				Tables.tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), Tables.getTable());
			}
		});

		Button endButton = new Button(groups.get(0), SWT.PUSH);
		endButton.setText("on last page");
		endButton.pack();
		endButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				page = countPage - 1;
				Tables.tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), Tables.getTable());
			}
		});

		Button previousButton = new Button(groups.get(1), SWT.PUSH);
		previousButton.setText("on previous page");
		previousButton.pack();
		previousButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (page > 0) {
					page--;
					Tables.tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), Tables.getTable());
				}
			}
		});

		Button nextButton = new Button(groups.get(1), SWT.PUSH);
		nextButton.setText("on next page");
		nextButton.pack();
		nextButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (page < countPage - 1) {
					page++;
					Tables.tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), Tables.getTable());
				}
			}
		});

		Button savePageSizeButton = new Button(groups.get(2), SWT.PUSH);
		savePageSizeButton.setText("Save page size");
		savePageSizeButton.pack();

		Combo combo = new Combo(groups.get(2), SWT.DROP_DOWN);
		String[] items = new String[] { "5", "10", "15" };
		combo.setItems(items);
		RowData layoutData = new RowData();
		layoutData.width = 60;
		combo.setLayoutData(layoutData);
		combo.pack();

		savePageSizeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					Integer count = Integer.valueOf(combo.getText());
					if (count > 0 & count < 100) {
						Pagenation.pageSize = count;
					} else
						errorMessege.open();

				} catch (NumberFormatException e) {
					errorMessege.open();
				}
				page = 0;
				Tables.tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), Tables.getTable());
			}
		});

		labels = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Label label = new Label(groups.get(3), SWT.NONE);
			labels.add(label);
		}
		getPageInfo();

	}

	public static void getPageInfo() {
		setPageCount();
		labels.get(0).setText("page size = " + pageSize);
		labels.get(1).setText("number of record = " + vetsCount);
		labels.get(2).setText("number of page = " + Integer.toString(page + 1));
	}

	public static void setPageCount() {
		setVetsCount();
		if (vetsCount % pageSize == 0)
			countPage = vetsCount / pageSize;
		else
			countPage = (vetsCount / pageSize) + 1;
	}

	public static void setPage(Integer page) {
		Pagenation.page = page;
	}

	public static Integer getPage() {
		return Pagenation.page;
	}

	public static void setVetsCount() {
		Pagenation.vetsCount = DataHandler.getVetCount();
	}

	public static void setPageSize(Integer pageSize) {
		Pagenation.pageSize = pageSize;
	}

	public static Integer getPageSize() {
		return Pagenation.pageSize;
	}

	public static Integer getPageCount() {
		setPageCount();
		return Pagenation.countPage;
	}
}
