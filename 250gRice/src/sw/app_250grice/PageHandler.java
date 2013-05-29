package sw.app_250grice;

import java.util.ArrayList;
import java.util.List;

import sw.exceptions.PageNotFoundException;

public class PageHandler {
	
	private static PageHandler singletonPageHandler;
	private List<Page> pages;
	private Integer currentPageIndex;
	
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
			this.addPage(new Page(name));			
	}
	
	public void addPageExisting(Page page) {
		if(!containsPageByName(page.name))
			this.addPage(page);			
	}
	
	private void addPage(Page page){
		if(page.getId() == null){
			currentPageIndex++;
			page.setId(currentPageIndex);
		}
		pages.add(page);
	}
	
	public void setCurrentPageIndex() {
		if(pages.size() == 0){
			currentPageIndex = 0;
			return;
		}
		
		currentPageIndex = getHighestPageIndex();
	}
	
	private Integer getHighestPageIndex() {
		Integer id = 0;
		for (Page page : pages)
			if(page.getId() > id)
				id = page.getId();
		
		return id;
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
		removAllPages();
		pages = null;
		singletonPageHandler = null;
	}
	
	public void removAllPages() {
		pages.clear();		
	}
	
	private Page getPageByName(String name) throws PageNotFoundException{
		for(Page page : pages)
			if(page.getName() == name)
				return page;
			
		throw(new PageNotFoundException());
	}
	
	public Page getPageById(int id) throws PageNotFoundException {
		for(Page page : pages)
			if(page.getId() == id)
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
