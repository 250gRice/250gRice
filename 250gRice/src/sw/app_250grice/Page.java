package sw.app_250grice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Page {
	
	String name;
	List<Item> items;
	
	public Page(String name) {
		items = new ArrayList<Item>();
		this.name = name;
	}
	
	public void addItem(Item toAdd) {
		Item toSearch = getItemByNameAndUnit(toAdd.getName(), toAdd.getUnit());
		
		if(toSearch == null)
			items.add(toAdd);
		else
			toSearch.addValue(toAdd.getValue());
	}
	
	public Item getItemByNameAndUnit(String name, Units unit){
	    for(Item item : items)
	        if(item.getName() == name && item.getUnit() == unit)
	            return item;
		
	    return null;
	}
	
	public void removeItemByNameAndUnit(String name, Units unit) {
		Iterator<Item> itr = items.iterator();
		Item itrItem;
		while(itr.hasNext()) {
			itrItem = itr.next();
			if(itrItem.getName() == name && itrItem.getUnit() == unit){
				itr.remove();
				return;
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this == obj) return true;
		if(!(obj instanceof Page)) return false;
		
		Page page = (Page)obj;
		
		boolean ret = true;
		
		List<Item> thisItems = this.getItems();
		List<Item> pageItems = page.getItems();
		
		ret = (this.name == page.name) && (thisItems.size() == pageItems.size());
		
		if(!ret)
			return ret;
		
		Iterator<Item> thisIt = thisItems.iterator();
		Iterator<Item> pageIt = pageItems.iterator();
		
		while(thisIt.hasNext())
		{
			ret &= thisIt.next().equals(pageIt.next());
		}
		
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = String.format(Locale.US, "Name:%s , Count:%d", this.name, this.items.size());
		
		return ret;
	}

	public String getName() {
		return name;
	}
	
	public List<Item> getItems() {
		List<Item> ret = new ArrayList<Item>();
		
		for (Item item : items) {
			ret.add(item.clone());
		}
		
		return ret;
	}
	
	public Page clone() {
		Page p = new Page(this.name);

		for (Item item : items) {
			p.addItem(item.clone());
		}
		
		return p;
	}
	

}
