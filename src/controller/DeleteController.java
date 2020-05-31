package controller;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import model.DataHandler;
import view.form.SearchForm;
import view.form.Pagenation;
import view.table.Tables;

public class DeleteController {
	private SearchForm generalForm;
	
	public DeleteController(SearchForm generalForm){
		this.generalForm = generalForm;
		this.deleteProcessing();
	}
	
	public void deleteProcessing() {
		generalForm.getStartButton().addListener(SWT.Selection, new Listener() {
		@Override
		public void handleEvent(Event arg0) {
			List<Button> buttons = generalForm.getButtons();
			Integer mod = 0;
			for (int i = 0; i < 3; i++) {
				if (buttons.get(i).getSelection() == true)
					mod = i;
			}
			Integer rezult = DataHandler.deleteVets(mod, generalForm.getText1(), generalForm.getText2());
			if (rezult == 0)
				generalForm.getErrorMessege().setMessage("Подходящих элементов не найдено");
			else
				generalForm.getErrorMessege().setMessage("Из списка удалено " + rezult + " элемента");
			Pagenation.setPage(0);
			Tables.tableConfig(DataHandler.configPage(Pagenation.getPage(), Pagenation.getPageCount(), Pagenation.getPageSize()), Tables.getTable());
			generalForm.getErrorMessege().open();
		}
	});
	}
}
