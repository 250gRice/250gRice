package sw.commands;

import sw.app_250grice.PageHandler;
import sw.exceptions.PageNameAlreadyExistsException;

public class CommandAddBlankPage implements ICommand {
	
	String name;
	PageHandler pageHandler;
	
	public CommandAddBlankPage(String name) throws PageNameAlreadyExistsException {
		pageHandler = PageHandler.getPageHandler();
		if(pageHandler.containsPageByName(name))
			throw new PageNameAlreadyExistsException();
		
		this.name = name;
	}

	@Override
	public void execute() {	
		pageHandler.addPageBlank(name);
	}
}
