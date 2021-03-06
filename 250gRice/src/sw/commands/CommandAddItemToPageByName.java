package sw.commands;

import sw.app_250grice.Item;
import sw.app_250grice.PageHandler;
import sw.exceptions.PageNotFoundException;

public class CommandAddItemToPageByName implements ICommand {
	
	Item itemToAdd;
	String pageName;
	PageHandler pageHandler;
	
	public CommandAddItemToPageByName(Item toAdd, String pageName) throws PageNotFoundException {
		pageHandler = PageHandler.getPageHandler();
		if(!pageHandler.containsPageByName(pageName)) 
			throw new PageNotFoundException();
		
		itemToAdd = toAdd;
		this.pageName = pageName;
		
	}

	@Override
	public void execute() {
		pageHandler.addItemToPageByName(itemToAdd, pageName);
	}
}
