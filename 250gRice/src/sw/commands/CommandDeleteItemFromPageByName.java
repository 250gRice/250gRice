package sw.commands;

import sw.app_250grice.PageHandler;
import sw.app_250grice.Units;
import sw.exceptions.ItemNotFoundException;
import sw.exceptions.PageNotFoundException;

public class CommandDeleteItemFromPageByName implements ICommand {
	
	String itemName;
	Units unit;
	String pageName;
	PageHandler pageHandler;
	
	public CommandDeleteItemFromPageByName(String  itemName, Units unit, String pageName) throws PageNotFoundException, ItemNotFoundException {
		pageHandler = PageHandler.getPageHandler();
		if(!pageHandler.containsPageByName(pageName)) 
			throw new PageNotFoundException();
		
		if(!pageHandler.containsItemInPageByNameAndUnit(itemName, unit, pageName))
			throw new ItemNotFoundException();
		
		this.itemName = itemName;
		this.unit = unit;
		this.pageName = pageName;
		
	}

	@Override
	public void execute() {
		pageHandler.removeItemFromPageByName(itemName, unit, pageName);
	}

}
