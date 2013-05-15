package sw.app_250grice;

import java.util.ArrayList;
import java.util.List;

import sw.exceptions.PageNotFoundException;

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
	
	public void addPageBlank(String name) {
		if(!containsPageByName(name))
			pages.add(new Page(name));			
	}
	
	public void addPageExisting(Page page) {
		if(!containsPageByName(page.name))
			pages.add(page.clone());			
	}
	
	public boolean containsPageByName(String name) {
		try {
		getPageByName(name);
		}
		catch (PageNotFoundException ex) {
			return false;
		}

		return true;
	}

	public List<Page> getPages() {
		List<Page> ret = new ArrayList<Page>();
		
		for (Page page : pages) {
			ret.add(page.clone());
		}
		
		return ret;
	}
	
	public void deletePageByName(String name) {
		try {
			Page toSearch = getPageByName(name);
			this.pages.remove(toSearch);
		}
		catch (PageNotFoundException ex) { /*silent*/ }
	}
	
	public void addItemToPageByName(Item toAdd, String pageName) {
		try {
		getPageByName(pageName).addItem(toAdd);
		}
		catch (PageNotFoundException ex) { /*silent*/ }
	}
	
	public void removeItemFromPageByName(String name, Units unit, String pageName) {
		try {
		Page toSearch = getPageByName(pageName);
		toSearch.removeItemByNameAndUnit(name, unit);
		}
		catch (PageNotFoundException ex) { /*silent*/ }
		
	}
	
	public List<Item> getItemsFromPageByName(String pageName) {
		List<Item> ret = new ArrayList<Item>();
		try {
			ret = getPageByName(pageName).getItems();
		}
		catch (PageNotFoundException ex) { /*silent*/ }
		
		return ret;
	}
	
	
	public void dispose() {
		pages.clear();
		pages = null;
		singletonPageHandler = null;
	}
	
	
	private Page getPageByName(String name) throws PageNotFoundException{
		for(Page page : pages)
			if(page.getName() == name)
				return page;
			
		throw(new PageNotFoundException());
	}
	
	public void renamePageByName(String oldName, String newName) {
		Page p = null;
		
		try {
		  p = this.getPageByName(oldName);
		}
		catch (PageNotFoundException ex) { /*silent*/ }

        if(p == null)
        	return;

		if (this.containsPageByName(newName))
			return;
		
		p.setName(newName);
	}
	
	public boolean containsItemInPageByNameAndUnit(String name, Units unit, String pageName)
	{
		Page p = null;
		try {
	    p = getPageByName(pageName);
		}
		catch (PageNotFoundException ex) { 
			return false; 
			}

	    return p.containsItemByNameAndUnit(name, unit);
	}
	
}
