package sw.app_250grice;

public class CommandDeleteItemFromPageByName implements ICommand {
	
	String itemName;
	Units unit;
	String pageName;
	PageHandler pageHandler;
	
	public CommandDeleteItemFromPageByName(String  itemName, Units unit, String pageName) throws PageNotFoundException, ItemNotFoundException {
		pageHandler = PageHandler.getPageHandler();
		if(!pageHandler.containsPageByName(pageName)) 
			throw new PageNotFoundException();
		
		if(!pageHandler.constainsItemInPageByNameAndUnit(itemName, unit, pageName))
			throw new ItemNotFoundException();
		
		this.itemName = itemName;
		this.unit = unit;
		this.pageName = pageName;
		
	}

	@Override
	public void execute() {
		pageHandler.deleteItemFromPageByName(itemName, unit, pageName);
	}

}
