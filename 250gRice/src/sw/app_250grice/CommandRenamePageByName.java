package sw.app_250grice;

public class CommandRenamePageByName implements ICommand {
	
	String oldPageName;
	String newPageName;
	PageHandler pageHandler;
	
	public CommandRenamePageByName(String oldPageName, String newPageName) throws PageNotFoundException, PageNameAlreadyExistsException {
		pageHandler = PageHandler.getPageHandler();
		if(!pageHandler.containsPageByName(oldPageName))
			throw new PageNotFoundException();
		
		if(pageHandler.containsPageByName(newPageName))
			throw new PageNameAlreadyExistsException();
		
		this.oldPageName = oldPageName;
		this.newPageName = newPageName;
	}

	@Override
	public void execute() {
		pageHandler.renamePageByName(oldPageName, newPageName);
	}

}
