package sw.app_250grice;

import java.util.ArrayList;
import java.util.List;

public class PageHandler {
	
	private static PageHandler singletonPageHandler;
	private List<Page> pages;
		
	private PageHandler(){
		pages = new ArrayList<Page>();		
	}
	
	public static PageHandler getPageHandler() {
		if(singletonPageHandler == null)
			singletonPageHandler = new PageHandler();
		return singletonPageHandler;
	}
	
	public boolean addPageBlank(String name) {
		Page toSearch = getPageByName(name);
		if(toSearch != null)
			return false;
		
		pages.add(new Page(name));		
		return true; 		
	}
	
	public boolean containsPageByName(String name) {
		Page toSearch = getPageByName(name);
		if(toSearch == null)
			return false;
			
		return true;
	}
	
	
	private Page getPageByName(String name) {
		for(Page page : pages)
			if(page.getName() == name)
				return page;
			
		return null;
	}
	
}
