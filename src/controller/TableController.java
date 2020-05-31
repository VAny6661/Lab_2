package controller;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;

import util.xml.LoadData;
import util.xml.SaveData;
import view.form.AddForm;
import view.form.SearchForm;

public class TableController {

	public static void listenerProcessin(List<MenuItem> listeners) {

		listeners.get(0).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				LoadData.loadVets();
			}
		});

		listeners.get(1).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				SaveData.saveVets();
			}
		});

		listeners.get(2).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				AddForm.addPets();
			}
		});

		listeners.get(3).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				SearchForm form = new SearchForm();
				form.searchForm(true);
			}
		});

		listeners.get(4).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				SearchForm form = new SearchForm();
				form.searchForm(false);
			}
		});
	}
}
