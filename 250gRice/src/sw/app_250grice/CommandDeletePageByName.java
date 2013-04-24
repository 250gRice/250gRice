package sw.app_250grice;

public class CommandDeletePageByName implements ICommand {

	String pageName;
	PageHandler pageHandler;
	
	public CommandDeletePageByName(String pageName) throws PageNotFoundException {
		pageHandler = PageHandler.getPageHandler();
		if(!pageHandler.containsPageByName(pageName))
			throw new PageNotFoundException();
		
		this.pageName = pageName;
	}
	
	@Override
	public void execute() {
		pageHandler.deletePageByName(pageName);
	}

}
